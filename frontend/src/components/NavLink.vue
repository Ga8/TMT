
<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="navmenuopen" app clipped>
      <v-list dense>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-home-city</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <a>
              <router-link tag="span" :to="{name: 'home'}"><span class="title-home" >Home</span></router-link>
            </a>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-calendar</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <a>
              <router-link tag="span" :to="{name: 'create'}"><span class="title-home" >Create an Event</span></router-link>
            </a>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-calendar-alert</v-icon>
          </v-list-item-action>
          <v-list-item-content @click="openclose"><a><span class="title-home" >Get an event</span></a></v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-account-group-outline</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <a>
              <router-link tag="span" :to="{name: 'about'}"><span class="title-home" >About</span></router-link>
            </a>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app clipped-left>
      <v-app-bar-nav-icon color="teal" @click.stop="navmenu"></v-app-bar-nav-icon>
      <v-toolbar-title><span class="title-home" > Take My Time </span></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon color="teal" @click="openclose">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-app-bar>

    <v-content>
      <v-container class="fill-height" fluid>
        <router-view />
      </v-container>
    </v-content>

    <v-bottom-sheet v-model="sheet">
      <v-sheet class="text-center" height="5%" justify="justify-center">
        <v-btn class="mt-6 button-foot" color="error" @click="openclose">close</v-btn>
        <div class="input">
          <v-text-field
            class="input"
            label="Event Code"
            placeholder="Put your event code here."
            outlined
            v-model="code"
            maxlength="50"
            maxwidth="350px"
          >Put your event code inside</v-text-field>
        </div>
        <v-btn type="button" class="success ma-2 button-foot" @click="getEvent">Get the event</v-btn>
      </v-sheet>
    </v-bottom-sheet>

    <v-footer app height=30>
            <div class="footer"> &copy; {{ new Date().getFullYear() }} Made with
          <v-icon color="teal">mdi-cards-heart</v-icon> by Gaëtan Guibaud</div>
    </v-footer>
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
    append: false
  }),
  computed: {},
  methods: {
    getEvent() {
      if (this.code != null && this.code != "") {
        router
          .push({ name: "getevent", params: { guid: this.code } })
          .catch((error) => console.log(error.message));

        this.sheet = false;
      } else {
        if (this.$route.params.guid != this.code) {
          alert(this.$route.params.guid + " : " + this.code);
          alert("Put a valid code to retrieve an event.");
        } else {
          alert("You already on this event");
        }
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
@import url(https://fonts.googleapis.com/css?family=Merienda+One);
@import url(https://fonts.googleapis.com/css?family=Roboto);

a {
  text-decoration: none;
  text-decoration-color: none;
  text-align: left;
  color: white !important;
}
li {
  text-decoration: none;
}
.title-home{
  font-family: 'Merienda One', sans-serif;
}
.card {
  height: 100%;
}
.footer {
  right: 10px;
  position: absolute;
}

.vuecolor {
  color: teal;
  font-weight: bold;
}
.button-foot{
  
  font-family: 'Roboto', sans-serif ;
}
.input {
  margin-left: 40%;
  margin-right: 40%;
  margin-top: 2%;
}
</style>
