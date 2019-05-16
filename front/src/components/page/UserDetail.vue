<template>
    <div>
        <span>{{userId}}</span><br>
        <span v-for="item in userInfo">
            <span>{{item.name}}</span><br>
        </span>
    </div>
</template>

<script>
    export default {
        name: "UserDetail",
        data() {
            return {
                userInfo: [],
                userId: null
            }
        },
        created() {
            this.$http.apiAddress().then(res => {
                console.log(res.data);
                this.userInfo=res.data.list;
            },error=>{
                console.log(error);
            });

            this.$http.getUserInfo().then(res=>{
               console.log(res.data);
               this.userId=res.data.data.userSn;
            },error=>{
                console.log(error);
            });
        }
    }
</script>

<style scoped>

</style>