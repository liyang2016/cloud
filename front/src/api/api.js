import axios from '../utils/axios'


export function fetch({url, params, method}) {
    return new Promise((resolve, reject) => {
        axios({
            url: url,
            method: method,
            data: params
        }).then(response => {
            resolve(response);
        }).catch((error) => {
            console.log(error)
            reject(error)
        })

    })
}

export const apiAddress = (params) => fetch({
    url: "/components/tableData",
    params: params,
    method: "post"
});

export const getUserInfo = (params) => fetch({
    url: "/components/userInfo",
    params: params,
    method: "get"
});