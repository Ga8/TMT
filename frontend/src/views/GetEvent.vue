<template>
  <v-container class="loadContainer">
    <v-row justify="center">
      <v-col cols="6" md="6">
      <loadingVue text="Loading" :loading=loading />
      </v-col>
    </v-row>
    <div v-if="data" class="ma-2">
      <v-row justify="center">
    <v-col cols="12" md="12" sm="12" >
          <h3>Welcome to the event </h3>
  <h1><span class="fontawesome-star star"></span> <span>{{title}}</span> <span class="fontawesome-star star"></span></h1>

          <h5 class="smoothcolor secondtitle ">created by</h5>
          <h2 class="cool">{{author}}</h2>
    </v-col>  
     <v-col cols="12" md="3" sm="9" class="ma-4">
          <h5 class="mb-4 smoothcolor">Grant access to this event giving this code to yours guests:</h5>
          <v-text-field style="width: 350px; margin-bottom: -15px;"  class="mx-auto" label="Meeting Code" outlined readonly :value="guid" id="meetingid"></v-text-field>
          <v-btn color=#094E47 class="mb-4 " @click="copy">Copy me</v-btn>
      </v-col>

      </v-row>
    </div>
    <div v-if="data">
      <v-form ref="form" lazy-validation>
         <v-card max-width="80%" class="mx-auto">
                  <v-toolbar class="head" color="teal" dark>
                    <v-toolbar-title class="title"><span class="head-title" >New participant informations</span></v-toolbar-title>
                    <v-tooltip right>
                      <template v-slot:activator="{ on, attrs }">
                        <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
                      </template>
                      <span>
                        If you didn't vote for the event date yet,
                        <br /> click on your disponibilities days
                        <br /> and indicate your name.
                        <br /> Then submit.
                      </span>
                    </v-tooltip>
                    <v-spacer></v-spacer>
                  </v-toolbar>
        <v-row justify="space-around">
          <v-col cols="12" md="7" sm="12" class="ma-4 border">
            <h5 class="mb-4 smoothcolor">Choose yours disponibilities (max : 5)</h5>
            <p v-if="!dateIsChosen" class="error">Choose at least one date</p>

            <vc-date-picker
              mode="multiple"
              is-expanded
              v-model="dates"
              :attributes="attributes"
              is-inline
              :available-dates="availableDates"
              popover-visibility="focus"
              color="teal"
              @dayclick="dayClicked"
              :rules="datesRules"
              :columns="$screens({ default: 1, xl: 2 })"
              :rows="$screens({ default: 1, xl: 2 })"
              is-dark
            >
              <div slot="day-popover" slot-scope="{  dayTitle, attributes }">
                <div class="text-center">{{ dayTitle }}</div>
                <ul v-for="{key, customData} in attributes" :key="key">
                  <li v-for="user in customData" :key="user" class="marginZero"> 
                    
                   {{user}}  </li>
                </ul>
              </div>
              <!-- :attributes="attributes" -->
            </vc-date-picker>
            <p v-if="!dateIsChosen" class="error">Choose at least one date</p>
          </v-col>
          <v-col cols="12" sm="12" md="3" class="ma-2 border">
            <h5 class="mb-4 smoothcolor">Enter your name</h5>
            <v-text-field
              v-model="name"
              :counter="20"
              :rules="nameRules"
              label="Name"
              required
              outlined
              maxlength="20"
            ></v-text-field>
            <v-divider class="mt-10" horizontal></v-divider>

            <v-btn class="ma-10 pulse-button" color="teal" @click="validate">Submit my disponibility</v-btn>
            <v-divider class="mb-10" horizontal></v-divider>

            <v-expansion-panels fixed="true">
              <h5 class="ma-4 smoothcolor">Optional hour for selected days</h5>
              <v-expansion-panel v-for="day in selectedDays" :key="day.date">
                <v-expansion-panel-header>
                  <v-icon>mdi-clock</v-icon>
                  <v-spacer></v-spacer>
                  <div class="hour_panel">
                    <template>{{day.date}}</template>
                  </div>
                  <v-spacer></v-spacer>
                </v-expansion-panel-header>
                <v-expansion-panel-content class="pa-1">
                  <v-time-picker
                    v-model="day.hour"
                    label="Hours"
                    format="24hr"
                    outlined
                    type="time"
                  ></v-time-picker>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
        </v-row>
         </v-card>
      </v-form>
    </div>
    <div v-if="error">
      <p>{{errorMessage}}</p>
    </div>
  </v-container>
</template>
<script>
import { mapActions } from "vuex";
import axios from "axios";
import LoadingVue from "../components/Loading.vue"

