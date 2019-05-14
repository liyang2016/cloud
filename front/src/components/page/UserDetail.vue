<template>
    <div>
        <span>{{userInfo}}</span>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: "UserDetail",
        computed: mapState({
            userInfo: state => state.userStore.userInfo
        }),
        created() {
            //初始化加载用户信息
            this.$store.dispatch('getUserInfo').then(res => {
                console.log(res)
            })
        }
    }
</script>

<style scoped>

</style>