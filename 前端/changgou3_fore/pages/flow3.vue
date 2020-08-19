<template>
  <div>
    <!-- 顶部导航 start -->
    <TopNav></TopNav>
    <!-- 顶部导航 end -->

    <div style="clear:both;"></div>

    <!-- 页面头部 start -->
    <div class="header w990 bc mt15">
      <div class="logo w990">
        <h2 class="fl">
          <a href="index.html">
            <img src="images/logo.png" alt="畅购商城" />
          </a>
        </h2>
        <div class="flow fr flow3">
          <ul>
            <li>1.我的购物车</li>
            <li>2.填写核对订单信息</li>
            <li class="cur">3.成功提交订单</li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 页面头部 end -->

    <div style="clear:both;"></div>

    <!-- 主体部分 start -->
    <div class="success w990 bc mt15">
      <div class="success_hd">
        <h2>订单提交成功</h2>
      </div>
      <div class="success_bd">
        <p>
          <span></span>订单提交成功，我们将及时为您处理
        </p>

        	<p>
                <div id="qrcode" style="width:256px;margin:0 auto;"></div>
            </p>
        <p class="message">
          完成支付后，你可以
          <a href>查看订单状态</a>
          <a href>继续购物</a>
          <a href>问题反馈</a>
        </p>
      </div>
    </div>
    <!-- 主体部分 end -->

    <div style="clear:both;"></div>
    <!-- 底部版权 start -->
    <Footer></Footer>
    <!-- 底部版权 end -->
  </div>
</template>

<script>
import TopNav from "../components/TopNav";
import Footer from "../components/Footer";
export default {
  head: {
    title: "支付页面",
    link: [{ rel: "stylesheet", href: "/style/success.css" }],
    script: [{ type: "text/javascript", src: "/js/qrcode.min.js" },]
  },
  components: {
    TopNav,
    Footer
  },
  data() {
    return {
      sn: this.$route.query.sn
    };
  },
  async mounted() {
 
    let { data } = await this.$request.pay({ "sn": this.sn });
    new QRCode(document.getElementById("qrcode"), data.other.wxurl);
  },
  methods: {
    async payQuery(){
    let {data} = await this.$REQuest.payQuery(this.sn)
    if(data.code==1){
    location.href='flow4'
    }else{
      alert(data.message)
    }
    },
       autoChange() {
      //1 与RabbitMQ建立会话
      var client = Stomp.client('ws://localhost:15674/ws');

      //关闭调试
      client.debug = false

      //2
      //2.1 成功回调函数
      var on_connect = (x) => {
        //订阅：RabbitMQ 消息有多种类型：queue 队列
        id = client.subscribe("/queue/order_pay_auto", (d) => {
          let sn = d.body
          if(sn == this.sn ){
            //跳转页面
            location.href='/flow4'
          }
        });
      };

      //2.2 失败回调函数
      var on_error =  function() {
        console.log('error');
      };

      //2.3 获得连接 ， client.connect('用户名', '密码', 成功回调函数, 失败回调函数, host);
      client.connect('guest', 'guest', on_connect, on_error, '/');

    }
  },
};
</script>

<style>
#qrcode img {
  background-color: #fff;
  padding: 6px;
}
</style>