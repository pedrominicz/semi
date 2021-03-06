import Vue from 'vue'
import VueRouter from 'vue-router'

import Category from '../views/Category.vue'
import Error from '../views/Error.vue'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Post from '../views/Post.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Home
  },
  {
    path: '/user/:user',
    component: Home,
    props: true
  },
  {
    path: '/user/:user/category/:category',
    component: Home,
    props: true
  },
  {
    path: '/category/:category',
    component: Home,
    props: true
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Login
  },
  {
    path: '/post',
    component: Post
  },
  {
    path: '/category',
    component: Category
  },
  {
    path: '/error/:message',
    component: Error,
    props: true
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
