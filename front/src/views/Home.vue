<template>
  <div class="home">
      <el-container>
          <el-header>
              footer
          </el-header>
        <el-main>
            <el-aside>
                <MenuList></MenuList>
            </el-aside>
          <el-main>
              <router-view></router-view>
          </el-main>
          <el-footer>
             <Footer></Footer>
          </el-footer>
        </el-main>
      </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import MenuList from '@/components/MenuList.vue'
import Footer from '@/components/Footer.vue'
export default {
  name: 'home',
  components: {
    MenuList, Footer
  }
}
</script>
