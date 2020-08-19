//state区域，相当于定义变量
export const state = () => ({
    user: null,
    keyword: null
})

//mutations区域，用于修改数据
export const mutations = {
    setData(state, obj) {
        state[obj.key] = obj.value
    }
}
