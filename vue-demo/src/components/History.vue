<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon type="code-sandbox" style="font-size:50px;margin-right:15px;"/>

        <a-dropdown>
          <a class="ant-dropdown-link">
            <router-link to="/collection">我的收藏</router-link>
            <a-icon type="down"/>
          </a>
          <a-menu slot="overlay">
            <a-menu-item>
              <router-link to="/home">首页</router-link>
            </a-menu-item>
            <a-menu-item>
              <router-link to="/history">历史记录</router-link>
            </a-menu-item>
          </a-menu>
        </a-dropdown>

        <span>推荐引擎</span>
        <div class="recommend">
          <span>内容推荐</span>
          <a-switch></a-switch>
          <span>协同过滤</span>
          <a-switch></a-switch>
          <span>热点推荐</span>
          <a-switch></a-switch>
        </div>

        <Search :title="title" @show="showSelected"></Search>

        <div class="userheader">
          <a-icon type="sync" @click="refresh" :spin="flag"/>
          <a-icon type="plus" @click="showDrawer"/>
          <a-drawer
            title="我的订阅"
            placement="right"
            :closable="false"
            @close="onClose"
            :visible="visible_plus"
          >
            <a-input-group compact class="rsshub">
              <span>订阅名称</span>
              <a-auto-complete :dataSource="datasource1" @select="onselect1" v-model="rsshub_name"/>
              <br>
              <span>订阅地址</span>

              <a-auto-complete :dataSource="datasource2" @select="onselect2" v-model="rsshub_url"/>
              <a-button type="primary" block @click="addurl">添加</a-button>

              <a-divider orientation="left">点击下方标签删除</a-divider>
              <a-popconfirm
                title="删除?"
                @confirm="deleteurl(i.name,i.url)"
                okText="确定"
                cancelText="取消"
                v-for="i in rsshub"
                :key="i.name"
              >
                <a-tag>{{i.name}}</a-tag>
              </a-popconfirm>
            </a-input-group>
          </a-drawer>

          <a-popover :title="user.username" placement="bottom">
            <template slot="content">
              <login @update="getUser"></login>
            </template>
            <a-avatar style="text-align:right;" :size="60" :src="user.avatar"></a-avatar>
          </a-popover>
        </div>
      </a-layout-header>
      <a-layout-content>
        <div style="height:100vh">
          <vue-waterfall-easy :imgWidth="400" :maxCols="4" :imgsArr="imgsArr" @click="showModal">
            <div style="background-color:white" class="img-info" slot-scope="props">
              <div style="margin:5px">
                <span style="font-weight:bold;font-size:20px;color:black;">{{props.value.title}}</span>
                <p style="color:grey">{{props.value.info}}</p>
              </div>
              <div class="my-tag">
                <a-tag color="pink">{{props.value.type}}</a-tag>
                <a-tag color="blue">Tag 1</a-tag>
                <a-tag color="black">Tag 1</a-tag>
                <a-tag color="green">Tag 1</a-tag>
                <a-popover trigger="click" v-model="tag_visible[props.index]">
                  <a @click="deletenews(props.index)" slot="content">不感兴趣</a>
                  <a-icon type="ellipsis"/>
                </a-popover>
                <a-icon
                  :theme.sync="click_themeA[props.index]"
                  type="heart"
                  @click="testFun(props.index,props.value._ID)"
                />
              </div>
            </div>
          </vue-waterfall-easy>

          <a-modal
            style="top:8px"
            v-model="visible"
            @ok="handleOk"
            width="850px"
            :footer="null"
            :closable="false"
          >
            <div class="news-modal">
              <p
                style="margin-bottom:5px;font-size: 25px;color:black;text-align: center;"
              >{{news[index].TITLE}}</p>
              <p style="font-size:16px;text-align:center">{{news[index].AUTHOR}}</p>

              <hr style="margin:3px;">

              <div style="font-size:17px;color:#0f0f0f; height:auto; ">
                <div style="height:auto;" v-html="news[index].DESCRIPTION"></div>
              </div>

              <comment ref="mycomment" :user="user" :id="news[index]._ID"></comment>
            </div>
          </a-modal>
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>


