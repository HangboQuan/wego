import Vue from 'vue'
import router from './router'
import App from './App.vue'
import elementUi from 'element-ui'
import VueCookies from 'vue-cookies' 
import 'element-ui/lib/theme-chalk/index.css'
import "./assets/reset.css"
import './assets/css/iconfont.css'
import './assets/css/iconfont.eot'
import './assets/css/iconfont.svg'
import './assets/css/iconfont.ttf'
import './assets/css/iconfont.woff'
import qs from 'qs'
import store from './store/main'
Vue.prototype.$store = store
Vue.use(VueCookies)
import axios from 'axios'
Vue.prototype.$qs = qs;

Vue.prototype.$axios = axios;

Vue.prototype.$red = false;

axios.defaults.withCredentials = true;

Vue.use(elementUi)



// $.ajaxSetup({
//   contentType: 'application/json;charset=utf-8'
// });

Vue.config.productionTip = false
Vue.config.devtools = true;

axios.withCredentials = true
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
