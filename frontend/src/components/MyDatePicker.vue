<template>
  <div class="about">
    <h1 class="title">{{title}}</h1>
    <v-row justify="center">
      <div class="datePickerDiv rowDiv">
        <vc-date-picker
          mode="multiple"
          v-model="dates"
          is-inline
          :attributes="attributes"
          :available-dates="availableDates"
          :columns="$screens({ default: 1, laptop: 2 })"
          :rows="$screens({ default: 1, laptop: 2 })"
          :is-expanded="layout.isExpanded"
          popover-visibility="focus"
          color="teal"
          @dayclick="dayClicked"
        ></vc-date-picker>
      </div>
      <div class="centerDiv rowDiv"></div>
      <div class="d-lg-inline-block ma-8 rowDiv">
        <v-expansion-panels fixed="true">
          <h3>Hours for selected days</h3>
          <v-expansion-panel v-for="day in selectedDays" :key="day.key">
            <v-expansion-panel-header>
              <template>
                {{day}}
                <v-icon>mdi-clock</v-icon>
              </template>
            </v-expansion-panel-header>
            <v-expansion-panel-content class="pa-1">
              <v-text-field label="Hours" value="18:00:00" type="time"></v-text-field>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </div>
    </v-row>
    <div></div>
  </div>
</template>
<script>
import { mapGetters, mapState } from "vuex";

export default {
  data() {
    return {
      dates: [],
      selectedDays: [],
      availableDates: []
    };
  },
  computed: {
    ...mapState(["title"]),

    ...mapGetters(["attributes"])
  },
  methods: {
    dayClicked(day) {
      if (!day.isDisabled) {
        this.selectedDay = day;

        if (this.selectedDays.length <= 3) {
          this.selectedDays.indexOf(day.id) === -1
            ? this.selectedDays.push(day.id)
            : this.selectedDays.splice(this.selectedDays.indexOf(day.id), 1);
          this.availableDates = [];
        } else if (this.selectedDays.length === 4) {
          if (this.selectedDays.indexOf(day.id) === -1) {
            this.selectedDays.push(day.id);
            this.availableDates = this.selectedDays;
          } else {
            this.selectedDays.splice(this.selectedDays.indexOf(day.id), 1);
            this.availableDates = [];
          }
        } else if (this.selectedDays.length === 5) {
          if (this.selectedDays.indexOf(day.id) !== -1) {
            this.selectedDays.splice(this.selectedDays.indexOf(day.id), 1);
            this.availableDates = [];
          } else {
            this.availableDates = this.selectedDays;
          }
        } else {
          console.log("else");
          if (this.selectedDays.indexOf(day.id) !== -1) {
            this.selectedDays.splice(this.selectedDays.indexOf(day.id), 1);
            this.availableDates = [];
          }
          this.availableDates = this.selectedDays;
        }
      }
    }
  }
};
</script>
<style>
.custom {
  display: inline;
  max-width: 250px;
  min-width: 250px;
}
.rowDiv {
  width: 30%;
  margin: 5%;
}

.title {
  margin: 3%;
}
.createButton {
  background-color: #4fc08d !important;
  font-family: sans-serif;
  font-weight: bold;
}
</style>