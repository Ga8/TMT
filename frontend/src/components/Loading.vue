     
<template>
  <v-row justify="center">
    <v-col cols="6" md="6">
      <div class="d-flex justify-center" v-if="loading">
        <v-progress-circular indeterminate color="teal" width="20" size="400"><span class="text">{{text}}</span>
        </v-progress-circular>
      </div>
    </v-col>
  </v-row>
</template>

<script>
export default {
    name: "LoadingVue",
  props: {
    text: String,
    loading : Boolean
    
  },
}
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Merienda+One);

.text{
    font-family : 'Courier New', Courier, monospace;
    color: aqua;
    font-size: 1.5em;
    
}
</style>