import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: undefined,
    user: undefined
  },
  getters: {
    isLogged (state) {
      return !!state.token
    },
    isAdmin (state) {
      return state.user && state.user.admin
    }
  },
  mutations: {
    setToken (state, token) {
      state.token = token
    },
    setUser (state, user) {
      state.user = user
    }
  },
  actions: {
    logout (context) {
      context.commit('setToken', undefined)
      context.commit('setUser', undefined)
    }
  },
  modules: {
  }
})
