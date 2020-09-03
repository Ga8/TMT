<template>
  <v-container class="loadContainer">
    <v-row justify="center">
      <v-col cols="6" md="6">
        <loadingVue text="Loading" :loading="loading" />
      </v-col>
    </v-row>

    <!-- titre -->
    <div v-if="data" class="ma-2">
      <v-row justify="center">
        <v-col cols="12" md="12" sm="12">
          <h3>Welcome to the event</h3>
          <h1>
            <span class="fontawesome-star star"></span>
            <span>{{title}}</span>
            <span class="fontawesome-star star"></span>
          </h1>

          <h5 class="smoothcolor secondtitle">created by</h5>
          <h2 class="cool author">{{author}}</h2>
        </v-col>
      </v-row>
    </div>

    <!-- first brick -->
    <div v-if="data">
      <v-form ref="form" lazy-validation>
        <v-card max-width="80%" class="mx-auto">
          <v-toolbar class="head" color="teal" dark>
            <v-toolbar-title class="title">
              <span class="head-title">New participant informations</span>
            </v-toolbar-title>
            <v-tooltip right>
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
              </template>
              <span>
                If you didn't vote for the event date yet,
                <br />click on your disponibilities days
                <br />and indicate your name.
                <br />Then submit.
              </span>
            </v-tooltip>
            <v-spacer></v-spacer>
          </v-toolbar>

          <!-- dates pickers -->
          <v-row justify="space-around">
            <v-col cols="12" md="7" sm="12" class="ma-4 border">
              <h4 class="mb-4 head-title">Choose yours disponibilities (max : 5)</h4>
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
                :rules="validateDate"
                :columns="$screens({ default: 1, xl: 2 })"
                :rows="$screens({ default: 1, xl: 2 })"
                is-dark
              >
                <div slot="day-popover" slot-scope="{  dayTitle, attributes }">
                  <div class="poptitle">{{ dayTitle }}</div>
                  <ul v-for="{key, customData} in attributes" :key="key">
                    <li v-for="user in customData" :key="user" class="marginZero">{{user}}</li>
                  </ul>
                </div>
                <!-- :attributes="attributes" -->
              </vc-date-picker>
              <p v-if="!dateIsChosen" class="error">Choose at least one date</p>

              <v-divider class="my-10" horizontal></v-divider>

              <div id="underpickercontainer">
                <v-col cols="12" md="12" sm="12" class="ma-4 border">
                  <v-row>
                    <v-col cols="12" md="5" sm="5" class="ma-4 border">
                      <h5 class="ma-4 smoothcolor">Percentage of your guest disponibility</h5>
                      <v-divider horizontal class="ma-2" />
                      <div class="legend">
                        <v-chip class="ma-2" color="#008E58" />
                        <span>over 80%</span>
                        <br />
                        <v-chip class="ma-2" color="#C8EE37" />
                        <span>60%-80%</span>
                        <br />
                        <v-chip class="ma-2" color="#F7C940" />
                        <span>40-60%</span>
                        <br />
                        <v-chip class="ma-2" color="#EE1010" />
                        <span>under 40%</span>
                        <br />
                      </div>
                    </v-col>
                    <v-divider class="my-10" vertical></v-divider>
                    <v-col cols="12" md="5" sm="5" class="ma-4 border">
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
                            <v-dialog
                              ref="dialog"
                              v-model="modal2"
                              width="250px"
                            >
                              <template v-slot:activator="{ on }">
                                <v-text-field
                                  v-model="day.hour"
                                  label="CLick to pick an hour"
                                  readonly
                                  outlined
                                  v-on="on"
                                
                                ></v-text-field>
                              </template>
                              <v-time-picker v-if="modal2" v-model="day.hour"    color="teal" full-width>
                                <v-spacer></v-spacer>
                                <v-btn text color="primary" @click="modal2 = false">OK</v-btn>
                              </v-time-picker>
                            </v-dialog>
                          </v-expansion-panel-content>
                        </v-expansion-panel>
                      </v-expansion-panels>
                    </v-col>
                  </v-row>
                </v-col>
              </div>
            </v-col>
            <v-col cols="12" sm="12" md="3" class="ma-2 border">
              <!-- Button submit -->

              <h5 class="mb-4 smoothcolor">Enter your name</h5>
              <v-text-field
                v-model="name"
                class="mx-8"
                :counter="20"
                :rules="[validateName]"
                label="Name"
                required
                outlined
                maxlength="20"
              ></v-text-field>
              <h5 class="mb-4 smoothcolor">
                Enter your email
                <v-tooltip right>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
                  </template>
                  <span>
                    This email will allow to send you a mail
                    <br />
                    when {{author}} will choose the final date for this event
                  </span>
                </v-tooltip>
              </h5>
              <v-text-field
                v-model="email"
                :counter="50"
                class="mx-8"
                label="Email"
                outlined
                maxlength="50"
              ></v-text-field>

              <div class="ma-2"></div>
              <v-divider class="mb-10" horizontal></v-divider>
              <v-row justify="center">
                <v-progress-circular
                  :rotate="360"
                  :size="150"
                  :width="15"
                  :value="value"
                  color="teal"
                >
                  mandatory :
                  <br />
                  {{value*2 / 100}}/2
                </v-progress-circular>
                <div class="ma-2"></div>
                <v-progress-circular
                  :rotate="360"
                  :size="150"
                  :width="15"
                  :value="value"
                  color="#095049"
                >
                  options :
                  <br />
                  {{options}}/6
                </v-progress-circular>
              </v-row>
              <v-btn
                class="ma-10 pulse-button"
                color="teal"
                @click="validate"
                :disabled="value  < 100"
                v-bind:class="classObject"
              >Submit my disponibility</v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-form>
      <v-card max-width="80%" class="mx-auto mt-8">
        <v-toolbar class="head" color="teal" dark>
          <v-toolbar-title class="title">
            <span class="head-title">Host resouces</span>
          </v-toolbar-title>
          <v-tooltip right>
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
            </template>
            <span>The admin code allow owner to choose the event date and that send email to guest who advise their email adress.</span>
          </v-tooltip>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-col cols="12" md="5" sm="9" class="ma-4">
          <h5 class="mb-4 smoothcolor">Grant access to this event giving this code to yours guests:</h5>
          <v-text-field
            style="width: 350px; margin-bottom: -15px;"
            class="mx-auto"
            label="Meeting Code"
            outlined
            readonly
            :value="guid"
            id="meetingid"
          ></v-text-field>
          <v-btn color="#094E47" class="mb-4" @click="copy">Copy me</v-btn>
        </v-col>
      </v-card>
    </div>
    <div v-if="error">
      <p>{{errorMessage}}</p>
    </div>
  </v-container>
