<template>
  <v-flex>
    <TitleVue title="WELCOME" :numerateur="numerateur" :denominateur="denominateur" />
    <v-card max-width="800" class="mx-auto mt-10">
      <v-toolbar color="teal" dark>
        <v-toolbar-title class="title">Choose a name an a title for your event</v-toolbar-title>
        <v-tooltip right>
          <template v-slot:activator="{ on, attrs }">
            <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
          </template>
          <span>
            The name and the title
            <br />are required
          </span>
        </v-tooltip>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-text-field
        label="Title"
        v-model="title"
        :counter="20"
        :rules="[validateTitle]"
        required
        outlined
        class="ma-10"
        maxlength="20"
      ></v-text-field>
      <v-text-field
        v-model="name"
        :counter="20"
        :rules="[validateName]"
        label="Name"
        required
        class="ma-10"
        outlined
        maxlength="20"
      ></v-text-field>
      <v-text-field
        v-model="email"
        :rules="[validateEmail]"
        label="E-mail"
        class="ma-10"
        outlined
        :counter="50"
        maxlength="50"
      ></v-text-field>
    </v-card>
  </v-flex>
</template>
<script>
import TitleVue from "../components/Title.vue";

export default {
  data: () => ({
    email: "",
    emailValid: false,
    name: "",
    nameValid : false,
    title: "",
    titleValid : false ,
    numerateur :0,
    denominateur : 3
  }),
  computed: {},
  components: {
    TitleVue
  },
  methods: {
    updateCount() {
    this.numerateur = 0 ;
      var arr = [this.emailValid, this.titleValid, this.nameValid];
      arr.forEach((value) =>{
        if (value ){
          this.numerateur++;
        } 
      }
      );
    },
    validateTitle() {
      var result =  this.required(this.title);
      if (result) {
         result = this.counter(this.title);
        if ( this.counter(this.title)) {
        result = this.counterMin(this.title); 
          if (result) {
            this.titleValid =  true;
          }
      }
      }
      return result;   
      }
    ,
    validateName() {
      var result =  this.required(this.name);
      if (result) {
         result = this.counter(this.name);
      if ( this.counter(this.name)) {
        this.nameValid =  true;
        }
      }
      return result;
      
      }
    ,
    validateEmail() {
      const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      var result = "Invalid e-mail.";
      result =  this.required(this.email);
      if (result && pattern.test(this.email)) {
        this.emailValid = true ;
       
        result = true;
      }
       this.updateCount();
      return result;
    },
    required(value) {
      return !!value || "Required.";
    },
    counter( value) {
     return value.length <= 20 || "Max 20 characters"
     },
    counterMin( value) {
     return value.length > 2 || "Min 2 characters"
     }
  }
};
</script>
<style scoped>
.dot-50:hover {
  color: white;
  border-color: black;
  background-image: linear-gradient(to half, #00fa9a 51%, transparent 50%);
  background-position: 0 50%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
.dot-80 {
  color: white;
  border-color: black;
  background-image: linear-gradient(to half, #00fa9a 51%, transparent 50%);
  background-position: 5 80%;
  transition: background-position 3000ms, color 3000ms ease,
    border-color 3000ms ease;
}
</style>


