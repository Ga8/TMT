<template>
  <div class="text-center">
    <v-bottom-sheet v-model="sheet">
      <v-sheet class="text-center" height="5%" justify="justify-center">
        <v-btn class="mt-6" color="error" @click="openclose">close</v-btn>
        <div class="input">
          <v-text-field
            class="input"
            label="Event Code"
            placeholder="Put your event code here."
            outlined
            v-model="code"
            maxlength="50"
          >Put your event code inside</v-text-field>
        </div>
        <v-btn type="button" class="success ma-2" @click="getEvent">Get the event</v-btn>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>
<script>
import {router} from "../router/index";


export default {
  data: () => ({
    sheet: false,
    id: 0,
    code: ""
  }),
  computed: {},
  methods: {
     getEvent() {
      if (this.code != null && this.code != "" &&  this.$route.params.guid != this.code) {
       
       router.push({ name: "get-event", params: { guid: this.code }})
       .catch(error => console.log(error.message));
       
       router.go();
        this.sheet = false;
      }else {
          alert("Put a valid code to retrieve an event.")
      }
    },
    openclose() {
      if (this.sheet) {
        this.sheet = false;
      } else {
        this.sheet = true;
      }
    }
  }
};
</script>
<style>
.close {
  color: white;
  font-weight: bold;
}

.get {
  font-weight: bold;
}
.input {
  margin-left: 25%;
  margin-right: 25%;
  margin-top: 2%;
}
</style>