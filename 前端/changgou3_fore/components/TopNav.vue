<template>
  <!--
      配置顶部导航的组件 
      顶部导航 start
  -->
  <div class="topnav">
    <div class="topnav_bd w990 bc">
      <div class="topnav_left"></div>
      <div class="topnav_right fr">
        <ul>
          <li v-if="user!=null">
            您好，&nbsp;{{user.username}}&nbsp;欢迎来到畅购！
            <a href @click.prevent="logout">退出</a>
          </li>
          <li v-if="user!=null" class="line">|</li>
          <li v-if="user==null">
            [
            <a href="/login">登录</a>] [
            <a href="/register">免费注册</a>]
          </li>
          <li v-if="user==null" class="line">|</li>
          <li v-if="user!=null">我的订单</li>
          <li v-if="user!=null" class="line">|</li>
          <li>客户服务</li>
        </ul>
      </div>
    </div>
  </div>
  <!-- 顶部导航 end -->
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  mounted() {
    let userStr = sessionStorage.getItem("user");
    if (userStr) {
      // 将string数据转换object，并填充到vuex中
      this.setData({ key: "user", value: JSON.parse(userStr) });
    }
  },
  methods: {
    logout() {
      sessionStorage.removeItem("user");
      sessionStorage.removeItem("token");
      //设置vuex中的数据为空
      this.setData({ key: "user", value: null });
      this.$router.push("/");
    },
    ...mapMutations(["setData"])
  },
  computed: {
    ...mapState(["user"])
  }
};
</script>

<style>
</style>