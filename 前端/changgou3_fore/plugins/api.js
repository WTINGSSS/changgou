//自定义函数
const request = {
    //checkUsername
    checkUsername: (username) => {
        return axios.post('/cgwebservice/user/checkusername', { username })
    },
    //checkmobile
    checkmobile: (user) => {
        return axios.post('/cgwebservice/user/checkmobile', user)
    },
    //发验证码
    sendSms: (user) => {
        return axios.post('/cgwebservice/sms', user)
    },
    register: (user) => {
        return axios.post('/cgwebservice/user/register', user)
    },
    login: (user) => {
        return axios.post('/cgauthservice/auth/login', user)
    },
    findBlist: (categoryId) => {
        return axios.get('/cgwebservice/brand/category/' + categoryId)
    },
    findSpec: (categoryId) => {
        return axios.get('/cgwebservice/specifications/category/' + categoryId)
    },
    //搜索
    search: (params) => {
        return axios.post('/cgsearchservice/sku/search', params)
    },
    //评论
    getComments: (spuid, pageSize, pageNum) => {
        return axios.get(`/cgwebservice/comments/spu/${spuid}`, {
            params: {
                pageSize: pageSize,
                pageNum: pageNum
            }
        })
    },
    //添加到购物车
    addToCart: (params) => {
        return axios.post("/gccartservice/carts", params)
    },
    getCart: () => {
        return axios.get('/gccartservice/carts')
    },
    updateCart: (params) => {
        return axios.put('/gccartservice/carts', params)
    },
    getAddress: () => {
        return axios.get('/cgorderservice/address')
    },
    addAddress: (params) => {
        return axios.post('/cgorderservice/address', params)
    },
    addOrder: (params) => {
        return axios.post('/cgorderservice/orders', params)
    },
    pay: (params) => {
        return axios.post("/cgorderservice/pay", params)
    },
    payQuery: (params) => {
        return axios.post("/cgorderservice/pay/${params}")
    }

}

var axios = null
export default ({ $axios }, inject) => {
    //参考 https://axios.nuxtjs.org/helpers
    let token = sessionStorage.getItem('token')
    if (token) {
        $axios.setToken(token)
    }

    //处理响应异常
    $axios.onError(error => {
        //token失效 服务器响应403
        if (error.response.status === 403) {
            redirect('/login')
            sessionStorage.removeItem('token')
        }
    })


    //3） 保存内置的axios
    axios = $axios

    //4) 将自定义函数交于nuxt
    // 使用方式1：在vue中，this.$request.xxx()
    // 使用方式2：在nuxt的asyncData中，content.app.$request.xxx()

    inject('request', request)
}