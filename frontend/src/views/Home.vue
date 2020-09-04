<template>
 <div>
   <div class="">
    <h3>Welcome to </h3>
  <h1><span class="fontawesome-star star"></span> <span>Take My Time</span> <span class="fontawesome-star star"></span></h1>
   </div>
     <v-expansion-panels  class="faq">
    <v-expansion-panel>
      <v-expansion-panel-header class="titre impair">What is this website for? </v-expansion-panel-header>
      <v-expansion-panel-content class="content" >
        This site is a simple and quick way to create an appointment when you don't know guests disponibilities. <br /><br />
        For exemple you need to organise an appointment and you want to know where your guest can be there.   <br />
        So,you create an event, the event creation will be associated with a code for your event.  <br /><br />
        Now, you just send the code event to your guest. They give their diponibilities.  <br />
        Then you can see at a glance when your guests are the most available for your appointment.   <br /><br />
         The final word is always for you, you will choose what ever they vote but you can do it with full knowledge of the facts.<br />
         If guests give me valid email they will be inform of the final choice. 
      </v-expansion-panel-content>
    </v-expansion-panel>
     <v-expansion-panel>
      <v-expansion-panel-header class="titre pair">Is it free? </v-expansion-panel-header>
      <v-expansion-panel-content class="content" >
        Yes it's totally free for you but not for me. 
      </v-expansion-panel-content>
    </v-expansion-panel>
     <v-expansion-panel>
      <v-expansion-panel-header class="titre impair">How create my event? </v-expansion-panel-header>
      <v-expansion-panel-content class="content" >
        This site is a simple and quick way to create an appointment when you don't know guests disponibilities. <br /><br />
        For exemple you need to organise an appointment and you want to know where your guest can be there.   <br />
        So,you create an event, the event creation will be associated with a code for your event.  <br /><br />
        Now, you just send the code event to your guest. They give their diponibilities.  <br />
        Then you can see at a glance when your guests are the most available for your appointment.   <br /><br />
         The final word is always for you, you will choose what ever they vote but you can do it with full knowledge of the facts.<br />
         If guests give me valid email they will be inform of the final choice. 
      </v-expansion-panel-content>
    </v-expansion-panel>
    <v-expansion-panel
    >
      <v-expansion-panel-header class="titre pair">Someone give me a code what i must do? </v-expansion-panel-header>
      <v-expansion-panel-content class="content">
        The person who give you the code is organising an event and he ask to you to give your disponibility to participate to this event.<br />
        In order to give your disponibility you can click on <img src="../assets/getEvent.png" width="200px" class= "img " /> or in <img src="../assets/loupe.png" width="60px" class= "img " />
         that you see in the right top corner. 
         <br /> <br />That action must open a  bottom form : <br /> <img src="../assets/bottomForm.jpg"  class= "grandeimg " />  
        <br /> Then just paste the code to retrieve the host event. 
      </v-expansion-panel-content>
    </v-expansion-panel>
    <v-expansion-panel
    >
      <v-expansion-panel-header class="titre impair">Why I need to give my email on event creation? </v-expansion-panel-header>
      <v-expansion-panel-content class="content">
        When you create an event I ask for your email in order to send you event informations.<br />
         That way you get a save of the events codes and you don't have to create a new event if you loose yours codes. <br />
         Moreover you will be inform on guest participations. <br />

         
        <span class="error" >Your email adress will never be used for advertising. </span>

      </v-expansion-panel-content>
    </v-expansion-panel>
    <v-expansion-panel
    >
      <v-expansion-panel-header class="titre pair">Why I should give my email on event participation? </v-expansion-panel-header>
      <v-expansion-panel-content class="content">
        That email is just needed to inform you when the event owner will choose the final date of the event you just vote. <br />
        <span class="error" > Your email adress will never be used for advertising. </span>
      </v-expansion-panel-content >
    </v-expansion-panel>
    <v-expansion-panel
    >
      <v-expansion-panel-header class="titre impair">Why do you build this application web?  </v-expansion-panel-header>
      <v-expansion-panel-content class="content">
        Firstly,  this web appplication is made for me. I wanted to find a solution to meet my friends but, you know,  no one get the same disponibilities.
      <br /> So i create that web application to see quickly when my friends are available for a party or at least when 80% of my guests can be there. 
      <br /> <br /> Moreover I really enjoy building web application with new technology and i was seeking for a project to use Vue.js. 
       in this project I step up on many parts of softwre building like UX design, UI, Css transition, web hosting, database administration ...
       <br /> And i can show to my company or future company my skill with vue.js and Springboot. 
      <br /> So i build it in Vue.js using most vue tool I can. Taking time to do the best practice and understand the most deeply as i can this framework.
       <br /><br /> I work 500 hours on this project.  I have some big improvement in my mind,  but I'll do it if this application get a little success and maybe i can get some money with adds.
       <br />If you don't like it i will go on another project. 
   


      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
 </div>
 
</template>
<script>


export default {

  data: () => ({
  
  }),  components: {
 
  },
  computed: {

  },
  methods: {

  }


};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=EB+Garamont);
@import url(https://fonts.googleapis.com/css?family=Oswald:400|Open+Sans:700);
@import url(http://weloveiconfonts.com/api/?family=fontawesome);
.pair {
  background : linear-gradient(to right , #10201E,  #4DBA87);
}
.impair{
background :linear-gradient(to right ,  #4DBA87, #10201E);
}
.img {
  margin-top : 5px;
  margin-bottom: -15px;
  border : lightgray 1px solid;
  margin-left : 10px ;
  margin-right:  10px; 
}
.faq{
width : 80%;
margin: auto ;
}
.grandeimg {
border : lightgray 1px solid;
margin : 10px;
}
.content {
  text-align: start;
  padding : 20px;
  font-family: 'EB Garamont', sans-serif;
}
.titre {
  font-size: larger;
  opacity: 87%;
}


/* fontawesome */
[class*="fontawesome-"]:before {
  font-family: 'FontAwesome', sans-serif;
}


section.text {
  position: absolute;
  width: 100%;
  min-width: 800px;
  text-align: center;
  top: 50%;
 
  
}

h1, h3 {
  transform: matrix(1, -0.20, 0, 1, 0, 0);
  -ms-transform: matrix(1, -0.20, 0, 1, 0, 0);
  -webkit-transform: matrix(1, -0.20, 0, 1, 0, 0);
  margin-bottom: 80px;
 
}

h1 {
  font-family: "Oswald", Sans-serif;
  text-transform: uppercase;
  font-weight: 400;
  font-size: 70px;
  text-shadow: 4px 5px #4DBA87, 6px 7px #c6a39a;
  opacity: 87%;
  margin-bottom: 100px;
}

h1 span {
  display: inline-block;
  vertical-align: middle;
}

span.star {
  font-size: 20px;
}

h3 {
  font-family: "Open Sans", Sans-serif;
  text-transform: uppercase;
  font-weight: 700;
  font-size: 20px;
  letter-spacing: 0.1em;
  margin-bottom: 10px;  
  position: relative;
  opacity: 87%;
  margin-top:-155px;
}

h3:before, h3:after {
  content: " ";
  position: absolute;
  width: 100px;
  height: 8px;
  border-top: 2px solid #4DBA87;
  border-bottom: 2px solid rgb(39, 117, 108);
}

h3:before {
  margin: 5px 0 0 -110px;
}

h3:after {
  margin: 5px 0 0 10px;
}


</style>


