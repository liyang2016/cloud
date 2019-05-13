//timestamp转日期时间
export function timestampToFullDate(timestamp) {
  if(timestamp){
    let newDate = new Date(timestamp);
    let dateString = newDate.toLocaleDateString();
    let time = newDate.toTimeString();
    let index_ = time.indexOf(' ');
    let allTimeString = time.substr(0,index_);
    return dateString+' '+allTimeString;
  }
  return '';
}

//timestamp转中文时间
export function datestampToChinaString(datestamp) {
  if(typeof datestamp !== 'string'){
    let date_string = new Date(datestamp).toLocaleDateString();
    let date_array = date_string.split('/');
    if(date_array[1] && date_array[1].length<2){
      date_array[1] = '0'+ date_array[1];
    }
    if(date_array[2] && date_array[2].length<2){
      date_array[2] = '0'+ date_array[2];
    }
    date_string = date_array[0]+'年'+date_array[1]+'月'+date_array[2]+'日';
    return date_string;
  }
  return datestamp;
}

//date 转字符串  	yyyy-MM-dd HH:mm:ss
export function dateToStringYYYYMMDDHHMMSS(date) {
 let dateTime = date.getFullYear() + '-' + ((date.getMonth() + 1)< 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1)) + '-' + (date.getDate()< 10 ? '0' + date.getDate() : date.getDate()) + ' '
  + (date.getHours()< 10 ? '0' + date.getHours() : date.getHours()) + ':' + (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())+ ':' + (date.getSeconds()< 10 ? '0' + date.getSeconds() : date.getSeconds());
  return dateTime;
}

//date 转字符串 yyyy-MM-dd
export function dateToStringYYYYMMDD(date) {
  let dateTime = date.getFullYear() + '-' + ((date.getMonth() + 1)< 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1)) + '-' + (date.getDate()< 10 ? '0' + date.getDate() : date.getDate());
  return dateTime;
}
//date 转字符串 yyyyMMddhhss
export function formatDateTime(date){
  let y = date.getFullYear();
  let m = date.getMonth() + 1;
  m = m < 10 ? ('0' + m) : m;
  let d = date.getDate();
  d = d < 10 ? ('0' + d) : d;
  let h = date.getHours();
  let minute = date.getMinutes();
  minute = minute < 10 ? ('0' + minute) : minute;
  return y+m+d+h+minute;
};
