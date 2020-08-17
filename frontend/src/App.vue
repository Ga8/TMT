<template>
      <v-app>
         <NavLinkVue /> 
    <div id="nav">
      <!-- <router-link to="/home">Home</router-link> 
      <router-link to="/create">Create</router-link> 
      <router-link to="/getEvent/0">Get Event</router-link> 
      <router-link to="/about">About</router-link> -->
       <CustomBottomSheetVue />
    </div>
   </v-app>
</template>
<script>
import CustomBottomSheetVue from './components/CustomBottomSheet.vue';
import NavLinkVue from './components/NavLink.vue';

export default {
	name: 'App',
	components: {
		CustomBottomSheetVue, NavLinkVue
	}
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>