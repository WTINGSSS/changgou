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
        <div class="flow fr">
          <ul>
            <li class="cur">1.我的购物车</li>
            <li>2.填写核对订单信息</li>
            <li>3.成功提交订单</li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 页面头部 end -->

    <div style="clear:both;"></div>

    <!-- 主体部分 start -->
    <div class="mycart w990 mt10 bc">
      <h2>
        <span>我的购物车</span>
      </h2>
      <table>
        <thead>
          <tr>
            <th class="col0">
              <input type="checkbox" :checked="selectAllChecked" @click="selectAll($event)" name id />
            </th>
            <th class="col1">商品名称</th>
            <th class="col2">商品信息</th>
            <th class="col3">单价</th>
            <th class="col4">数量</th>
            <th class="col5">小计</th>
            <th class="col6">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(goods,index) in cart" :key="index">
            <th class="col0">
              <input type="checkbox" v-model="goods.checked" name id />
            </th>
            <td class="col1">
              <a href>
                <img :src="goods.midlogo" alt />
              </a>
              <strong>
                <a href>{{goods.goods_name}}</a>
              </strong>
            </td>
            <td class="col2">
              <p
                v-for="(value,key,index) in goods.spec_info_id_txt"
                :key="index"
              >{{key}} : {{value}}</p>
            </td>
            <td class="col3">
              ￥
              <span>{{goods.price.toFixed(2)}}</span>
            </td>
            <td class="col4">
              <a href="javascript:;" @click.prevent="minus(goods)" class="reduce_num"></a>
              <input
                type="text"
                name="amount"
                value="1"
                v-model="goods.count"
                class="amount"
                @keyup="updateCount(goods,$event)"
              />
              <a href="javascript:;" @click.prevent="plus(goods)" class="add_num"></a>
            </td>
            <td class="col5">
              ￥
              <span>{{(goods.price*goods.count).toFixed(2)}}</span>
            </td>
            <td class="col6">
              <a href @click.prevent="del(index)">删除</a>
            </td>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <td colspan="7">
              购物金额总计：
              <strong>
                ￥
                <span id="total">{{totalPrice}}</span>
              </strong>
            </td>
          </tr>
        </tfoot>
      </table>
      <div class="cart_btn w990 bc mt10">
        <a href class="continue">继续购物</a>
        <a class="checkout" @click.prevent="submit">结 算</a>
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
    title: "购物车页面",
    link: [{ rel: "stylesheet", href: "/style/cart.css" }],
    script: [{ type: "text/javascript", src: "/js/cart1.js" }]
  },
  components: {
    TopNav,
    Footer
  },
  data() {
    return {
      cart: [],
      selectAllChecked: false
    };
  },
  async mounted() {
    //获取token
    let token = sessionStorage.getItem("token");
    if (token) {
      //登录：服务器获得数据
      let { data } = await this.$request.getCart();
      this.cart = data.data;
      console.info(this.cart);
    } else {
      let cartStr = localStorage.getItem("cart");

      this.cart = JSON.parse(cartStr);
    }
  },
  methods: {
    minus: function(goods) {
      if (goods.count > 1) {
        goods.count--;
      }
    },
    plus: function(goods) {
      //可以考虑库存
      goods.count++;
    },
    updateCount: function(goods, e) {
      console.info(e.target.value);
      if (/^\d+$/.test(e.target.value)) {
        goods.count = e.target.value;
      } else {
        goods.count = 1;
      }
    },
    del(index) {
      if (confirm("您确定要删除么?")) {
        this.cart.splice(index, 1);
        console.info(this.cart);
      }
    },
    selectAll(e) {
      this.cart.forEach(item => {
        item.checked = e.target.checked;
      });
    },
    submit() {
      let token = sessionStorage.getItem("token");
      if (token) {
        this.$router.push("flow2");
      } else {
        //确定登录成功后调整的页面
        localStorage.setItem("returnURL", "flow2");
        //没有登录
        this.$router.push("login");
      }
    }
  },
  computed: {
    totalPrice() {
      let sum = 0;
      this.cart.forEach(g => {
        sum += g.price * g.count;
      });
      return sum;
    }
  },
  watch: {
    //深度监听
    cart: {
      async handler(newCart, oldCart) {
        //根据登录情况，更新购物车
        let token = sessionStorage.getItem("token");
        if (token) {
          //登录
          let { data } = await this.$request.updateCart(newCart);
          if (data.code == 0) {
            alert(data.message);
          }
        } else {
          //未登录--将购物车中的数据保持localStorage中
          localStorage.setItem("cart", JSON.stringify(newCart));
        }

        //购物项项选中个数，购物车个数相同，全选需要选中
        let cartSize = this.cart.length;
        let checkedSize = this.cart.filter(item => item.checked).length;
        this.selectAllChecked = cartSize == checkedSize;
      },
      //true代表如果在 watch 里声明了之后，就会立即先去执行里面的handler方法
      immediate: false,
      //深度监听，常用于对象下面属性的改变
      deep: true
    }
  }
};
</script>

<style>
</style>