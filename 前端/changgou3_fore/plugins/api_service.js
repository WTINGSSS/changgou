//自定义函数
const request = {
    findNews: () => {
        return axios.get('/cgwebservice/news', {
            params: {
                pageNum: 1,
                pageSize: 8,
                sortWay: 'asc'
            }
        })
    },
    findClist: () => {
        return axios.get('/cgwebservice/category')
    },
    //商品详情
    getGoodsInfo:(skuId)=>{
        return axios.get('/cgwebservice/sku/goods/'+skuId)
    }
   
}

var axios = null
export default ({ $axios, redirect, process }, inject) => {


    //3） 保存内置的axios
    axios = $axios

    //4) 将自定义函数交于nuxt
    // 使用方式1：在vue中，this.$request.xxx()
    // 使用方式2：在nuxt的asyncData中，content.app.$request.xxx()

    inject('request', request)
}