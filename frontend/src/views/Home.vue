<template>
  <v-flex>
    <loadingVue text="Loading" :loading=loading />

  </v-flex>
</template>
<script>
import LoadingVue from "../components/Loading.vue"


export default {

  data: () => ({
    email: "",
    emailValid: false,
    name: "",
    nameValid : false,
    title: "",
    titleValid : false ,
    numerateur :3,
    denominateur : 3,
    loading :true,
  }),  components: {
    LoadingVue
  },
  computed: {},
 
  methods: {

  test : function () {
  console.log("TESSSSSSSSSSSSSSSSSSSST")
  }}
};
</script>
<style scoped>


</style>


