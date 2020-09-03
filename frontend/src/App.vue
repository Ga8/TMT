<template>
      <v-app>
         <NavLinkVue /> 
           <v-content>
      <v-container class="fill-height" fluid>
        <router-view />
      </v-container>
    </v-content>
    <v-footer app height=30>
            <div class="footer"> &copy; {{ new Date().getFullYear() }} Made with
          <v-icon color="teal">mdi-cards-heart</v-icon> by Gaëtan Guibaud</div>
    </v-footer>
   </v-app>
</template>
<script>

import NavLinkVue from './components/NavLink.vue';

export default {
	name: 'App',
	components: {
		NavLinkVue
	}
}
</script>

<style lang="scss">
#app {
 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  
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
.footer {
  right: 10px;
  position: absolute;
}
</style>