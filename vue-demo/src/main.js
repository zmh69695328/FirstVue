import Vue from 'vue'
import App from './App.vue'

import axios from 'axios'
Vue.config.productionTip = false
Vue.prototype.axios = axios
axios.defaults.withCredentials = false
axios.defaults.baseURL = 'http://localhost:8081'

import VueRouter from 'vue-router'

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
Vue.config.productionTip = false
Vue.use(Antd);
Vue.use(VueRouter);

// http request 拦截器
axios.interceptors.request.use(
  config => {
    if (localStorage.JWT_TOKEN) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
      config.headers.Authorization = `${localStorage.JWT_TOKEN}`;
    }
    return config;
  },
  err => {
    return Promise.reject(err);
  });


import Home from "./components/Home.vue";
import Test from "./components/Test.vue";
import Collection from "./components/Collection.vue";

const routes = [
  { path: '/home', component: Home },
  { path: '/Test', component: Test },
  { path:'/collection',component:Collection},
]

const router = new VueRouter({
  routes // (缩写) 相当于 routes: routes
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
