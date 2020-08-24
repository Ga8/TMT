<template>
 <v-container >
  <div class="headshot headshot-1 dot" v-bind:class="{'dot-25':twenty, 'dot-50':fifty, 'dot-75':seventy,'dot-100':hundred}">
<v-row justify="space-around">
<v-spacer class="space"></v-spacer>
<span class="letter" >{{title}}</span>
<v-spacer class="space"></v-spacer>

</v-row>
  </div>
  <div> <v-btn color=teal v-on:click="click()"> {{count}}</v-btn></div>
  <div class="ma-4"> <v-btn color=teal v-on:click="click0()"> zero</v-btn></div>
</v-container>

</template>
<script>
//mport { mapGetters, mapState } from "vuex";

export default {
  name: "TitleVue",
  props: {
      title: String
  },
  data: () => ({
  hundred : false,
  seventy : false,
  fifty : false,
  twenty : false,
  count: 0,
  }),
  computed: {
  
  },

  methods: {
    click(){
      this.count = this.count + 25 ;
      if (this.count >= 100 ){
        
        this.hundred = true;
        }else if (this.count >= 75){
        
         this.seventy = true;
      }else if (this.count >= 50){
       
        this.fifty =true ;
      }else if (this.count >= 25 ){
        this.twenty = true;
      }
      
    },
     click0(){
      this.count = 0 ;
      this.fifty = false;
      this.hundred = false;
       this.seventy = false;
       this.twenty=false;

    }
    
  }
};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Lato:900);
body{
  font-family: 'Lato', sans-serif;
    
}

.dot {
  border: 1px;  
  border-style: solid;
  border-radius: 50%;
  border-color: black;
  color: black;
  padding: 5px;
  background-size: 200% 200%;

}
.dot-25 {
  background-image: 
    linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 25%;
  transition: background-position 3000ms, color 3000ms ease, border-color 3000ms ease;
}
.dot-50 {
  background-image: 
    linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 50%;
  transition: background-position 3000ms, color 3000ms ease, border-color 3000ms ease;
}

.dot-75 {
  background-image: 
    linear-gradient(to top, green 51%, transparent 50%);
  background-position: 0 75%;
  transition: background-position 3000, color 3000ms ease, border-color 3000ms ease;
}
.dot-100 {
  background-image: 
    linear-gradient(to top, green 51%, transparent 50%);
  background-position: 0 100%;
  transition: background-position 3000, color 3000ms ease, border-color 3000ms ease;
}
.headshot {
  box-shadow: inset  0 0 25px  white,  0 0 45px #009688 ;
  padding : 45px;
  max-width: 400px;
  margin-left: auto;
  margin-top: 30px;
   margin-bottom: 30px;
}
.space{
    min-width: 40px;
}
.headshot-1 {
	border-radius: 50%;

}
.letter{
  display: inline-block;
  font-weight: 800;
  font-size: 2em;
  margin: 0;
  position: relative;
  color: white;
  transform-style: preserve-3d;
  perspective: 400;
  z-index: 1;
}


</style>


