import Vue from 'vue';
import Vuex from 'vuex';
import userStore from './modules/userStore'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {

    },
    modules: {
        userStore
    },
    mutations: {

    }
});

export default store