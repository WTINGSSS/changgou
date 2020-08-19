<template>
  <div>
    <TopNav></TopNav>
    <div style="clear:both;"></div>
    <HeaderLogo></HeaderLogo>
    <div style="clear:both;"></div>
    <!-- 正文 -->
    <!-- 注册内容start -->
    <div class="login w990 bc mt10 regist">
      <div class="login_hd">
        <h2>用户注册</h2>
        <b></b>
      </div>
      <div class="login_bd">
        <div class="login_form fl">
          <form action method="post">
            <ul>
              <li>
                <label for>用户名：</label>
                <input
                  type="text"
                  class="txt"
                  v-model="user.username"
                  name="username"
                  @blur="checkUsername"
                />
                <p>3-20位字符，可由中文、字母、数字和下划线组成</p>
                <p :class="userMsg.username.code==1?'success':'error'">{{userMsg.username.message}}</p>
              </li>
              <li>
                <label for>密码：</label>
                <input
                  type="password"
                  class="txt"
                  name="password"
                  v-model="user.p1"
                  @blur="checkpassword1"
                />
                <p>6-20位字符，可使用字母、数字和符号的组合，不建议使用纯数字、纯字母、纯符号</p>
                <p v-if="userMsg.password1.code==0" class="error">{{userMsg.password1.message}}</p>
              </li>
              <li>
                <label for>确认密码：</label>
                <input
                  type="password"
                  class="txt"
                  name="password"
                  v-model="user.p2"
                  @blur="checkpassword2"
                />
                <p>请再次输入密码</p>
                <p v-if="userMsg.password2.code==0" class="error">{{userMsg.password2.message}}</p>
              </li>
              <li>
                <label for>手机号码：</label>
                <input
                  type="text"
                  class="txt"
                  name="mobile"
                  v-model="user.mobile"
                  @blur="checkmobile"
                />
                <p>请输入手机号码</p>
                <p v-if="userMsg.mobile.code==1" class="success">{{userMsg.mobile.message}}</p>
                <p v-else class="error">{{userMsg.mobile.message}}</p>
              </li>
              <li class="checkcode">
                <label for>验证码：</label>
                <input type="text" name="checkcode" v-model="user.code" />
                <!-- prevent阻止事件冒泡行为 -->
                <button :disabled="btnDisabled" @click.prevent="sendSms">
                  发送验证码
                  <span v-show="btnDisabled">{{senconds}}秒</span>
                </button>
                <span :class="userMsg.smsData.code==1?'success':'error'">{{userMsg.smsData.message}}</span>
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="checkbox" class="chb" checked="checked" /> 我已阅读并同意《用户注册协议》
              </li>
              <li>
                <label for>&nbsp;</label>
                <input type="submit" value @click.prevent="register" class="login_btn" />
              </li>
            </ul>
          </form>
        </div>
        <div class="mobile fl">
          <h3>手机快速注册</h3>
          <p>
            中国大陆手机用户，编辑短信 “
            <strong>XX</strong>”发送到：
          </p>
          <p>
            <strong>1069099988</strong>
          </p>
        </div>
      </div>
    </div>
    <!-- 注册footer -->
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
    title: "注册页面",
    link: [{ rel: "stylesheet", href: "style/login.css" }],
    script: [
      { type: "text/javascript", src: "js/header.js" },
      { type: "text/javascript", src: "js/index.js" }
    ]
  },
  components: {
    TopNav,
    HeaderLogo,
    Footer
  },
  data() {
    return {
      user: {
        mobile: "",
        p1: "",
        p2: ""
      },
      userMsg: {
        //错误提示数据
        smsData: "",
        username: {},
        mobile: {},
        password1: {},
        password2: {}
      },
      btnDisabled: false, //倒计时控制变量
      senconds: 30, //默认倒计时数
      timer: null //接收定时器,清除定时器
    };
  },
  methods: {
    //校验用户名
    async checkUsername() {
      if (this.user.username == null) {
        this.userMsg.username = {
          code: 0,
          message: "用户名不能为空"
        };
        return;
      }
      let { data } = await this.$request.checkUsername(this.user.username);
      this.userMsg.username = data;
    },
    //校验手机号
    async checkmobile() {
      console.info(this.user);
      let { data } = await this.$request.checkmobile(this.user);
      this.userMsg.mobile = data;
    },
    //校验密码
    checkpassword1() {
      if (this.user.p1 == "") {
        this.userMsg.password1 = {
          code: 0,
          message: "密码不能为空"
        };
      }
    },
    //校验确认密码
    checkpassword2() {
      if (this.user.p2 == "") {
        this.userMsg.password2 = {
          code: 0,
          message: "确认密码不能为空"
        };
      } else if (this.user.p2 != this.user.p1) {
        this.userMsg.password2 = {
          code: 0,
          message: "两次输入密码不一致"
        };
      }
      this.user.password = this.user.p2;
    },
    async sendSms() {
      //简单校验
      if (!this.user.username) {
        this.userMsg.username = {
          code: 0,
          message: "用户名不能为空"
        };
        return;
      }
      if (!this.user.mobile) {
        this.userMsg.mobile = {
          code: 0,
          message: "手机号不能为空"
        };
        return;
      }

      //倒计时
      //按钮状态变为不可用
      this.btnDisabled = true;
      this.timer = setInterval(() => {
        //小于1重置
        if (this.senconds <= 1) {
          //按钮回到可用状态
          this.btnDisabled = false;
          //关闭定时器
          clearInterval(this.timer);
        } else {
          this.senconds--;
        }
      }, 1000);

      //继续发送
      let { data } = await this.$request.sendSms(this.user);
      if (data.code == 1) {
        //发送成功
        alert(data.message);
      } else {
        //错误信息
        this.userMsg.code = data.message;
      }
    },
    async register() {
      let { data } = await this.$request.register(this.user);
      if (data.code == 1) {
        this.$router.push("/login");
      } else {
        this.userMsg.smsData = data;
      }
    }
  }
};
</script>

<style>
</style>