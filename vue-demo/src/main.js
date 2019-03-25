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


import News from "./components/News.vue";
import Home from "./components/Home.vue";
import Test from "./components/Test.vue"
const routes = [
  { path: '/news', component: News },
  { path: '/home', component: Home },
  { path: '/Test', component: Test }
]

const router = new VueRouter({
  routes // (缩写) 相当于 routes: routes
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
