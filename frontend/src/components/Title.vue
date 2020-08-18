<template>
 <v-container class="headshot headshot-1">
<v-row justify="space-around">
<div v-for="word in titleTab" :key="word" >
<span class="letter" :data-letter="word">{{word}}</span>
<v-spacer class="space"></v-spacer>
</div>
</v-row>
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
  
  }),
  computed: {
    titleTab : function () {
        
       return  this.title.split(' ');

  }
  },

  methods: {
    
  }
};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Lato:900);
body{
  font-family: 'Lato', sans-serif;
    ;
}
.headshot {
  box-shadow: inset  0 0 25px  white,  0 0 45px #009688 ;
  padding : 45px;
  max-width: 400px;
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
  color: #009688;
  transform-style: preserve-3d;
  perspective: 400;
  z-index: 1;
}
.letter:before, .letter:after{
  position:absolute;
  content: attr(data-letter);
  transform-origin: top left;
  top:0;
  left:0;
}
.letter, .letter:before, .letter:after{
  transition: all 0.3s ease-in-out;
}
.letter:before{
  color: #fff;
  text-shadow: 
    -1px 0px 1px rgba(255,255,255,.8),
    1px 0px 1px rgba(0,0,0,.8);
  z-index: 3;
  transform:
    rotateX(0deg)
    rotateY(-15deg)
    rotateZ(0deg);
}
.letter:after{
  color: rgba(0,0,0,.11);
  z-index:2;
  transform:
    scale(1.08,1)
    rotateX(0deg)
    rotateY(0deg)
    rotateZ(0deg)
    skew(0deg,1deg);
}
.letter:hover:before{
  color: #fafafa;
  transform:
    rotateX(0deg)
    rotateY(-40deg)
    rotateZ(0deg);
}
.letter:hover:after{
  transform:
    scale(1.08,1)
    rotateX(0deg)
    rotateY(40deg)
    rotateZ(0deg)
    skew(0deg,22deg);
}
</style>


