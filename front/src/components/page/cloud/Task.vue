<template>
    <div>
        <span>{{userId}}</span><br>
        <span v-for="item in userInfo">
            <span>{{item.name}}</span><br>
        </span>

        <div class="qrcode" ref="qrCodeUrl"></div>
    </div>
</template>

<script>
    import QRCode from 'qrcodejs2'
    export default {
        name: "Task",
        data() {
            return {
                userInfo: [],
                userId: null
            }
        },
        mounted(){
            new QRCode(this.$refs.qrCodeUrl, {
                text: 'https://www.qtshe.com',
                width: 100,
                height: 100,
                colorDark: '#000000',
                colorLight: '#ffffff',
                correctLevel: QRCode.CorrectLevel.H
            })
        },
        created() {
            this.$http.apiAddress().then(res => {
                console.log(res.data);
                this.userInfo=res.data.list;
            },error=>{
                console.log(error);
            });


        }
    }
</script>

<style scoped>

</style>