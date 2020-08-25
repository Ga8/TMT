<template>
  <v-container>
    <v-btn :disabled="percentage < 80 ">
      <div
        class="headshot headshot-1 dot"
       
         
          v-bind:class="classObject" >

        <v-row justify="space-around">
          <v-spacer class="space"></v-spacer>
          <span class="letter">{{title}}</span>
          <v-spacer class="space"></v-spacer>
        </v-row>
      </div>
    </v-btn>
  
  </v-container>
</template>
<script>
//mport { mapGetters, mapState } from "vuex";

export default {
  name: "TitleVue",
  props: {
    title: String,
    numerateur : Number,
    denominateur : Number
  },
  data: () => ({
  
  }),
  computed: {
    percentage : function ()
    {
      console.log(this.numerateur / this.denominateur);
       return 100*(this.numerateur / this.denominateur ); 
    },

    classObject : function (){
      console.log(  10*Math.floor(this.percentage/10));
      return "dot-"+   10*Math.floor(this.percentage/10);
    }
    
  },

  methods: {
 
}};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Lato:900);
body {
  font-family: "Lato", sans-serif;
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
.dot-10 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 10%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-20 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 20%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-30 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 30%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-40 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 40%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-50 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 50%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-60 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 60%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-70 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 70%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}

.dot-80{
  background-image: linear-gradient(to top, green 51%, transparent 50%);
  background-position: 0 80%;
  transition: background-position 3000, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-90 {
  background-image: linear-gradient(to top, green 50%, transparent 50%);
  background-position: 0 90%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-100 {
  background-image: linear-gradient(to top, green 51%, transparent 50%);
  background-position: 0 100%;
  transition: background-position 3000, color 3000ms ease,
    border-color 3000ms ease;
}

.pulse-button {
  border: none;
  box-shadow: 0 0 0 0 lightyellow;

  cursor: pointer;
  -webkit-animation: pulse 1.25s infinite cubic-bezier(0.33, 0, 0, 1);
  -moz-animation: pulse 1.25s infinite cubic-bezier(0.33, 0, 0, 1);
  -ms-animation: pulse 1.25s infinite cubic-bezier(0.33, 0, 0, 1);
  animation: pulse 1.25s infinite cubic-bezier(0.33, 0, 0, 1);
}
.pulse-button:hover {
  -webkit-animation: none;
  -moz-animation: none;
  -ms-animation: none;
  animation: none;
}

.headshot {
  box-shadow: inset 0 0 25px white, 0 0 45px #009688;
  padding: 45px;
  max-width: 400px;
  margin-left: auto;
  margin-top: 30px;
  margin-bottom: 30px;
}
.space {
  min-width: 40px;
}
.headshot-1 {
  border-radius: 50%;
}
.letter {
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


