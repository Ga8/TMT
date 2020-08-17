
<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="navmenuopen" app clipped>
      <v-list dense>
        <v-list-item link v-for="item in items" :key="item.title" :to="item.path">
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>{{ item.title }}</v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app clipped-left>
      <v-app-bar-nav-icon @click.stop="navmenu"></v-app-bar-nav-icon>
      <v-toolbar-title>Take My Time</v-toolbar-title>
      <v-spacer></v-spacer>

      <v-btn icon @click="openclose">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-app-bar>

    <v-content>
      <v-container class="fill-height" fluid>
        <router-view />
      </v-container>
    </v-content>

    <v-footer app>
      <div class="footer">&copy; {{ new Date().getFullYear() }} Guibaud Gaëtan</div>
    </v-footer>
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
  </v-app>
</template>
<script>
import { router } from "../router/index";

export default {
  data: () => ({
    sheet: false,
    navmenuopen: false,
    id: 0,
    code: "",
    drawer: null,
    append: false,
    items: [
      { title: "Home", icon: "mdi-home-city", path: "home" },
      { title: "Create an Event", icon: "mdi-calendar", path: "create" },
      { title: "Get an Event", icon: "mdi-calendar-alert", path: "getEvent" },
      { title: "About", icon: "mdi-account-group-outline", path: "about" }
    ]
  }),
  computed: {},
  methods: {
    getEvent() {
      if (
        this.code != null &&
        this.code != "" &&
        this.$route.params.guid != this.code
      ) {
        this.navmenuopen = false;

        router
          .push({ name: "get-event", params: { guid: this.code } })
          .catch(error => console.log(error.message));

        router.go();
        this.sheet = false;
      } else {
        alert("Put a valid code to retrieve an event.");
      }
    },
    openclose() {
      if (this.sheet) {
        this.sheet = false;
      } else {
        this.sheet = true;
      }
    },
    navmenu() {
      if (this.navmenuopen) {
        this.navmenuopen = false;
      } else {
        this.navmenuopen = true;
      }
    }
  },
  props: {
    source: String
  },
  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>
<style>
.card {
  height: 100%;
}
.footer{
 right: 10px;
 position: absolute;
}
</style>
