import Vue from 'vue';
import Vuex from 'vuex';
import userStore from './modules/userStore'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        count: 0
    },
    modules: {
        userStore
    },
    mutations: {
        increment (state) {
            state.count++
        }
    }
})

export default store