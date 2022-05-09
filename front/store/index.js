import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
	//数据源
	state: {
		historyLists: uni.getStorageSync("__history")||[]
	},
	mutations: {
		SET_HISTORY_LISTS(state, history) {
			state.historyLists = history
		},
		CLEAR_HISTORY(state){
			state.historyLists=[]
		}
	},
	actions: {
		set_history({commit, state},history) {
			let list=state.historyLists
			if (!list.includes(history)) {
				let i = list.indexOf(history);
				list.splice(i, 1);
				list.unshift(history)
			} else {
				list.unshift(history)
				uni.setStorageSync('__history',list)
			}
			commit('SET_HISTORY_LISTS',list)
		},
		clearHistory({commit}){
			uni.setStorageSync("__history",[])
			commit('CLEAR_HISTORY')
		}
	}
})

export default store
