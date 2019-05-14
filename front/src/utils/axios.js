import axios from "axios";
import {formatDateTime} from "./date";
// axios 配置
axios.defaults.timeout = 60000;
axios.defaults.withCredentials = true;
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
//http request 拦截器
axios.interceptors.request.use((config) => {
    let datetime = formatDateTime(new Date());
    let getConfigData = JSON.stringify(config.data);
    config.data={
        common:{
            opTime:datetime,
        },
        business:getConfigData,
    };
    return config;
}, error => {
    return Promise.reject(error);
});


// 响应拦截器
axios.interceptors.response.use(
    response => {
        if (response.status === 200) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(response);
        }
    },
    // 服务器状态码不是200的情况
    error => {
        if (error.response.status) {
            switch (error.response.status) {
                // 401: 未登录
                // 未登录则跳转登录页面，并携带当前页面的路径
                // 在登录成功后返回当前页面，这一步需要在登录页操作。
                case 401:
                    break;
                // 403 token过期
                // 登录过期对用户进行提示
                // 清除本地token和清空vuex中token对象
                // 跳转登录页面
                case 403:
                    // 清除token
                    break;
                // 404请求不存在
                case 404:
                    break;
                // 其他错误，直接抛出错误提示
                default:
            }
            return Promise.reject(error.response);
        }
    }
);

export default axios;