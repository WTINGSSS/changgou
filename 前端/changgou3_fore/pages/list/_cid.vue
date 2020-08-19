<template>
  <div>
    <!-- 顶部导航 start -->
    <TopNav></TopNav>
    <!-- 顶部导航 end -->

    <div style="clear:both;"></div>

    <!-- 头部 start -->
    <HeaderSearch :isFirst="false" :list="clist"></HeaderSearch>
    <!-- 头部搜索 end -->

    <div style="clear:both;"></div>

    <!-- 列表主体 start -->
    <div class="list w1210 bc mt10">
      <!-- 面包屑导航 start -->
      <div class="breadcrumb">
        <h2>
          当前位置：
          <a href>首页</a> >
          <a href>电脑、办公</a>
        </h2>
      </div>
      <!-- 面包屑导航 end -->

      <!-- 左侧内容 start -->
      <div class="list_left fl mt10">
        <!-- 分类列表 start -->
        <div class="catlist">
          <h2>电脑、办公</h2>
          <div class="catlist_wrap">
            <div class="child">
              <h3 class="on">
                <b></b>电脑整机
              </h3>
              <ul>
                <li>
                  <a href>笔记本</a>
                </li>
                <li>
                  <a href>超极本</a>
                </li>
                <li>
                  <a href>平板电脑</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>电脑配件
              </h3>
              <ul class="none">
                <li>
                  <a href>CPU</a>
                </li>
                <li>
                  <a href>主板</a>
                </li>
                <li>
                  <a href>显卡</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>办公打印
              </h3>
              <ul class="none">
                <li>
                  <a href>打印机</a>
                </li>
                <li>
                  <a href>一体机</a>
                </li>
                <li>
                  <a href>投影机</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>网络产品
              </h3>
              <ul class="none">
                <li>
                  <a href>路由器</a>
                </li>
                <li>
                  <a href>网卡</a>
                </li>
                <li>
                  <a href>交换机</a>
                </li>
              </ul>
            </div>

            <div class="child">
              <h3>
                <b></b>外设产品
              </h3>
              <ul class="none">
                <li>
                  <a href>鼠标</a>
                </li>
                <li>
                  <a href>键盘</a>
                </li>
                <li>
                  <a href>U盘</a>
                </li>
              </ul>
            </div>
          </div>

          <div style="clear:both; height:1px;"></div>
        </div>
        <!-- 分类列表 end -->

        <div style="clear:both;"></div>

        <!-- 新品推荐 start -->
        <div class="newgoods leftbar mt10">
          <h2>
            <strong>新品推荐</strong>
          </h2>
          <div class="leftbar_wrap">
            <ul>
              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot1.jpg" alt />
                    </a>
                  </dt>
                  <dd>
                    <a href>美即流金丝语悦白美颜新年装4送3</a>
                  </dd>
                  <dd>
                    <strong>￥777.50</strong>
                  </dd>
                </dl>
              </li>

              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot2.jpg" alt />
                    </a>
                  </dt>
                  <dd>
                    <a href>领券满399减50 金斯利安多维片</a>
                  </dd>
                  <dd>
                    <strong>￥239.00</strong>
                  </dd>
                </dl>
              </li>

              <li class="last">
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot3.jpg" alt />
                    </a>
                  </dt>
                  <dd>
                    <a href>皮尔卡丹pierrecardin 男士长...</a>
                  </dd>
                  <dd>
                    <strong>￥1240.50</strong>
                  </dd>
                </dl>
              </li>
            </ul>
          </div>
        </div>
        <!-- 新品推荐 end -->

        <!--热销排行 start -->
        <div class="hotgoods leftbar mt10">
          <h2>
            <strong>热销排行榜</strong>
          </h2>
          <div class="leftbar_wrap">
            <ul>
              <li></li>
            </ul>
          </div>
        </div>
        <!--热销排行 end -->

        <!-- 最近浏览 start -->
        <div class="viewd leftbar mt10">
          <h2>
            <a href>清空</a>
            <strong>最近浏览过的商品</strong>
          </h2>
          <div class="leftbar_wrap">
            <dl>
              <dt>
                <a href>
                  <img src="/images/hpG4.jpg" alt />
                </a>
              </dt>
              <dd>
                <a href>惠普G4-1332TX 14英寸笔记...</a>
              </dd>
            </dl>

            <dl class="last">
              <dt>
                <a href>
                  <img src="/images/crazy4.jpg" alt />
                </a>
              </dt>
              <dd>
                <a href>直降200元！TCL正1.5匹空调</a>
              </dd>
            </dl>
          </div>
        </div>
        <!-- 最近浏览 end -->
      </div>
      <!-- 左侧内容 end -->

      <!-- 列表内容 start -->
      <div class="list_bd fl ml10 mt10">
        <!-- 热卖、促销 start -->
        <div class="list_top">
          <!-- 热卖推荐 start -->
          <div class="hotsale fl">
            <h2>
              <strong>
                <span class="none">热卖推荐</span>
              </strong>
            </h2>
            <ul>
              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/hpG4.jpg" alt />
                    </a>
                  </dt>
                  <dd class="name">
                    <a href>惠普G4-1332TX 14英寸笔记本电脑 （i5-2450M 2G 5</a>
                  </dd>
                  <dd class="price">
                    特价：
                    <strong>￥2999.00</strong>
                  </dd>
                  <dd class="buy">
                    <span>立即抢购</span>
                  </dd>
                </dl>
              </li>

              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/list_hot3.jpg" alt />
                    </a>
                  </dt>
                  <dd class="name">
                    <a href>ThinkPad E42014英寸笔记本电脑</a>
                  </dd>
                  <dd class="price">
                    特价：
                    <strong>￥4199.00</strong>
                  </dd>
                  <dd class="buy">
                    <span>立即抢购</span>
                  </dd>
                </dl>
              </li>

              <li>
                <dl>
                  <dt>
                    <a href>
                      <img src="/images/acer4739.jpg" alt />
                    </a>
                  </dt>
                  <dd class="name">
                    <a href>宏碁AS4739-382G32Mnkk 14英寸笔记本电脑</a>
                  </dd>
                  <dd class="price">
                    特价：
                    <strong>￥2799.00</strong>
                  </dd>
                  <dd class="buy">
                    <span>立即抢购</span>
                  </dd>
                </dl>
              </li>
            </ul>
          </div>
          <!-- 热卖推荐 end -->

          <!-- 促销活动 start -->
          <div class="promote fl">
            <h2>
              <strong>
                <span class="none">促销活动</span>
              </strong>
            </h2>
            <ul>
              <li>
                <b>.</b>
                <a href>DIY装机之向雷锋同志学习！</a>
              </li>
              <li>
                <b>.</b>
                <a href>京东宏碁联合促销送好礼！</a>
              </li>
              <li>
                <b>.</b>
                <a href>台式机笔记本三月巨惠！</a>
              </li>
              <li>
                <b>.</b>
                <a href>富勒A53g智能人手识别鼠标</a>
              </li>
              <li>
                <b>.</b>
                <a href>希捷硬盘白色情人节专场</a>
              </li>
            </ul>
          </div>
          <!-- 促销活动 end -->
        </div>
        <!-- 热卖、促销 end -->

        <div style="clear:both;"></div>

        <!-- 商品筛选 start -->
        <div class="filter mt10">
          <h2>
            <a href>重置筛选条件</a>
            <strong>商品筛选</strong>
          </h2>
          <div class="filter_wrap">
            <dl>
              <dt>品牌：</dt>
              <dd :class="{'cur' : searchMap.brandId == '' }">
                <a href>不限</a>
              </dd>
              <dd
                :class="{'cur' : searchMap.brandId == bl.id}"
                v-for="(bl,index) in blist"
                :key="index"
              >
                <a :href="bl.link" @click.prevent="brandSearch(bl.id)">{{bl.brandName}}</a>
              </dd>
            </dl>

            <!-- 规格列表 -->
            <dl v-for="(spec,specIndex) in specList" :key="specIndex">
              <dt>{{spec.specName}}：</dt>
              <dd class="cur">
                <a href @click.prevent="specSearch(spec.specName,null,$event)">不限</a>
              </dd>
              <dd
                :class="{'cur' : opt.selected }"
                v-for="(opt,optIndex) in spec.options"
                :key="optIndex"
              >
                <a
                  href
                  @click.prevent="specSearch(spec.specName ,opt.optionName ,$event )"
                >{{opt.optionName}}</a>
              </dd>
            </dl>
          </div>
        </div>
        <!-- 商品筛选 end -->

        <div style="clear:both;"></div>

        <!-- 排序 start -->
        <div class="sort mt10">
          <dl>
            <dt>排序：</dt>
            <dd :class="{'cur': searchMap.sortBy == 'xl'}">
              <a href @click.prevent="sortSearch('xl')">销量</a>
            </dd>
            <dd :class="{'cur': searchMap.sortBy == 'jg'}">
              <a href @click.prevent="sortSearch('jg')">
                价格
                <!-- 升序：价格由低到高 -->
                <span v-if="searchMap.sortBy == 'jg' && searchMap.sortWay=='asc'">↑</span>
                <!-- 降序：价格由高到低 -->
                <span v-if="searchMap.sortBy == 'jg' && searchMap.sortWay=='desc'">↓</span>
              </a>
            </dd>
            <dd :class="{'cur': searchMap.sortBy == 'pl'}">
              <a href @click.prevent="sortSearch('pl')">评论数</a>
            </dd>
            <dd :class="{'cur': searchMap.sortBy == 'sj'}">
              <a href @click.prevent="sortSearch('sj')">上架时间</a>
            </dd>价格：
            <input
              type="number"
              v-model="searchMap.minPrice"
              style="width:80px"
              placeholder="￥"
            /> -
            <input type="number" v-model="searchMap.maxPrice" style="width:80px" placeholder="￥" />
            <input type="submit" value="搜索" @click.prevent="searchList()" />
          </dl>
        </div>
        <!-- 排序 end -->

        <div style="clear:both;"></div>

        <!-- 商品列表 start-->
        <div class="goodslist mt10">
          <ul>
            <li v-for="(p,pi) in searchResult.list" :key="pi">
              <dl>
                <dt>
                  <a :href="'/Goods?id='+p.id">
                    <img :src="p.midlogo" alt />
                  </a>
                </dt>
                <dd style="height:40px;">
                  <a :href="'/Goods?id=' + p.id">{{p.goodsName}} {{p.id}}</a>
                </dd>
                <dd>
                  <strong>￥{{p.price}}</strong>
                </dd>
                <dd>
                  <a href>
                    <em>已有{{p.commentCount}}人评价</em>
                  </a>
                </dd>
              </dl>
            </li>
          </ul>
        </div>
        <!-- 商品列表 end-->

        <!-- 分页信息 start -->
        <pagination
          :total="searchResult.count"
          :page_size="searchMap.pageSize"
          @page_changed="pageChanged"
        ></pagination>
        <!-- 分页信息 end -->
      </div>
      <!-- 列表内容 end -->
    </div>
    <!-- 列表主体 end-->

    <div style="clear:both;"></div>
    <!-- 底部导航 start -->
    <BottomNav></BottomNav>
    <!-- 底部导航 end -->

    <div style="clear:both;"></div>
    <!-- 底部版权 start -->
    <footer></footer>
    <!-- 底部版权 end -->
  </div>
