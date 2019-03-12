import Vue from 'vue'
import App from './App.vue'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import axios from 'axios'
Vue.config.productionTip = false

Vue.use(iView);
Vue.prototype.$axios = axios
Vue.config.productionTip = false
axios.defaults.withCredentials = false

new Vue({
  render: h => h(App),
}).$mount('#app')
