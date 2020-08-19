<template>
  <div>
    <TopNav></TopNav>
    <div style="clear:both;"></div>
    <HeaderLogo></HeaderLogo>
    <div style="clear:both;"></div>
    <!-- 正文 -->
    <!-- 登录表单 -->
    <div class="login w990 bc mt10">
      <div class="login_hd">
        <h2>用户登录</h2>
        <b></b>
      </div>
      <div class="login_bd">
        <div class="login_form fl">
          <form action method="post">
            <ul>
              <li>
                <label for>用户名：</label>
                <input type="text" class="txt" name="username" v-model="user.username" />
              </li>
              <li>
                <label for>密码：</label>
                <input type="password" class="txt" name="password" v-model="user.password" />
                <a href>忘记密码?</a>
              </li>
              <li class="checkcode">
                <label for>验证码：</label>
                <input type="text" name="checkcode" v-model="user.code" />
                <img :src="imgSrc" alt />
                <span>
                  看不清？
                  <a href @click.prevent="changeVerifyCode">换一张</a>
                </span>
              </li>
              <li v-if="errorMsg!=''">
                <label for>&nbsp;</label>
                <span style="color:#ff5b5b">{{errorMsg}}</span>
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="checkbox" class="chb" /> 保存登录信息
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="button" @click.prevent="login" value class="login_btn" />
              </li>
            </ul>
          </form>

          <div class="coagent mt15">
            <dl>
              <dt>使用合作网站登录商城：</dt>
              <dd class="qq">
                <a href>
                  <span></span>QQ
                </a>
              </dd>
              <dd class="weibo">
                <a href>
                  <span></span>新浪微博
                </a>
              </dd>
              <dd class="yi">
                <a href>
                  <span></span>网易
                </a>
              </dd>
              <dd class="renren">
                <a href>
                  <span></span>人人
                </a>
              </dd>
              <dd class="qihu">
                <a href>
                  <span></span>奇虎360
                </a>
              </dd>
              <dd class>
                <a href>
                  <span></span>百度
                </a>
              </dd>
              <dd class="douban">
                <a href>
                  <span></span>豆瓣
                </a>
              </dd>
            </dl>
          </div>
        </div>

        <div class="guide fl">
          <h3>还不是商城用户</h3>
          <p>现在免费注册成为商城用户，便能立刻享受便宜又放心的购物乐趣，心动不如行动，赶紧加入吧!</p>

          <a href="regist.html" class="reg_btn">免费注册 >></a>
        </div>
      </div>
    </div>
    <!-- end -->
    <div style="clear:both;"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import TopNav from "../components/TopNav";
import HeaderLogo from "../components/HeaderLogo";
import Footer from "../components/Footer";
export default {
  head: {
    title: "用户登录",
    link: [{ rel: "stylesheet", href: "style/login.css" }],
    script: []
  },
  components: {
    TopNav,
    HeaderLogo,
    Footer
  },
  data() {
    return {
      imgSrc: "",
      errorMsg: "",
      user: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    changeVerifyCode() {
      //判断必须要输入用户名
      if (this.user.username) {
        //切换图片路径
        this.imgSrc = `http://localhost:10010/v3/cgwebservice/verifycode?t=${new Date()}&username=${
          this.user.username
        }`;
      } else {
        this.errorMsg = "用户名不能为空";
      }
    },
    async login() {
      let { data } = await this.$request.login(this.user);
      if (data.code == 1) {
        //成功
        sessionStorage.setItem("user", JSON.stringify(data.other.login));
        //保存token
        sessionStorage.setItem("token", data.other.token);
        let returnURL = localStorage.getItem("returnURL");
        if (returnURL) {
          location.href = returnURL;
        } else {
          //默认：跳转首页
          location.href = "/";
        }
      }
    }
  },
  watch: {
    user: {
      handler(v) {
        if (v) {
          //如果user数据发生改变，修改提示信息
          this.errorMsg = "";
        }
      },
      deep: true
    }
  }
};
</script>

<style>
</style>