import axios from '../utils/axiosUtil'


export function fetch(url, params) {
    return new Promise((resolve, reject) => {
        axios.get(url, params)
            .then(response => {
                resolve(response.data);
            })
            .catch((error) => {
                console.log(error)
                reject(error)
            })
    })
}

export default {
    // 获取页面的后台数据
    getUserInfo(cb){
        setTimeout(() => cb(fetch('/fuse/userInfo')), 100)
    }
}
