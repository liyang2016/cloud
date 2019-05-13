import api from '../../api/api'
import types from '../../utils/mutation-types'

const state = {
    userInfo: null
};

const getters = {
    userInfo: (state, getters, rootState) => {
        return state.userInfo
    }
};

const actions = {
    getUserInfo({commit}) {
        api.getUserInfo(userInfo => commit(types.SET_USER_VO, userInfo))
    }
};

const mutations = {
    [types.SET_USER_VO](state, data) {
        state.userInfo = data
    }

};

export default {
    state,
    getters,
    actions,
    mutations
}


