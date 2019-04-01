<template>
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
        <div style="text-align:right;margin-right:5px;">
          <a-icon type="heart" @click="testFun"/>
        </div>
      </div>
    </vue-waterfall-easy>
    <a-modal style="top:8px" v-model="visible" @ok="handleOk" width="800px" :footer="null">
      <div class="news-modal">
        <p
          style="margin-bottom:5px;font-size: 25px;color:black;
  text-align: center;"
        >{{news[index].TITLE}}</p>
        <p style="font-size:16px;text-align:center">{{news[index].AUTHOR}}</p>

        <hr style="margin:3px;">

        <div style="font-size:17px;color:#0f0f0f; height:auto; ">
          <p v-html="news[index].DESCRIPTION"></p>
        </div>

        <comment></comment>
      </div>
    </a-modal>
  </div>
</template>


<script>
import vueWaterfallEasy from "vue-waterfall-easy";
import Comment from "./Comment";
export default {
  name: "NewsList",
  data() {
    return {
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
    Comment
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
a-layout-content {
  height: "100vh";
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