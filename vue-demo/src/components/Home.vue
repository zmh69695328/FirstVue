<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon type="code-sandbox" style="font-size:50px;margin-right:15px;"/>

        <a-dropdown>
          <a class="ant-dropdown-link" href="#">
            首页
            <a-icon type="down"/>
          </a>
          <a-menu slot="overlay">
            <a-menu-item>
              <a href="javascript:;">我的收藏</a>
            </a-menu-item>
            <a-menu-item>
              <a href="javascript:;">浏览历史</a>
            </a-menu-item>
          </a-menu>
        </a-dropdown>

        <span>推荐引擎</span>
        <div class="recommend">
          <span>内容推荐</span>
          <a-switch></a-switch>
          <span>协同过滤</span>
          <a-switch></a-switch>
          <span>关联规则</span>
          <a-switch></a-switch>
        </div>

        <div class="userheader">
          <a-icon type="sync" @click="refresh" :spin="flag"/>

          <a-popover title="熟睡的哲学家" placement="bottom">
            <template slot="content">
              <login></login>
            </template>
            <a-avatar
              style="text-align:right;"
              :size="60"
              src="https://s.gravatar.com/avatar/7b3885009be8e0f49e1a8d4f145c5563?s=80"
            ></a-avatar>
          </a-popover>
        </div>
      </a-layout-header>
      <a-layout-content>
        <div style="height:100vh">
          <vue-waterfall-easy
            :imgWidth="400"
            :maxCols="4"
            :imgsArr="imgsArr"
            @scrollReachBottom="fetchImgsData"
            @click="showModal"
          >
            <div style="background-color:white" class="img-info" slot-scope="props">
              <div style="margin:5px">
                <span style="font-weight:bold;font-size:20px;color:black;">{{props.value.title}}</span>
                <p style="color:grey">{{props.value.info}}</p>
              </div>
              <div class="my-tag">
                <a-tag color="pink">Tag 1</a-tag>
                <a-tag color="pink">Tag 1</a-tag>
                <a-tag color="pink">Tag 1</a-tag>
                <a-tag color="pink">Tag 1</a-tag>

                <a-popover title="1111" trigger="click" v-model="tag_visible">
                  <a slot="content">Close</a>
                  <a-icon type="ellipsis"/>
                </a-popover>
                <a-icon type="heart" @click="testFun"/>
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
                style="margin-bottom:5px;font-size: 25px;color:black;
  text-align: center;"
              >{{news[index].TITLE}}</p>
              <p style="font-size:16px;text-align:center">{{news[index].AUTHOR}}</p>

              <hr style="margin:3px;">

              <div style="font-size:17px;color:#0f0f0f; height:auto; ">
                <div style="height:auto;" v-html="news[index].DESCRIPTION"></div>
              </div>

              <comment></comment>
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
export default {
  name: "Home",
  data() {
    return {
      tag_visible: false,
      flag: false,
      getnum: 8,
      news: [],
      index: 0,
      list: [],
      visible: false,
      collapsed: false,
      imgsArr: [], //存放所有已加载图片的数组（即当前页面会加载的所有图片）
      fetchImgsArr: [] //存放每次滚动时下一批要加载的图片的数组
    };
  },
  components: {
    vueWaterfallEasy,
    Comment,
    Login
  },
  methods: {
    recommend1() {},
    recommend2() {},
    recommend3() {},
    testFun() {
      console.log("------------------------------------------");
    },
    showModal(event, { index, value }) {
      // console.log("visible");
      this.visible = true;
      console.log(index);
      this.index = index;
    },
    handleOk(e) {
      console.log(e);
      this.visible = false;
    },
    initImgsArr(n, m) {
      //初始化图片数组的方法，把要加载的图片装入
      var arr = [];
      for (var i = n; i < m && i < this.news.length; i++) {
        arr.push({
          src: this.news[i].img,
          info: this.news[i].info.slice(0, 70).concat("..."),
          title: this.news[i].TITLE
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
      this.axios
        .get("/news")
        .then(response => {
          this.news = response.data;
          console.log(this.news);
          this.imgsArr = this.initImgsArr(0, this.getnum); //初始化第一次（即页面加载完毕时）要加载的图片数据
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
    }
  },

  created() {
    this.initList();
  }
};
</script>

<style scoped>
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
  margin-right: 8px;
}
</style>