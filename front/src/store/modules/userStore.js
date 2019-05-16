import types from '../../utils/mutation-types'

const state = {
    userInfo: null
};

const getters = {
    userInfo: (state) => {
        return state.userInfo
    }
};

const actions = {

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