<script>
import vueWaterfallEasy from "vue-waterfall-easy";
import Comment from "./Comment";
import Login from "./Login";
import Search from "./Search";
import Vue from "vue";
export default {
  name: "History",
  data() {
    return {
      click_themeA: [],
      favoriteList: [],
      allFavoriteList: [],
      historyList: [],
      datasource2: [
        "https://rsshub.app/cctv/china",
        "https://rsshub.app/juejin/category/frontend",
        "https://rsshub.app/jianshu/home",
        "https://rsshub.app/zhihu/hotlist",
        "https://rsshub.app/smzdm/ranking/pinlei/11/3",
        "https://rsshub.app/wallstreetcn/news/global",
        "https://rsshub.app/3dm/news"
      ],
      datasource1: [
        "央视新闻",
        "掘金前端",
        "简书",
        "知乎热点",
        "什么值得买",
        "华尔街见闻",
        "3DMGame"
      ],
      user: {},
      tag_visible: [],
      flag: false,
      getnum: 10,
      news: [],
      title: [],
      index: 0,
      list: [],
      visible: false,
      collapsed: false,
      imgsArr: [], //存放所有已加载图片的数组（即当前页面会加载的所有图片）
      fetchImgsArr: [], //存放每次滚动时下一批要加载的图片的数组
      visible_plus: false,
      rsshub: [],
      rsshub_name: "",
      rsshub_url: ""
    };
  },
  components: {
    vueWaterfallEasy,
    Comment,
    Login,
    Search
  },
  methods: {
    showSelected(value) {
      //this.$message.success(value);
      for (let i in this.news) {
        if (this.news[i].TITLE == value) {
          this.index = i;
          break;
        }
      }
      setTimeout(() => {
        this.$refs.mycomment.myclick();
      }, 100);
      this.visible = true;
    },
    onselect1(value) {
      let i = this.datasource1.indexOf(value);
      this.rsshub_url = this.datasource2[i];
    },
    onselect2(value) {
      let i = this.datasource2.indexOf(value);
      this.rsshub_url = this.datasource1[i];
    },
    showDrawer() {
      this.getRSShub();
      this.visible_plus = true;
    },
    onClose() {
      this.visible_plus = false;
    },
    recommend1() {},
    recommend2() {},
    recommend3() {},
    favorite() {
      this.getFavoriteList();
      this.imgsArr = [];
      setTimeout(() => {
        for (let i in this.news) {
          for (let j in this.favoriteList) {
            if (this.favoriteList[j].newsid == this.news[i]._ID) {
              this.click_themeA[this.imgsArr.length] = "filled";
              this.imgsArr.push({
                src: this.news[i].img,
                info: this.news[i].info.slice(0, 70).concat("..."),
                title: this.news[i].TITLE,
                _ID: this.news[i]._ID,
                type: this.news[i].TYPE
              });

              break;
            }
          }
        }
      }, 200);
    },
    history() {},
    testFun(index, id) {
      if (this.click_themeA[index] == "filled") {
        Vue.set(this.click_themeA, index, "outlined");
        this.$message.success("取消收藏" + id);
        this.axios
          .post("/delete/favorite", { newsid: id })
          .then(response => {});
        //setTimeout(() => {}, 500);
      } else {
        this.$message.success("收藏成功");
        Vue.set(this.click_themeA, index, "filled");
        this.axios.post("/add/favorite", { newsid: id }).then(response => {});
        //setTimeout(() => {}, 500);
      }
    },
    getUser() {
      if (localStorage.JWT_TOKEN) {
        this.axios
          .get("/information")
          .then(response => {
            this.user = response.data;
            //Vue.set(this.user, "avatar", response.data.avatar);
            console.log(this.user);
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        this.user["username"] = "未登录";
        this.user["avatar"] = "../assets/1.png";
      }
    },
    getImg() {
      //  this.axios.get(this.user.avatar).then(response =>{
      //    console.log("get  ------"+response.data);
      //    retrun response.data;
      //  })
    },
    showModal(event, { index, value }) {
      console.log("-------");
      console.log(value);
      setTimeout(() => {
        this.$refs.mycomment.myclick();
      }, 100);
      for (let i in this.news) {
        if (this.news[i]._ID == value._ID) {
          this.index = i;
          break;
        }
      }

      this.visible = true;
    },
    handleOk(e) {
      this.visible = false;
    },
    initImgsArr(n, m) {
      //初始化图片数组的方法，把要加载的图片装入
      var arr = [];
      for (var i = n; i < m && i < this.news.length; i++) {
        arr.push({
          src: this.news[i].img,
          info: this.news[i].info.slice(0, 70).concat("..."),
          title: this.news[i].TITLE,
          _ID: this.news[i]._ID,
          type: this.news[i].TYPE
        }); //src为加载的图片的地址、link为超链接的链接地址、 //info为自定义的图片展示信息，请根据自己的情况自行填写
      }
      return arr;
    },

    fetchImgsData() {
      //获取新的图片数据的方法，用于页面滚动满足条件时调用

      this.fetchImgsArr = this.initImgsArr(this.getnum, this.getnum + 4); // 模拟每次请求的下一批新的图片的数据数据
      this.imgsArr = this.imgsArr.concat(this.fetchImgsArr); //数组拼接，把下一批要加载的图片放入所有图片的数组中
      this.getnum += 4;
    },
    initList() {
      this.getFavoriteList();
      setTimeout(() => {}, 500);
      this.axios
        .get("/news")
        .then(response => {
          this.news = response.data;
          for (let i in this.news) {
            this.title.push(this.news[i].TITLE);
            var flag = false;
            for (let j in this.favoriteList) {
              if (this.favoriteList[j].newsid == this.news[i]._ID) {
                flag = true;
                break;
              }
            }
            if (flag) this.click_themeA[i] = "filled";
            else this.click_themeA[i] = "outlined";
          }

          //this.imgsArr = this.initImgsArr(0, this.getnum); //初始化第一次（即页面加载完毕时）要加载的图片数据
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    refresh() {
      this.flag = true;
      this.axios
        .get("/test")
        .then(response => {
          this.initList();
          this.flag = false;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    getRSShub() {
      this.axios
        .get("/get/rss")
        .then(response => {
          this.rsshub = response.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    getFavoriteList() {
      this.axios.get("/get/favorite").then(response => {
        this.favoriteList = response.data;
      });
    },
    addurl() {
      this.axios
        .post("/post/rss", { name: this.rsshub_name, url: this.rsshub_url })
        .then(response => {
          this.rsshub.push(response.data);
          this.rsshub_name = this.rsshub_url = "";
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    deleteurl(name1, url1) {
      this.axios
        .post("/delete/rss", { name: name1, url: url1 })
        .then(response => {
          this.getRSShub();
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    deletenews(index) {
      this.imgsArr.splice(index, 1);
      this.$message.success("将减少推荐相关内容");
    }
  },
  created() {
    this.initList();
    this.favorite();
    this.getUser();
  },
  mounted() {}
};
</script>

<style >
.ant-layout-header .userheader {
  float: right;
  margin-right: 20px;
}

.ant-layout-header .userheader .anticon {
  margin-right: 10px;
  cursor: pointer;
  font-size: 23px;
}
.ant-layout-header .userheader .ant-avatar {
  cursor: pointer;
  margin-bottom: 5px;
}
.login {
  cursor: pointer;
}
.ant-modal-content .ant-modal-body .news-modal img {
  height: auto;
}
.recommend {
  display: inline;
}
.recommend span {
  font-weight: bold;
  margin-left: 15px;
}
.recommend .ant-switch {
  margin-left: 5px;
}
.my-tag .ant-tag {
  margin: 0px 0px 3px 3px;
}
.my-tag .anticon {
  float: right;
  margin-right: 5px;
  font-size: 18px;
}
.ant-dropdown-link {
  font-size: 20px;
  font-weight: bold;
  color: black;
  margin-right: 20px;
}
.rsshub span {
  font-size: 15px;
  margin: 5px;
}
.rsshub .ant-btn {
  margin-top: 10px;
}
.rsshub .ant-tag {
  margin-left: 6px;
  margin-top: 5px;
  font-weight: bold;
}
.rsshub .ant-select-auto-complete {
}
</style>