export default {
  async created() {
    this.loading = true;
    this.data= false;
    console.log("created called.");

    this.guid = this.$route.params.guid;
    if (this.guid !== "0") {
      await axios({
        method: "get",
        url: "/api/getmeeting",
        params: { guid: this.$route.params.guid }
      })
        .then(response => {
          this.response = response.data;
          console.log(this.response);
          this.author = this.response.author;
          this.title = this.response.title;
          this.data = true;
          this.loading = false;
          this.error = false;
        })
        .catch(error => {
          this.error = true;
          this.data = false;
          this.loading = false;
          console.log(error.message);
          this.errorMessage = error.message;
        });
    } else {
      this.data = false;
      this.loading = true;
      this.error = false;
    }
  },components : {
    LoadingVue
  },
  data: () => ({
    author: "",
    rows: 1,
    full: true,
    autogrow: true,
    loading: false,
    data: null,
    error: null,
    errorMessage: null,
    dense: true,
    readonly: true,
    response: "",
    message: "",
    guid: 0,
    valid: false,
    dateIsChosen: true,
    name: "",
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 20) || "Name must be less than 10 characters",
      v => (v && v.length >= 2) || "Name must contain more than 2 characters"
    ],
    dates: [],
    selectedDays: [],
    selectedOpportunity: [],
    availableDates: [],
    title: "",
    datesRules: [v => !!v || "dates is required"]
  }),
  computed: {
    attributes() {
      if (this.data) {
        return [
          // Attributes for todos
          ...this.response.opportunitiesOutput.map(op => ({
            dates: op.date,
            highlight: {
              color: op.color
            },
            popover: {
              label: op.label,
              labelClass: "popover"
            },
            customData: op.label.split("|")
          }))
        ];
      } else {
        return [];
      }
    }
  },
  methods: {
    ...mapActions(["get"]),

    copy() {
      var testingCodeToCopy = document.querySelector("#meetingid");
      testingCodeToCopy.setAttribute("type", "text");
      testingCodeToCopy.select();

      try {
        var successful = document.execCommand("copy");
        var msg = successful ? "successfully" : "unsuccessfully";
        alert("Meeting code was copied " + msg);
      } catch (err) {
        alert("Oops, unable to copy");
      }
    },
    dayClicked(day) {
      if (!day.isDisabled) {
        this.selectedDay = this.createOpportunity(day);
        if (this.selectedDays.length < 5) {
          this.addOrDelete(this.selectedDay, true);
        } else if (this.selectedDays.length === 5) {
          this.addOrDelete(this.selectedDay, false);
        }
        this.availability(day);
      }
    },
    createData: vm => ({
      guid: vm.guid,
      name: vm.name,
      opportunities: vm.selectedDays
    }),
    async update(meeting) {
      await axios({
        method: "post",
        url: "/api/update",
        data: meeting
      })
        .then(this.$router.go())
        .catch(error => {
          console.log(error);
          this.$router.push(`/GetEvent/error`);
        });
    },
    async validate() {
      if (this.$refs.form.validate() && this.selectedDays.length != 0) {
        var response = await this.update(this.createData(this));
        console.log("update " + response);
        this.$router.push(`/GetEvent/${response}`);
      } else {
        this.valid = false;
        this.dateIsChosen = true;
        if (this.selectedDays.length == 0) {
          this.dateIsChosen = false;
        }
      }
    },
    createOpportunity: day => ({
      date: day.id,
      hour: ""
    }),
    addOrDelete(day, couldAdd) {
      for (let dday of this.selectedDays) {
        if (dday.date == day.date) {
          this.selectedDays.splice(this.selectedDays.indexOf(dday), 1);
          couldAdd = false;
        }
      }
      if (couldAdd) {
        this.selectedDays.push(day);
      }
    },
    availability() {
      if (this.selectedDays.length < 5) {
        this.availableDates = [];
      } else {
        for (let dday of this.selectedDays) {
          this.availableDates.push(dday.date);
        }
      }
    }
  }
};
</script>
<style scoped>
@import url(https://fonts.googleapis.com/css?family=Merienda+One);
@import url(https://fonts.googleapis.com/css?family=Oswald:400|Open+Sans:700);
@import url(http://weloveiconfonts.com/api/?family=fontawesome);
 
.loadContainer {
  justify-content: center;
}
.border {
  border: solid 1px black;
  min-width: 350px;
}

.code {
  border: solid 1px black;
  min-width: 380px;
}
.smoothcolor {
  color: #aab41b;
  font-family: 'Merienda one';
  font-size: 1.4em
}
.green {
  background-color: #4fc08d;
}
.daypicker {
  font-size: 4em;
}
.margintopneg {
  margin-top: -10px;
}
.pulse-button {
  position: relative;

  border: none;
  box-shadow: 0 0 0 0 lightyellow;
  background-color: black;
  background-size: cover;
  background-repeat: no-repeat;
  z-index: 10;
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
.popover {
  color: red;
}
@-webkit-keyframes pulse {
  to {
    box-shadow: 0 0 0 20px rgba(232, 76, 61, 0);
  }
}
@-moz-keyframes pulse {
  to {
    box-shadow: 0 0 0 20px rgba(232, 76, 61, 0);
  }
}
@-ms-keyframes pulse {
  to {
    box-shadow: 0 0 0 20px rgba(232, 76, 61, 0);
  }
}
@keyframes pulse {
  to {
    box-shadow: 0 0 0 20px rgba(232, 76, 61, 0);
  }
}
.marginZero{
  margin: 0;
  text-align: left;
}
.head{
  background :  linear-gradient(to right , #095049, #101F1D);
}
.head-title {
  font-family: 'Roboto';
  color: white;
  opacity: 88%;

}
.code{
  width: 330px;

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
  margin-top: -55px;
  
}

h1, h3 {
  transform: matrix(1, -0.20, 0, 1, 0, 0);
  -ms-transform: matrix(1, -0.20, 0, 1, 0, 0);
  -webkit-transform: matrix(1, -0.20, 0, 1, 0, 0);
  margin-bottom: 150px;
  margin-right: 100px;
}

h1 {
  font-family: "Oswald", Sans-serif;
  text-transform: uppercase;
  font-weight: 400;
  font-size: 70px;
  text-shadow: 4px 5px #4DBA87, 6px 7px #c6a39a;
  opacity: 87%;
  margin-top: -30px;
  
}
.cool {
   font-family: "Oswald", Sans-serif;
  text-transform: uppercase;
  font-weight: 200;
  font-size: 35px;
  text-shadow: 2px 3px #4DBA87, 3px 4px #c6a39a;
  opacity: 87%;
  
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
  opacity: 87%
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

.secondtitle{
margin-top: -100px;
}
</style>