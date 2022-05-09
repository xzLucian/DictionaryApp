import App from './App'

// #ifndef VUE3
import Vue from 'vue'
import uView from "uni_modules/uview-ui"
import cuCustom from './colorui/components/cu-custom.vue'
import store from './store'

Vue.use(uView);
Vue.component('cu-custom',cuCustom);
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	store,
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif