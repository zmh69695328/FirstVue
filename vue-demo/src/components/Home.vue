<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout-sider :style="{height: '100vh'}" :trigger="null" collapsible v-model="collapsed">
      <div class="logo"/>
      <a-menu theme="dark" mode="inline" :defaultSelectedKeys="['1']">
        <a-menu-item key="1">
          <a-icon type="user"/>
          <span>发现</span>
          <router-link to="/newslist"></router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <a-icon type="video-camera"/>
          <span>我的订阅</span>
        </a-menu-item>
        <a-menu-item key="3">
          <a-icon type="upload"/>
          <span>我的收藏</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0" class="header">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="()=> collapsed = !collapsed"
        />
        <a-icon
          type="sync"
          style="font-size:20px;"
          @click="()=> collapsed = !collapsed"
          :spin="flag"
        />
        <a-avatar
          :size="62"
          src="https://s.gravatar.com/avatar/7b3885009be8e0f49e1a8d4f145c5563?s=80"
        ></a-avatar>
      </a-layout-header>
      <a-layout-content>
        <news-list></news-list>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>


<script>
import vueWaterfallEasy from "vue-waterfall-easy";
import Comment from "./Comment";
import NewsList from "./NewsList";
import Header from "./Header";
export default {
  name: "Home",
  data() {
    return {
      flag: true,
      index: 0,
      list: [],
      visible: false,
      collapsed: false,
      imgsArr: [], //存放所有已加载图片的数组（即当前页面会加载的所有图片）
      fetchImgsArr: [] //存放每次滚动时下一批要加载的图片的数组
    };
  },
  components: {
    Header,
    vueWaterfallEasy,
    Comment,
    NewsList
  },
  methods: {
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
      for (var i = n; i < m; i++) {
        arr.push({
          src: this.news[i].img,
          link: this.news[i].img,
          info: this.news[i].DESCRIPTION.slice(0, 50),
          title: this.news[i].TITLE
        }); //src为加载的图片的地址、link为超链接的链接地址、 //info为自定义的图片展示信息，请根据自己的情况自行填写
      }
      return arr;
    },

    fetchImgsData() {
      //获取新的图片数据的方法，用于页面滚动满足条件时调用
      this.imgsArr = this.imgsArr.concat(this.fetchImgsArr); //数组拼接，把下一批要加载的图片放入所有图片的数组中
    },
    initList() {
      this.axios
        .get("/news")
        .then(response => {
          this.news = response.data;
          console.log(this.news);
          this.imgsArr = this.initImgsArr(0, 10); //初始化第一次（即页面加载完毕时）要加载的图片数据
          this.fetchImgsArr = this.initImgsArr(0, 10); // 模拟每次请求的下一批新的图片的数据数据
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
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

.ant-modal-header .ant-modal-title {
  font-size: 25px;
  text-align: center;
}
.ant-modal-body .news-modal div p div img {
  height: auto;
  width: 100%;
  vertical-align: bottom;
}
</style>