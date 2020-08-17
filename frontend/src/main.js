import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VCalendar from './plugins/VCalendar';
import router from './router';
import store from './store/store';
import MyDatePicker from './components/MyDatePicker';


Vue.config.productionTip = false

new Vue({
  vuetify,
  VCalendar,
  router,
  store,
  MyDatePicker,
  render: h => h(App)
}).$mount('#app')
