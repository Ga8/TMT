<template>
  <div class="container">
    <div v-bind:class="flipped ? 'flip-container flipped': 'flip-container'">
      <div class="flipper">
        <div class="front">
          <div>
            <v-card class="mx-auto" max-width="100%" tile>
              <v-img height="100%" src="../assets/front.jpg">
                <v-row class="fill-height">
                  <div class="text-center" justify="center">
                  <v-col align-self="center" class="pa-0" cols="12">
                    <v-avatar class="profile" size="165" color="grey"  tile>
                      <v-img src="../assets/alice-cat.jpg"></v-img>
                    </v-avatar>
                 
                  <div>
                    <p class="title">Guibaud Gaëtan</p>
                    <p class="margin-top-neg"> French Software Engineer</p>
                  </div>
                    <div>
                      <v-chip class="ma-2" color="indigo" text-color="white">
                        <v-avatar left>
                          <v-icon>mdi-language-java</v-icon>
                        </v-avatar>Java
                      </v-chip>
                      <v-chip class="ma-2" color="rgb(10,46,40)" text-color="white">
                        <v-avatar text-color="white" left tile>
                          <v-icon>mdi-vuejs</v-icon>
                        </v-avatar>Vue.js
                      </v-chip>
                      <v-chip class="ma-2" color="green darken-4" text-color="white">
                        <v-avatar left class="green darken-4">
                          <v-icon>mdi-leaf</v-icon>
                        </v-avatar>Springboot
                      </v-chip>
                      <v-chip class="ma-2" color="blue darken-4" text-color="white">
                        <v-avatar left color="blue darken-4">
                          <v-icon>mdi-vuetify</v-icon>
                        </v-avatar>Vuetify
                      </v-chip>
                    </div>
                    <div class="ma-4">
                      <v-icon
                        class="pulse-button"
                        v-on:click="flipped=true"
                        x-large
                        color="white"
                      >mdi-swap-horizontal-bold</v-icon>
                    </div>
                   </v-col>
                  </div>
                </v-row>
              </v-img>
            </v-card>
          </div>
        </div>
        <div class="back">
          <v-img height="100%" src="../assets/front.jpg">
            <v-row align="end" class="fill-height">
              <div class="parentback">
                <v-col align-self="center" class="pa-0" cols="12">
                  <div>
                    <p class="ma-4">You get a bug?</p>
                    <p class="ma-4">You want an improvement for this web application?</p>
                    <p class="ma-4">You are an awesome enterprise based in New Zealand, you like my work and want employ me?</p>
                    <p class="ma-4">Feel free contacting me thanks to </p>
                   
                      <v-chip
                        class="ma-2"
                        color="rgb(0,119,181)"
                        text-color="white"
                        href="https://www.linkedin.com/in/ga%C3%ABtan-guibaud-bb7518120/"
                        target="_blank"
                      >
                        <v-avatar left class="rgb(0,119,181)">
                          <v-icon>mdi-linkedin</v-icon>
                        </v-avatar>Linkedin
                      </v-chip>
                    <p>              
                      <v-chip class="ma-2" color="black" text-color="white">
                        <v-avatar left class="black">
                          <v-icon>mdi-email</v-icon>
                        </v-avatar>Ga8.code@live.fr
                      </v-chip>
                    </p>
                  </div>
                  <div class="ma-4 returnposition">
                    <v-icon
                      class="pulse-button"
                      v-on:click="flipped=false"
                      x-large
                      color="white"
                    >mdi-swap-horizontal-bold</v-icon>
                  </div>
                </v-col>
              </div>
            </v-row>
          </v-img>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FlipCard",
  data: function() {
    return {
      flipped: false
    };
  },
  methods: {}
};
</script>

<style type='text/css' scoped>
i.frontFlipBtn,
i.backFlipBtn {
  position: absolute;
  right: 10px;
  top: 10px;
}
i.backFlipBtn {
  -webkit-transform: rotateY(-180deg);
  -moz-transform: rotateY(-180deg);
  -o-transform: rotateY(-180deg);
  -ms-transform: rotateY(-180deg);
  transform: rotateY(-180deg);
}
.container {
  width: 40%;
  margin: auto;
}

.flip-container {
  -webkit-perspective: 1000;
  -moz-perspective: 1000;
  -o-perspective: 1000;
  perspective: 1000;
}
.flip-container {
  min-height: 120px;
  min-width: 300px;
}
.flipper {
  -moz-transform: perspective(1000px);
  -moz-transform-style: preserve-3d;
  position: relative;
}
.front,
.back {
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -o-backface-visibility: hidden;
  backface-visibility: hidden;
  -webkit-transition: 0.6s;
  -webkit-transform-style: preserve-3d;
  -moz-transition: 0.6s;
  -moz-transform-style: preserve-3d;
  -o-transition: 0.6s;
  -o-transform-style: preserve-3d;
  -ms-transition: 0.6s;
  -ms-transform-style: preserve-3d;
  transition: 0.6s;
  transform-style: preserve-3d;
  top: 0;
  left: 0;
  width: 100%;
}
.back {
  -webkit-transform: rotateY(-180deg);
  -moz-transform: rotateY(-180deg);
  -o-transform: rotateY(-180deg);
  -ms-transform: rotateY(-180deg);
  transform: rotateY(-180deg);
  position: absolute;
}
.flip-container.flipped .back {
  -webkit-transform: rotateY(0deg);
  -moz-transform: rotateY(0deg);
  -o-transform: rotateY(0deg);
  -ms-transform: rotateY(0deg);
  transform: rotateY(0deg);
}
.flip-container.flipped .front {
  -webkit-transform: rotateY(180deg);
  -moz-transform: rotateY(180deg);
  -o-transform: rotateY(180deg);
  -ms-transform: rotateY(180deg);
  transform: rotateY(180deg);
}
.front {
  z-index: 2;
}
.pulse-button {
  position: relative;
  width: 60px;
  height: 60px;
  border: none;
  box-shadow: 0 0 0 0 lightyellow;
  border-radius: 50%;
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
.parentback {
  width: 100%;
}
.margin-top-neg{
  margin-top: -15px;
}
.bold {
  font-weight: bold;
}

.text-center{
  width: 100%;
  margin: 1%;
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