</template>
<script>
import { mapActions } from "vuex";
import axios from "axios";
import LoadingVue from "../components/Loading.vue";

export default {
  async created() {
    this.loading = true;
    this.data = false;
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
  },
  components: {
    LoadingVue
  },
  data: () => ({
    author: "",
    rows: 1,
    email: "",
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
    dates: [],
    selectedDays: [],
    selectedOpportunity: [],
    availableDates: [],
    title: "",
    emailValid: false,
    nameValid: false,
    dateValid: false,
    numerateur: 0,
    denominateur: 2,
    options: 0,
    modal2: false
  }),
  computed: {
    value() {
      return (100 * this.numerateur) / this.denominateur;
    },
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
            customData: op.label.split("|").filter(function(el) {
              return el != "";
            })
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
        this.validateDate();
      }
    },
    createData: vm => ({
      guid: vm.guid,
      name: vm.name,
      email: vm.email,
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
    },
    validateName() {
      this.nameValid = false;
      var result = this.required(this.name);
      console.log(result);
      if (result === true) {
        result = this.counter(this.name);
        if (result === true) {
          result = this.counterMin(this.name);
          if (result === true) {
            this.nameValid = true;
          }
        }
      }
      this.updateCount();
      return result;
    },
    validateDate() {
      this.dateValid = false;
      var result = "dates is required";

      result = this.selectedDays.length != 0;

      if (result === true) {
        this.dateValid = true;
      }
      this.updateCount();
      return result;
    },
    validateEmail() {
      const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      var result = "Invalid e-mail.";
      this.emailValid = false;

      result = pattern.test(this.email);

      if (result === true) {
        this.emailValid = true;
      }

      return result;
    },
    required(value) {
      return !!value || "Required.";
    },
    counter(value) {
      return value.length <= 20 || "Max 20 characters";
    },
    counterMin(value) {
      return value.length >= 2 || "Min 2 characters";
    },
    updateCount() {
      this.numerateur = 0;
      var arr = [this.emailValid, this.nameValid, this.dateValid];
      arr.forEach(val => {
        if (val) {
          this.numerateur++;
        }
      });
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
  color: #ffffff;
  font-family: "Merienda one";
  font-size: 1.4em;
  opacity: 60%;
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
  z-index: 1;
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
  background-color: red;
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
.marginZero {
  margin: 0;
  text-align: left;
}
.head {
  background: linear-gradient(to right, #095049, #101f1d);
}
.head-title {
  font-family: "Roboto";
  color: white;
  opacity: 88%;
}
.code {
  width: 330px;
}
.poptitle {
  color: teal;
}

/* fontawesome */
[class*="fontawesome-"]:before {
  font-family: "FontAwesome", sans-serif;
}

section.text {
  position: absolute;
  width: 100%;
  min-width: 800px;
  text-align: center;
  top: 50%;
  margin-top: -55px;
}

h1,
h3 {
  transform: matrix(1, -0.2, 0, 1, 0, 0);
  -ms-transform: matrix(1, -0.2, 0, 1, 0, 0);
  -webkit-transform: matrix(1, -0.2, 0, 1, 0, 0);
  margin-bottom: 150px;
  margin-right: 100px;
}

h1 {
  font-family: "Oswald", Sans-serif;
  text-transform: uppercase;
  font-weight: 400;
  font-size: 70px;
  text-shadow: 4px 5px #4dba87, 6px 7px #c6a39a;
  opacity: 87%;
  margin-top: -30px;
}
.cool {
  font-family: "Oswald", Sans-serif;
  text-transform: uppercase;
  font-weight: 200;
  font-size: 35px;
  text-shadow: 2px 3px #4dba87, 3px 4px #c6a39a;
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
  opacity: 87%;
}

h3:before,
h3:after {
  content: " ";
  position: absolute;
  width: 100px;
  height: 8px;
  border-top: 2px solid #4dba87;
  border-bottom: 2px solid rgb(39, 117, 108);
}

h3:before {
  margin: 5px 0 0 -110px;
}

h3:after {
  margin: 5px 0 0 10px;
}

.secondtitle {
  margin-top: -150px;
  margin-left: 210px;
}
.author {
  margin-left: 300px;
  margin-bottom: 50px;
}

/* under date picker*/
.legend {
  min-width: 250px;
  text-align: start;
  margin-left: 25%;
}
.timePicker {
  width: 125px;
 
}
</style>