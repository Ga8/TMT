<template>
  <v-flex>
    <TitleVue title="WELCOME" @click.native="test" :numerateur="numerateur" :denominateur="denominateur" />
    <v-card max-width="800" class="mx-auto mt-10">
      <v-toolbar color=#4DBA87 class="head" dark>
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
      <!-- <v-text-field
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
      ></v-text-field> -->
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
    numerateur :3,
    denominateur : 3
  }),
  computed: {},
  components: {
    TitleVue
  },
  methods: {
  //   updateCount() {
  //   this.numerateur = 0 ;
  //     var arr = [this.emailValid, this.titleValid, this.nameValid];
  //     arr.forEach((value) =>{
  //       if (value ){
  //         this.numerateur++;
  //       } 
  //     }
  //     );
  //   },
  //   validateTitle() {
  //     this.titleValid =  false;
  //     var result =  this.required(this.title);
  //     if (result===true) {
  //        result = this.counter(this.title);
  //       if ( result===true) {
  //       result = this.counterMin(this.title); 
  //         if (result ===true) {
  //           this.titleValid =  true;
  //         }
  //     }
  //     }
  //     this.updateCount();
  //     return result;   
  //     }
  //   ,
  //   validateName() {
  //      this.nameValid =  false;
  //     var result =  this.required(this.name);
  //      console.log(result);
  //     if (result === true) {
  //        console.log(result);
  //        result = this.counter(this.name);
  //     if (result ===true) {
  //        console.log(result);
  //        result = this.counterMin(this.name);
  //        if (result ===true ){
  //       this.nameValid =  true;
  //       }
  //       }
  //     }
  //       console.log(result);
  //       console.log("name : " +this.name)
  //       console.log("counter : " + this.counterMin(this.name))
  //      this.updateCount();
  //     return result;
      
  //     }
  //   ,
  //   validateEmail() {
  //     const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  //     var result = "Invalid e-mail.";
  //      this.emailValid = false;
  //     result =  this.required(this.email);
  //     if (result ===true ) {
  //       result =  pattern.test(this.email)

  //       if (result === true){
  //       this.emailValid = true ;
       
  //      }
  //     }
  //      this.updateCount();
  //     return result;
  //   },
  //   required(value) {
  //     return !!value || "Required.";
  //   },
  //   counter( value) {
  //    return value.length <= 20 || "Max 20 characters"
  //    },
  //   counterMin( value) {
  //    return value.length > 2 || "Min 2 characters"
  //    }
  // },
  test : function () {
  console.log("TESSSSSSSSSSSSSSSSSSSST")
  }}
};
</script>
<style scoped>

.head{
  background :  linear-gradient(to right ,#051422,#075D63)
}
.title{
  color : #ffffff;
  opacity: 80%;
}
</style>


