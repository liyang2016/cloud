const Mock = require('mockjs');
//格式： Mock.mock( url, post/get , 返回的数据)；
Mock.mock('/components/userInfo', 'get', require('./json/userInfo'));
Mock.mock('/components/tableData','post',require('./json/vuetable'));
