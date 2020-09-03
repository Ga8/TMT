import Vue from "vue";
import Vuex from "vuex";
import * as axios from "axios";
import {router} from "../router/index";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
   meeting : [],
  },
  getters: {
    attributes: (state) => {
      return state["meeting.opportunities"].map((op) => ({
        dates: op.date,
        dot: {
          color: op.color,
        },
        popover: {
          label: op.label,
        },
      }));
    },
  },
  mutations: {
    updateState(meeting) {
      this.state.meeting = meeting;
    },
  },
  actions: {
    async addMeeting(commit, meeting) {
    
      var response = await axios({
        method: "post",
        url: "/api/addmeeting",
        data: meeting,
      }).then(  router.push(`/GetEvent/${response.data}`)
      .catch(router.push(`/GetEvent/error`)));
     
      router.go();
   
    },
    async isvalidRedirect(commit, id) {
      console.log(id);
      var response = await axios({
        method: "get",
        url: "/api/isvalid",
        params: { guid: id },
      })
        .then(console.log(response))
        .catch((error) => console.log(error));

      if (response !== undefined && response.data != undefined && response.data) {
        router.push({ name: "get-event", params: { guid: id }}).catch(error => console.log(error.message));
      }
    },
     async isvalid(commit, id) {
      // console.log("isvalid id : " +id);
      var response = await axios({
        method: "get",
        url: "/api/isvalid",
        params: { guid: id },
      })
        .then(console.log("is valid response : " + response.data))
        .catch((error) => console.log(error));

        return response.data;
    },
     async  get( commit , id ){
       console.log("im in store with id : " + id);
      var test ;
       await  axios({
        method: "get",
        url: "/api/getmeeting",
        params: { guid: id },
      })
        .then(response =>{ this.meeting = response.data;
          console.log("response promise axios : " + JSON.stringify(response.data))
          console.log("meeting promise axios : " + JSON.stringify(this.meeting))
        test = response})
        .catch((error) => console.log("error : " +error.message));

        console.log("test : " + JSON.stringify(test.data))
        return test.data;
    },
    async update(commit, id) {
      console.log("update id : " + id);
      var response = await axios({
        method: "post",
        url: "/api/update",
        params: { guid: id },
      })
        .then(console.log(response))
        .catch((error) => console.log(error));

      return response.data;
    },
  }
});
