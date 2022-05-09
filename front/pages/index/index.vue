<template>
	<view class="index">
		<view class="wrap">
			<u-back-top :scroll-top="scrollTop" :iconStyle="iconStyle"></u-back-top>
		</view>
		<view class="center-search-container">
			<view class="center-search-bar">
				<view class="nav-searchform">
					<view class="nav-search-content">
						<input class="nav-search-input" v-model="keyword" type="text" placeholder="请输入"
							@focus="showPanel" @confirm="keySearch(keyword)" @input="onKeyYHKNameInput" />
						<view class="nav-search-clean" v-if="keyword" @click="clear()">
							<svg width="26" height="26" viewBox="0 0 16 16" fill="none"
								xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" clip-rule="evenodd"
									d="M8 14.75C11.7279 14.75 14.75 11.7279 14.75 8C14.75 4.27208 11.7279 1.25 8 1.25C4.27208 1.25 1.25 4.27208 1.25 8C1.25 11.7279 4.27208 14.75 8 14.75ZM9.64999 5.64303C9.84525 5.44777 10.1618 5.44777 10.3571 5.64303C10.5524 5.83829 10.5524 6.15487 10.3571 6.35014L8.70718 8.00005L10.3571 9.64997C10.5524 9.84523 10.5524 10.1618 10.3571 10.3571C10.1618 10.5523 9.84525 10.5523 9.64999 10.3571L8.00007 8.70716L6.35016 10.3571C6.15489 10.5523 5.83831 10.5523 5.64305 10.3571C5.44779 10.1618 5.44779 9.84523 5.64305 9.64997L7.29296 8.00005L5.64305 6.35014C5.44779 6.15487 5.44779 5.83829 5.64305 5.64303C5.83831 5.44777 6.15489 5.44777 6.35016 5.64303L8.00007 7.29294L9.64999 5.64303Z"
									fill="#C9CCD0"></path>
							</svg>
						</view>
					</view>
					<view class="nav-search-btn" @click="keySearch(keyword)">
						<svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" clip-rule="evenodd"
								d="M16.3451 15.2003C16.6377 15.4915 16.4752 15.772 16.1934 16.0632C16.15 16.1279 16.0958 16.1818 16.0525 16.2249C15.7707 16.473 15.4456 16.624 15.1854 16.3652L11.6848 12.8815C10.4709 13.8198 8.97529 14.3267 7.44714 14.3267C3.62134 14.3267 0.5 11.2314 0.5 7.41337C0.5 3.60616 3.6105 0.5 7.44714 0.5C11.2729 0.5 14.3943 3.59538 14.3943 7.41337C14.3943 8.98802 13.8524 10.5087 12.8661 11.7383L16.3451 15.2003ZM2.13647 7.4026C2.13647 10.3146 4.52083 12.6766 7.43624 12.6766C10.3517 12.6766 12.736 10.3146 12.736 7.4026C12.736 4.49058 10.3517 2.1286 7.43624 2.1286C4.50999 2.1286 2.13647 4.50136 2.13647 7.4026Z"
								fill="currentColor"></path>
						</svg>
					</view>
				</view>
				<view v-show="!isShow&&user!=null" class="search-panel">
					<view class="trending" v-if="searchHistoryList.length>=1">
						<view class="header">
							<view class="search_title">搜索历史</view>
							<view class="iconfont icon-bin" style="font-size: 38rpx; color:#BBBBBB;"
								@click="clearHistory()"></view>
						</view>
						<scroll-view scroll-y="true">
							<view class="trendings-single">
								<view class="trendings-item">
									<view class="history_view" v-for="(item,index) in searchHistoryList" :key="index"
										@click="itemSearch(item)">
										{{item}}
									</view>
								</view>
							</view>
						</scroll-view>
					</view>
					<view v-else class="no-data">暂无搜索历史记录！</view>
				</view>
			</view>
		</view>
		<view class="attention" style="margin-top: 80rpx;">
			<view class="item">
				<view class="iconfont icon-jinrong" style="font-size: 52rpx;color: #007AFF;"></view>
				<view class="item_name">金融</view>
			</view>
			<view class="item">
				<view class="iconfont icon-wuli" style="font-size: 62rpx;color: #007AFF;"></view>
				<view class="item_name">物理</view>
			</view>
			<view class="item">
				<view class="iconfont icon-sheji" style="font-size: 62rpx;color: #007AFF;"></view>
				<view class="item_name">设计</view>
			</view>
			<view class="item">
				<view class="iconfont icon-yishu" style="font-size: 62rpx;color: #007AFF;"></view>
				<view class="item_name">艺术</view>
			</view>
		</view>
		<view class="suggestion" style="font-size: 40rpx; font-weight: 600; margin: 100rpx 0 5rpx 40rpx;">
			推荐
		</view>
		<scroll-view scroll-y="true">
			<view class="waterfalls">
				<waterfall :list="comList" @image-click="open">
					<template v-slot:default="item">
						<view class="listtitle">
							<view class="list"
								style="height: 100rpx; display: flex; justify-content: space-between;align-items: center;">
								<view class="listtitle1">{{ item.title }}</view>
							</view>
						</view>
					</template>
				</waterfall>
				<u-loadmore :status="status" />
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import waterfall from "@/components/waterfall/waterfall.vue";
	export default {
		components: {
			waterfall
		},
		data() {
			return {
				keyword: '',
				comList: [],
				scrollTop: 0,
				iconStyle: {
					color: '#ffffff'
				},
				page: 1,
				pageSize: 7,
				status: 'loading',
				isLoadMore: false,
				collect: false,
				isShow: true,
				searchHistoryList: [],
				user:null
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onReachBottom() {
			if (!this.isLoadMore) {
				this.isLoadMore = true;
				this.page += 1;
				setTimeout(() => {
					this.getWaterFall();
				}, 2000)
			}
		},
		onLoad() {
			this.getWaterFall();
			const user = uni.getStorageSync("access-admin");
			if (user != "") {
				this.user = JSON.parse(user);
			}
		},
		methods: {
			getWaterFall: function() {
				//获取waterfall的数据
				uni.request({
					url: 'http://localhost:8081/waterfall/list',
					data: {
						pageNo: this.page,
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						// this.comList = res.data;
						if (res.data) {
							this.comList = this.comList.concat(res.data);
							if (res.data.length < this.pageSize) {
								this.isLoadMore = true;
								this.status = 'nomore';
							} else {
								this.isLoadMore = false;
							}
						} else {
							this.isLoadMore = true;
							this.status = 'nomore';
						}
					},
					fail: () => {
						uni.showToast({
							title: "服务器开小差了呢，请您稍后再试",
							icon: 'none'
						});
						this.isLoadMore = false;
						if (this.page > 1) {
							this.page--;
						}
					}
				});
				//获取waterfall的数量
				uni.request({
					url: 'http://localhost:8081/waterfall/count',
					data: {

					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.list = res.data;
					}
				});
			},
			clear: function() {
				this.keyword = '';
				this.is_search = true;
			},
			likeTap(item) {
				this.collect = !this.collect
				if (item.isClick == 1) {
					uni.showToast({
						title: '取消点赞',
					});
					item.isClick = 0;
				} else if (item.isClick == 0) {
					uni.showToast({
						title: '点赞成功',
					});
					item.isClick = 1;
				}
				console.log(item.isClick)
				this.setUpdateLikes(item.id, item.isClick);
			},
			like(articleid, index) {
				if (index == 1) {
					uni.showToast({
						title: '取消收藏',
					});
					index = 0;
				} else if (index == 0) {
					uni.showToast({
						title: '收藏成功',
					});
					index = 1;
				}
				// this.setUpdateLikes(articleid, index);
			},
			open(item) {
				console.log("打开详情页" + item.id)
				uni.navigateTo({
					url: "./detail/detail?id=" + item.id,
				})
			},
			setUpdateLikes(id, val) {
				uni.request({
					url: 'http://localhost:8081/waterfall/updateLikes',
					data: {
						articleid: id,
						index: val,
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data);
					}
				});
			},
			searchClick() {
				if (this.keyword == '') {
					uni.showToast({
						title: '请输入单词',
						icon: 'error'
					})
				} else {
					uni.navigateTo({
						url: '../search/search?q=' + this.keyword,
						success: res => {
							console.log("success!");
						},
						fail: () => {
							console.log("defeat!");
						},
						complete: () => {}
					});
				}
			},
			showPanel() {
				this.isShow = !this.isShow;
			},
			hidePanel() {
				this.isShow = !this.isShow;
			},
			onKeyYHKNameInput: function(event) {
				this._timer = setTimeout(() => {
					clearTimeout(this._timer)
					var value = event.target.value;
					//匹配数字的正则表达式
					var patt = /.*[0-9,\u4E00-\u9FA5]{1,}.*/i;
					//匹配特殊字符的正则表达式
					var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
					let _str = ""
					for (let i = 0, len = value.length; i < len; i++) {
						_str += value[i].replace(pattern, '').replace(patt, '')
					}
					this.keyword = _str;
				}, 3)
			},
			// 清空历史搜素
			clearHistory() {
				if (this.user != null) {
					uni.showToast({
						title: '已清空'
					});
					uni.removeStorage({
						key: 'searchList' + this.user.id
					});
					this.searchHistoryList = [];
				}
			},
			itemSearch(item) {
				this.keyword = item
				this.keySearch(item)
			},
			// 搜索的事件
			keySearch(keyword) {
				if (keyword == '') {
					uni.showModal({
						title: '搜索内容不能为空'
					});
				} else {
					if (this.user != null) {
						this.historySearchDetail = false
						this.tabPageDetail = true
						if (this.searchHistoryList.includes(keyword)) {
							let i = this.searchHistoryList.indexOf(keyword);
							this.searchHistoryList.splice(i, 1);
							this.searchHistoryList.unshift(keyword)
						} else {
							this.searchHistoryList.unshift(keyword)
							uni.setStorageSync('searchList' + this.user.id, JSON.stringify(this.searchHistoryList))
						}
						uni.navigateTo({
							url: '../search/search?q=' + this.keyword,
							success: res => {
								console.log("success!");
							},
							fail: () => {
								console.log("defeat!");
							},
							complete: () => {}
						});
					} else {
						uni.navigateTo({
							url: '../search/search?q=' + this.keyword,
							success: res => {
								console.log("success!");
							},
							fail: () => {
								console.log("defeat!");
							},
							complete: () => {}
						});
					}

				}
			}
		},
		onShow() {
			if (this.user != null) {
				this.searchHistoryList = JSON.parse(uni.getStorageSync('searchList' + this.user.id) || '[]')
				console.log('onshow', this.searchHistoryList)
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import url("https://at.alicdn.com/t/font_2355023_rbd2eehhy9.css");

	.u-loadmore {
		margin-bottom: 60px !important;
	}

	.attention {
		width: 88%;
		margin: 0 auto;
		height: 200rpx;
		margin-top: 20rpx;
	}

	.item {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		box-shadow: 0px 0px 28rpx 10rpx rgba(78, 101, 153, 0.14);
		background-color: white;
		width: 150rpx;
		height: 200rpx;
		float: left;
		margin-left: 14rpx;
		border-radius: 8rpx;
	}

	//瀑布流
	.page {
		background-color: #eee;
		height: 100%;
	}

	.pubuBox {
		padding: 22rpx;
	}

	.pubuItem {
		column-count: 2;
		column-gap: 20rpx;
	}

	.item-masonry {
		box-sizing: border-box;
		border-radius: 15rpx;
		overflow: hidden;
		background-color: #fff;
		break-inside: avoid;
		/*避免在元素内部插入分页符*/
		box-sizing: border-box;
		margin-bottom: 20rpx;
		box-shadow: 0px 0px 28rpx 1rpx rgba(78, 101, 153, 0.14);
	}

	.item-masonry image {
		width: 100%;
	}

	.listtitle {
		font-size: 24rpx;

		.listtitle1 {
			line-height: 39rpx;
			//css设置文字超出范围出现省略号
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			-webkit-box-orient: vertical;
			min-height: 39rpx;
			max-height: 78rpx;
			font-size: 26rpx;
			font-weight: 600;
		}
	}

	.Index {
		width: 100%;
		height: 100%;
	}

	.center-search-container {
		flex: 1 auto;
		height: 38rpx;
		margin-top: 20rpx;
	}

	.center-search-bar {
		position: relative;
		margin: 0 auto;
		width: 90%;
	}

	.nav-searchform {
		display: flex;
		align-items: center;
		padding: 0 0 0 15rpx;
		position: relative;
		z-index: 1;
		overflow: hidden;
		height: 100rpx;
		background-color: #FFFFFF;
		opacity: .9;
		transition: background-color .3s;
		border-radius: 16rpx;
	}

	.nav-search-input {
		flex: 1;
		overflow: hidden;
		padding-right: 8px;
		border: none;
		background-color: transparent;
		box-shadow: none;
		color: var(--text2);
		font-size: 32rpx;
		line-height: 32rpx;
	}

	.nav-search-content {
		display: flex;
		align-items: center;
		justify-content: space-between;
		position: relative;
		padding: 0 8rpx;
		height: 80rpx;
		width: 80%;
		border-radius: 16rpx;
		background-color: #E3E5E7;
	}

	.nav-search-btn {
		background-color: #C9CCD0;
		position: absolute;
		right: 20rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		margin: 0;
		padding: 0;
		width: 32px;
		height: 32px;
		border-radius: 16rpx;
		line-height: 32rpx;
	}

	.search-panel {
		position: relative;
		margin-top: -10rpx;
		height: 300rpx;
		overflow-y: auto;
		background: #FFFFFF;
		border-top: none;
		border-radius: 0 0 16rpx 16rpx;
		padding: 13px 0 16px;
		-webkit-font-smoothing: antialiased;
		z-index: 1;
	}

	.header {
		display: flex;
		justify-content: space-between;
		margin: 0 10rpx 0 10rpx;

		.search_title {
			font-size: 30rpx;
			font-weight: 500;
		}
	}

	.trendings-item {
		width: 100%;
		flex-wrap: wrap;
		justify-content: space-between;
		display: flex;
		padding: 0 40rpx;
	}

	.trendings-item:after {
		content: '';
		width: 30%;
	}

	.history_view {
		background-color: #E2E0E0;
		padding: 4rpx 20rpx 4rpx 20rpx;
		margin-top: 20rpx;
		min-width: 200rpx;
		flex-shrink: 0;
		border: 1px solid grey;
		border-radius: 15rpx;
		text-align: center;
	}

	.no-data {
		display: flex;
		margin-top: 60rpx;
		justify-content: center;
	}
</style>
