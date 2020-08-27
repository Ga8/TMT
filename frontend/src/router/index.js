import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export const router = new VueRouter({
  mode: 'hash',
  base: "./",
  routes: [
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/',
    name: 'home',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue')
  },
  {
    path: '/get',
    name: 'get',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/GetEvent.vue')
  },
  {
    path: '/getEvent/:guid',
    name: 'getevent',
    component: () => import(/* webpackChunkName: "about" */ '../views/GetEvent.vue'),
    props: true,
    
  },
  {
    path: '/create',
    name: 'create',
    component: () => import(/* webpackChunkName: "about" */ '../views/Create.vue'),
    
  }

]
})

export default router
