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

export default axios;