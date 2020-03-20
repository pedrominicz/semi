import Vue from 'vue'
import VueRouter from 'vue-router'
import Read from '../views/Read.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Read
  },
  {
    path: '/user/:name',
    component: Read,
    props: (route) => ({ path: `user/${route.params.name}` })
  },
  {
    path: '/category/:name',
    component: Read,
    props: (route) => ({ path: `category/${route.params.name}` })
  },
  {
    path: '*',
    redirect: '/'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
