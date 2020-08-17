import Vue from 'vue';
import VCalendar from 'v-calendar';

// Use v-calendar & v-date-picker components
Vue.use(VCalendar, {
  componentPrefix: 'vc',
  screens: {
    tablet: '800px',
    laptop: '1024px',
    desktop: '1024px',
  },
  
});


export default new VCalendar({
});