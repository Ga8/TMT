<template>
  <div>
    <v-row justify="center">
      <div v-if="loading">
        <v-progress-circular indeterminate color="teal" size="128" width="68"></v-progress-circular>
      </div>

      <div v-if="data" class="code ma-2">
        <v-textarea label="Meeting Code" outlined readonly rows="1" :value="guid"></v-textarea>
        <v-btn color="#4fc08d" @click="copy">Copy!</v-btn>
      </div>
    </v-row>
    <div v-if="data">
      <v-form ref="form" lazy-validation>
        <v-row justify="space-around">
          <v-col cols="6" md="6" class="ma-4 border">
            <h3 class="ma-2">Choose yours disponibilities (max : 5)</h3>
            <p v-if="!dateIsChosen" class="error">Choose at least one date</p>

            <vc-date-picker
              mode="multiple"
              v-model="dates"
              is-inline
              :available-dates="availableDates"
              columns="$screens({ lg: 2 }, 1)"
              :rows="1"
              is-expanded
              popover-visibility="focus"
              color="teal"
              @dayclick="dayClicked"
              :rules="datesRules"
              :attributes="attributes"
              class="daypicker"
            >
              <!-- :attributes="attributes" -->
            </vc-date-picker>
            <p v-if="!dateIsChosen" class="error">Choose at least one date</p>
          </v-col>
          <v-col cols="2" sm="3" md="3" class="ma-2 border">
            <h3 class="ma-3">{{response.title}}</h3>
            <h3 class="ma-3">Your name</h3>
            <v-text-field
              v-model="name"
              :counter="20"
              :rules="nameRules"
              label="Name"
              required
              outlined
              maxlength="20"
            ></v-text-field>
            <v-btn class="ma-2 createButton" @click="validate">Submit my disponibility</v-btn>
          </v-col>
          <v-col cols="2" md="2" class="ma-2 border">
            <v-expansion-panels fixed="true">
              <h3>Hours for selected days</h3>
              <v-expansion-panel v-for="day in selectedDays" :key="day.date">
                <v-expansion-panel-header>
                  <v-row>
                    <v-col cols="1">
                      <v-icon>mdi-clock</v-icon>
                    </v-col>
                    <v-col cols="2"></v-col>
                    <v-col cols="7">
                      <div class="hour_panel">
                        <template>{{day.date}}</template>
                      </div>
                    </v-col>
                  </v-row>
                </v-expansion-panel-header>
                <v-expansion-panel-content class="pa-1">
                  <v-text-field v-model="day.hour" label="Hours" outlined type="time"></v-text-field>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
        </v-row>
      </v-form>
    </div>
    <div v-if="error">
      <p>{{errorMessage}}</p>
    </div>
  </div>
</template>
<script>
import { mapActions } from "vuex";
import axios from "axios";

export default {
  async created() {
    console.log("created called.");

    this.guid = this.$route.params.guid;

    await axios({
      method: "get",
      url: "/api/getmeeting",
      params: { guid: this.$route.params.guid }
    })
      .then(response => {
        this.response = response.data;
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
  },
  data: () => ({
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
    titleRules: [
      v => !!v || "Title is required",
      v => (v && v.length <= 20) || "Title must be less than 20 characters",
      v => (v && v.length >= 2) || "Title must contain more than 2 characters"
    ],
    datesRules: [v => !!v || "dates is required"]
  }),
  computed: {
    attributes() {
      if (this.data) {
        return [
          // Attributes for todos
          ...this.response.opportunitiesOutput.map(op => ({
            dates: op.date,
            dot: {
              color: op.color
            },
            popover: {
              label: op.label
            }
          }))
        ];
      } else {
        return [];
      }
    }
  },
  methods: {
    ...mapActions(["update"]),
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
      title: vm.title,
      name: vm.name,
      opportunities: vm.selectedDays
    }),
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
.border {
  border: solid 1px black;
}

.code {
  border: solid 1px black;
  min-width: 380px;
}

.green {
  background-color: #4fc08d;
}
.daypicker{
  font-size: 4em;
}
</style>