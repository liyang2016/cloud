import types from '../../utils/mutation-types'
import Cookies from 'js-cookie';

const userInfo = Cookies.get('userInfo');

const state = {
    userInfo: Cookies.get('userInfo') ? JSON.parse(userInfo) : ''
};

const getters = {
    userInfo: (state) => {
        return state.userInfo
    }
};

const actions = {
    loginIn(context, userInfo) {
        context.commit(types.SET_USER_VO, userInfo);
    },

    loginOut(context) {
        context.commit(types.REMOVE_USER_VO);
    }
};

const mutations = {
    [types.SET_USER_VO](state, data) {
        Cookies.set('userInfo', JSON.stringify(data));
        Object.assign(state.userInfo, data);
    },

    [types.REMOVE_USER_VO](state) {
        Cookies.remove('userInfo');
        state.userInfo = null;
    }

};

export default {
    state,
    getters,
    actions,
    mutations
}


