import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: undefined
  },
  getters: {
    isLogged (state) {
      return !!state.token
    }
  },
  mutations: {
    setToken (state, token) {
      state.token = token
    }
  },
  actions: {
    logout (context) {
      context.commit('setToken', undefined)
      return false
    }
  },
  modules: {
  }
})
