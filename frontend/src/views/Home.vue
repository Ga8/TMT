<template>
<v-flex>
 <TitleVue title="WELCOME"  />
 
</v-flex>
</template>
<script>
import TitleVue from '../components/Title.vue';


export default {
  data: () => ({
 
  }),
  components: {
		TitleVue
	},
  methods: {
    
  }
};
</script>
<style scoped>
.dot-50:hover {
  color: white;
  border-color: black;
  background-image: 
    linear-gradient(to half, #00FA9A 51%, transparent 50%);
  background-position: 0 50%;
  transition: background-position 3000ms, color 3000ms ease, border-color 3000ms ease;
}
.dot-80 {
  color: white;
  border-color: black;
  background-image: 
    linear-gradient(to half, #00FA9A 51%, transparent 50%);
  background-position: 5 80%;
  transition: background-position 3000ms, color 3000ms ease, border-color 3000ms ease;
}
</style>