</template>

<script>
import TopNav from "../../components/TopNav";
import HeaderSearch from "../../components/HeaderSearch";
import BottomNav from "../../components/BottomNav";
import Footer from "../../components/Footer";
import Pagination from "~/components/Pagination.vue";
export default {
  head: {
    title: "搜索页",
    link: [
      { rel: "stylesheet", href: "/style/list.css" },
      { rel: "stylesheet", href: "/style/common.css" },
      { rel: "stylesheet", href: "/style/bottomnav.css" }
    ],
    script: [
      { type: "text/javascript", src: "/js/header.js" },
      { type: "text/javascript", src: "/js/list.js" }
    ]
  },
  components: {
    TopNav,
    HeaderSearch,
    BottomNav,
    Footer,
    Pagination
  },
  data() {
    return {
      searchMap: {
        //搜索条件
        brandId: "", //当前品牌id
        catId: "", //分类id
        pageNum: 1, //分页：
        pageSize: 4, //分页：
        specList: {}, //规格条件
        keyword: "",
        sortBy: "x1", //排序：销量
        sortWay: "asc", //排序：排序
        minPrice: "", //最低价格
        maxPrice: "" //最高价格
      },
      blist: [], //品牌
      specList: [], //规格
      clist: [],
      searchResult: {} //搜索结果
    };
  },
  async asyncData({ app }) {
    //多次请求（只有一次）
    let [{ data: categoryData }] = await Promise.all([
      app.$request.findClist()
    ]);
    //返回
    return {
      clist: categoryData.data
    };
  },

  async mounted() {
    //查询分类品牌
    this.searchMap.catId = this.$route.params.cid;
    let { data: brandData } = await this.$request.findBlist(
      this.searchMap.catId
    );
    this.blist = brandData.data;
    //查询指定分类所有规范
    let { data: specData } = await this.$request.findSpec(this.searchMap.catId);
    this.specList = specData.data;

    //查询所有
    this.searchList();
  },
  methods: {
    async searchList() {
      let { data } = await this.$request.search(this.searchMap);
      this.searchResult = data.data;
    },
    brandSearch(bid) {
      this.searchMap.brandId = bid;
      this.searchList();
    },
    specSearch(specName, optionName, e) {
      //参数1：specName 规格名称
      //参数2：optionName 规格选项的值
      //1) 处理条件：选择值，如果存在添加条件，如果不存在删除条件
      if (optionName) {
        //存在
        this.searchMap.specList[specName] = optionName;
      } else {
        //不存在
        delete this.searchMap.specList[specName];
      }
      this.searchList();
      //方式1：jquery代码 处理回显
      //获得事件源 a 标签，再获得父标签<dd>，再添加用时
      $(e.target)
        .parent()
        .addClass("cur");
      //获得 a 标签父元素的所有，将样式移除
      $(e.target)
        .parent()
        .siblings()
        .removeClass("cur");
    },
    sortSearch(sortBy) {
      if (this.searchMap.sortBy == sortBy) {
        //点击第二次切换排序方式
        this.searchMap.sortWay =
          this.searchMap.sortWay == "asc" ? "desc" : "asc";
      } else {
        //第一次默认升序
        this.searchMap.sortBy = sortBy;
        this.searchMap.sortWay = "asc";
      }
      this.searchList();
    },
    pageChanged: function(pageNum) {
      //根据第几页查询数据
      this.searchMap.pageNum = pageNum;
      //查询
      this.searchList();
    }
  },
  watch: {
    "$store.state.keyword": function(newValue, oldValue) {
      this.searchMap.keyword = newValue;
      this.searchList();
    }
  }
};
</script>

<style>
</style>