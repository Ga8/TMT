<template>
  <v-container fluid>
    <v-form ref="form" lazy-validation>
      <v-row justify="space-around">
        <v-col sm="12" md="6" class="ma-2">
          <div align="left" class="picker ma-4">
            <h2 class=" ma-4 fill">Fill the form to create an event</h2>
            <h3 class="ma-4">Choose a date for your event (max : 5)</h3>
            <div v-if="!dateIsChosen" align="center" class="error">
            <p class="ma-0" >  <v-icon>mdi-alert</v-icon> Choose at least one date  <v-icon>mdi-alert</v-icon> </p>
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
              :rules="datesRules"
              :columns="$screens({ default: 1, lg: 2 })"
              :rows="$screens({ default: 1, lg: 2 })"
            >
              <!-- :attributes="attributes" -->
            </vc-date-picker>
               <div v-if="!dateIsChosen" align="center" class="error">
            <p class="ma-0" >  <v-icon>mdi-alert</v-icon> Choose at least one date  <v-icon>mdi-alert</v-icon> </p>
            </div>
          </div>
        </v-col>
        <v-col sm="12" md="4">
          <div class="ma-3">
            <div align="center">
              <h3 class="ma-3">Find an event title</h3>
              <v-text-field
                label="Title"
                v-model="title"
                :counter="20"
                :rules="titleRules"
                required
                outlined
                maxlength="20"
              ></v-text-field>
              <h3 class="ma-3">Indicate your name</h3>
              <v-text-field
                v-model="name"
                :counter="20"
                :rules="nameRules"
                label="Name"
                required
                outlined
                maxlength="20"
              ></v-text-field>
              <v-btn class="ma-2 createButton" @click="validate">Create an event</v-btn>
            </div>
            <div class="ma-4 hours" align="center" justify="center">
              <div class="maxwidth">
                <v-expansion-panels fixed="true">
                  <h3 class="ma-2">Hours for selected days (optional) </h3>
                  <v-expansion-panel v-for="day in selectedDays" :key="day.date">
                    <v-expansion-panel-header>
                      <v-icon>mdi-clock</v-icon>
                      <div>
                        <template>{{day.date}}</template>
                      </div>
                    </v-expansion-panel-header>
                    <v-expansion-panel-content class="pa-1">
                      <v-text-field 
                      v-model="day.hour" 
                      label="Hours" 
                      outlined type="time" 
                      ></v-text-field>
                    </v-expansion-panel-content>
                  </v-expansion-panel>
                </v-expansion-panels>
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>
<script>
import { mapActions } from "vuex";
//mport { mapGetters, mapState } from "vuex";

export default {
  data: () => ({
    valid: true,
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
    titleRules: [
      v => !!v || "Title is required",
      v => (v && v.length <= 20) || "Title must be less than 20 characters",
      v => (v && v.length >= 2) || "Title must contain more than 2 characters"
    ],
    datesRules: [v => !!v || "dates is required"]
  }),
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
      name: vm.name,
      opportunities: vm.selectedDays
    }),
    ...mapActions(["addMeeting"]),
    async validate() {
      if (this.$refs.form.validate() && this.selectedDays.length != 0) {
        var response = await this.addMeeting(this.createData(this));
        console.log(response);
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
<style>
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
  color: rgb(65,184,131);
  text-decoration: underline;
}

.checklabel{
  margin-top: 20px;
  margin-left: 5px;
}
</style>