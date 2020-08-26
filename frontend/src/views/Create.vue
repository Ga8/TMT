<template>
  <v-flex>
    <v-container fluid>
      <v-form ref="form" lazy-validation>
        <TitleVue title="CREATE AN EVENT"  @click="validate" :numerateur="numerateur" :denominateur="denominateur" />

        <v-row justify="space-around">

          <!-- first  box -->
          <v-col sm="12" md="4">
            <div class="ma-3">
              <div align="center">
                <v-card max-width="800" class="mx-auto">
                  <v-toolbar class="head" color="teal" dark>
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
                  <v-btn class="ma-2 pulse-button" color=teal >Create an event</v-btn>
                </v-card>
              </div>

              <!-- optional hours card -->
              <v-card max-width="800" class="mx-auto mt-8">
                <v-toolbar  class="head" color="teal" dark>
                  <v-toolbar-title class="title" justify="center"> Hours for selected days</v-toolbar-title>

                  <v-tooltip right>
                    <template v-slot:activator="{ on, attrs }">
                      <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
                    </template>
                    <span>
                      The Hours are optional 
                    </span>
                  </v-tooltip>
                </v-toolbar>
                    <v-expansion-panels fixed="true">
                        <v-expansion-panel v-for="day in selectedDays" :key="day.date">
                          <v-expansion-panel-header>
                            <v-icon>mdi-clock</v-icon>
                            <div>
                              <template>{{day.date}}</template>
                            </div>
                          </v-expansion-panel-header>
                          <v-expansion-panel-content class="pa-1">
                            <v-text-field class="ma-auto hours" v-model="day.hour" label="Hours" outlined type="time"></v-text-field>
                          </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-expansion-panels>
              </v-card>
            </div>
          </v-col>
          <v-col sm="12" md="6" >


                <v-card max-width="800" class="mx-auto mt-4">
                <v-toolbar class="head" color="teal" dark>
                  <v-toolbar-title class="title" justify="center"> Choose disponibilities for your event</v-toolbar-title>

                  <v-tooltip right>
                    <template v-slot:activator="{ on, attrs }">
                      <v-icon v-bind="attrs" v-on="on">mdi-comment-question</v-icon>
                    </template>
                    <span>
                      You can choose 5 disponibilities
                    </span>
                  </v-tooltip>
                </v-toolbar>
            <!-- date picker -->
            <div align="center" class="picker ma-4 pb-4">
      
              <div v-if="!dateIsChosen" align="center" class="error">
                <p class="ma-0">
                  <v-icon>mdi-alert</v-icon>Choose at least one date
                  <v-icon>mdi-alert</v-icon>
                </p>
              </div>
              <vc-date-picker
                mode="multiple"
                is-expanded
                v-model="dates"
                is-inline
                :available-dates="availableDates"
                popover-visibility="focus"
                color="teal"
                @dayclick="dayClicked"
                :rules="validateDate"
                :columns="$screens({ default: 1, lg: 2 })"
                :rows="$screens({ default: 1, lg: 2 })"
                is-dark
              >
                <!-- :attributes="attributes" -->
              </vc-date-picker>
              <div v-if="!dateIsChosen" align="center" class="error">
                <p class="ma-0">
                  <v-icon>mdi-alert</v-icon>Choose at least one date
                  <v-icon>mdi-alert</v-icon>
                </p>
              </div>
            </div>
                </v-card>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
  </v-flex>
</template>
<script>
import axios from "axios";
import TitleVue from "../components/Title.vue";
import {TitleButtonMixin}  from "../mixins/TitleButtonMixin";

export default {
  mixins : [TitleButtonMixin],
  data: () => ({
    valid: true,
    dateIsChosen: true,
    denominateur : 4,
    selectedDays: [],
    selectedOpportunity: [],
    availableDates: []
   
  }),
  components: {
    TitleVue
  },
  computed: {
    // ...mapState(["title"]),
    //   ...mapGetters(["attributes"])
  },
  methods: {
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
      title: vm.title,
      author: {
        "name" : vm.name,
        "email" : vm.email
      },
      opportunities: vm.selectedDays
    }),
    async validate() {
      if (this.$refs.form.validate() && this.selectedDays.length != 0) {
        await this.addMeeting(this.createData(this));
      } else {
        this.valid = false;
        this.dateIsChosen = true;
        if (this.selectedDays.length == 0) {
          this.dateIsChosen = false;
        }
      }
    },
    async addMeeting(meeting) {
      await axios({
        method: "post",
        url: "/api/addmeeting",
        data: meeting
      }).then(response =>
        this.$router.push(`/GetEvent/${response.data}`).catch(error => {
          console.log(error);
          this.$router.push(`/GetEvent/error`);
        })
      );
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
<style>
@import url(https://fonts.googleapis.com/css?family=Lato:900);
.title {
  font-family: "Lato", sans-serif;
  font-size: 400;
  justify-content: center;
  align-content: center;
  align-self: center;
  text-align: center;
}
.maxwidth {
  max-width: 400px;
}
.border-white {
  border: white 1px solid;
}
.about {
  display: flex;
  justify-content: center;
}

.margin2 {
  margin: 2%;
}

.title {
  margin: 3%;
}

.daypicker {
  font-size: 4em;
}
.createButton {
  background-color: #4fc08d !important;
  font-family: sans-serif;
  font-weight: bold;
}
.fill {
  font-family: sans-serif;
  color: rgb(65, 184, 131);
  text-decoration: underline;
}

.checklabel {
  margin-top: 20px;
  margin-left: 5px;
}

.hours{
  max-width: 250px;
}
.head{
  background :  linear-gradient(to right ,#051422,#075D63)
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
</style>