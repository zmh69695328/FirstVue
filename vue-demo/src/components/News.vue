<template>
  <div class="news-list">
    <vue-waterfall-easy :imgsArr="imgsArr" @scrollReachBottom="fetchImgsData"></vue-waterfall-easy>
  </div>
</template>

<script>
import vueWaterfallEasy from "vue-waterfall-easy";

export default {
  name: "News",
  components: {
    vueWaterfallEasy
  },
  data() {
    return {
      news: [],
      imgsArr: [],
      fetchImgsArr: [] //存放每次滚动时下一批要加载的图片的数组
    };
  },
  props: {
    gap: {
      // 图片间隔
      type: Number,
      default: 20
    },
    //    imgWidth: { // 指定图片的统一宽度
    //    type: Number,
    //  default: 240
    //},
    timeOut: {
      // 预加载事件小于500毫秒就不显示加载动画，增加用户体验
      type: Number,
      default: 500
    },
    height: {
      type: String,
      default: "100%"
    }
  },
  created() {
    //this.getMsg();
    this.imgsArr = this.initImgsArr(0, 8); //初始化第一次（即页面加载完毕时）要加载的图片数据
    this.fetchImgsArr = this.initImgsArr(8, 16); // 模拟每次请求的下一批新的图片的数据数据
  },
  methods: {
    getMsg() {
      var _this = this;
      this.axios
        .get("http://localhost:8081/hello")
        .then(function(response) {
          console.log(response.data);
          let res = response.data;
          _this.news = res;
          console.log(_this.news);
          _this.imgsArr = _this.initImgsArr(0, 5); //初始化第一次（即页面加载完毕时）要加载的图片数据
          console.log(_this.imgsArr);
          _this.fetchImgsArr = _this.initImgsArr(6, 10); // 模拟每次请求的下一批新的图片的数据数据
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    initImgsArr(n, m) {
      //初始化图片数组的方法，把要加载的图片装入
      var arr = [];
      for (var i = n; i < m; i++) {
        arr.push({
          id: i,
          src: `../../static/img/${i + 1}.jpg`,
          link: "https:/",
          info: "一些图片描述文字"
        });
        //src为加载的图片的地址、link为超链接的链接地址、
        //info为自定义的图片展示信息，请根据自己的情况自行填写
      }
      console.log(arr);
      return arr;
    },
    fetchImgsData() {
      //获取新的图片数据的方法，用于页面滚动满足条件时调用
      this.imgsArr = this.imgsArr.concat(this.fetchImgsArr); //数组拼接，把下一批要加载的图片放入所有图片的数组中
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
