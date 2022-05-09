<template>
	<view>
		<view class="top_box" v-if="NoReviewWords.length!=0"
			style=" width: 90%;margin: 20rpx auto; display: flex;align-items: center;justify-content: space-between;">
			<view class="title" style="font-size: 40rpx;font-weight: 500;">{{this.bookname}}</view>

		</view>

		<view v-else class="no-data">单词本还未收录单词哦！</view>
		<u-subsection :list="list" :current="curNow" @change="sectionChange"></u-subsection>
		<view v-if="curNow==0" class="vocabulary_list">
			<view class="operate"
				style="display: flex; justify-content: space-between; align-items: center;margin:20rpx;">
				<view class="recite"
					style="background-color: #0081FF;padding: 15rpx; border-radius: 30rpx;color: white;width: 200rpx;text-align: center;"
					@click="reciteVocabulary">背诵单词</view>
				<view :class="this.deletebtn!=true ? 'btn' : 'btn on'">
					<view class="iconfont icon-deleteAll" style="font-size: 40rpx;" @click="deleteAllItems"></view>
				</view>
			</view>
			<scroll-view scroll-y="true">
				<u-checkbox-group v-model="checkboxValue1" placement="column" @change="checkboxChange">
					<!-- <u-checkbox :customStyle="{marginBottom: '8px'}" v-for="(item, index) in NoReviewWords" :key="index"
							:label="item.name" :name="item.name">
						</u-checkbox> -->
					<u-swipe-action>
						<u-swipe-action-item :options="options1" v-for="(item,index) in NoReviewWords"
							@click="deleteItem(item)">
							<view class="item_box" style="display: flex; align-items: center; margin: 10rpx;">
								<u-checkbox v-if="deletebtn==true" :name="item.name"
									:checked="NoReviewWords.includes(String(item.value))"></u-checkbox>
								<!-- <checkbox :value="item.value" v-for="(item,index) in items" :key="index" :checked="checkList.includes(String(item.value))">{{item.name}}</checkbox> -->
								<view v-else class="iconfont icon-song" @click="voice(1,item.name)"></view>
								<view class="content_box" style="width: 90%; margin: 30rpx auto;">
									<view class="item" style="font-size: 34rpx; font-weight: 700; color: white;">
										{{item.name}}
									</view>
									<view class="apology" style="font-size: 24rpx; color: #BBBBBB;white-space: nowrap;
										overflow: hidden;text-overflow: ellipsis;">{{item.apology}}</view>
								</view>
							</view>
						</u-swipe-action-item>
					</u-swipe-action>
				</u-checkbox-group>
			</scroll-view>
			<view class="bottom_box" :class="this.deletebtn!=true ? 'bottom' : 'bottom active'">
				<view class="box" style="display: flex;justify-content: space-around;">
					<view class="selectAll"
						style="margin: 10rpx;padding: 30rpx;background-color: #FFFFFF;border-radius: 30rpx;"
						@click="selectAll">全选</view>
					<view class="deleteAll"
						style="margin: 10rpx;padding: 30rpx;background-color: #FFFFFF;border-radius: 30rpx;"
						@click="deleteAll">删除</view>
				</view>
			</view>
		</view>
		<view v-if="curNow==1" class="ReviewWord">
			<scroll-view scroll-y="true" style="margin-top: 20rpx;">
				<u-swipe-action>
					<!-- <checkbox-group @change="seletedStatus" checked> -->
					<u-swipe-action-item :options="options1" v-for="(item,index) in ReviewWords" @click="deleteItem(item)">
						<view class="item_box" style="display: flex; align-items: center; margin: 10rpx;">
							<view class="iconfont icon-song" @click="voice(1,item.name)"></view>
							<view class="content_box" style="width: 90%; margin: 30rpx auto;">
								<view class="item" style="font-size: 34rpx; font-weight: 700; color: white;">
									{{item.name}}
								</view>
								<view class="apology" style="font-size: 24rpx; color: #BBBBBB;white-space: nowrap;
							overflow: hidden;text-overflow: ellipsis;">{{item.apology}}</view>
							</view>
						</view>
					</u-swipe-action-item>
					<!-- </checkbox-group> -->
				</u-swipe-action>
			</scroll-view>
		</view>

	</view>
</template>

<script>
	export default {

		data() {
			return {
				user: Object,
				book: Object,
				bookid: 0,
				bookname: '',
				NoReviewWords: [],
				ReviewWords: [],
				list: ['待复习', '已复习'],
				curNow: 0,
				options1: [{
					text: '删除'
				}],
				checkboxValue1: [],
				// 基本案列数据
				checkboxList1: [{
						name: '苹果',
						disabled: false
					},
					{
						name: '香蕉',
						disabled: false
					},
					{
						name: '橙子',
						disabled: false
					}
				],
				deletebtn: false,
				show: true,
				seletedAll: false,
				timer:null
			}
		},
		onUnload() {
			if (this.timer) {
				clearTimeout(this.timer);
				this.timer = null;
			}
		},
		onLoad(e) {
			const user = uni.getStorageSync("access-admin");
			this.user = JSON.parse(user);
			this.bookname = e.bookname;
			this.bookid = e.bookid;
			this.getNoReviewedWord(e.bookid)
			this.getReviewedWord(e.bookid);
		},
		methods: {
			checkboxChange(n) {
				// console.log('change', n);
				this.checkboxValue1 = n;
				console.log(this.checkboxValue1);
			},
			sectionChange(index) {
				this.curNow = index;
			},
			getNoReviewedWord(bookid) {
				uni.request({
					url: 'http://localhost:8081/vocabulary/getNoReviewedWord',
					data: {
						bookid: bookid,
						userid: this.user.id
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						// console.log(res.data)
						this.NoReviewWords = res.data.data;
					},
					fail: () => {

					}
				});
			},
			getReviewedWord(bookid) {
				uni.request({
					url: 'http://localhost:8081/vocabulary/getReviewedWord',
					data: {
						bookid: bookid,
						userid: this.user.id
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.ReviewWords = res.data.data;
					},
					fail: () => {

					}
				});
			},
			reciteVocabulary() {
				uni.navigateTo({
					url: 'reciteVocabulary/reciteVocabulary?bookid=' + this.bookid
				})
			},
			voice(i, q) {
				const innerAudioContext = uni.createInnerAudioContext();
				innerAudioContext.autoplay = true;
				innerAudioContext.src = 'http://dict.youdao.com/dictvoice?type=' + i + '&audio=' + q;
				innerAudioContext.onPlay(() => {
					console.log('开始播放');
				});
				innerAudioContext.onError((res) => {
					console.log(res.errMsg);
					console.log(res.errCode);
				})
			},
			deleteItem(item) {
				console.log("删除" + item.vocabularyid)
				uni.showModal({
					title: '提示',
					content: '确定删除？',
					cancelText: "取消", // 取消按钮的文字  
					confirmText: "确认", // 确认按钮的文字  
					showCancel: true, // 是否显示取消按钮，默认为 true
					confirmColor: '#f55850',
					cancelColor: '#39B54A',
					success: (res) => {
						if (res.confirm) {
							console.log('comfirm') //点击确定之后执行的代码
							this.NoReviewWords = this.NoReviewWords.filter((i) => {
								return i != item
							})
							uni.request({
								url: 'http://localhost:8081/vocabulary/deleteWord',
								data: {
									vocid: item.vocabularyid,
								},
								header: {
									'Content-Type': 'application/x-www-form-urlencoded'
								},
								success: (res) => {
									console.log(res.data)
									uni.showToast({
										title:"删除成功",
										icon:"success"
									})
									this.timer = setTimeout(() => {
										location.reload();
									}, 3000);
								},
								fail: () => {
							
								}
							});
							location.reload()
						} else {
							console.log('cancel') //点击取消之后执行的代码
						}
					}
				})
			},
			deleteAllItems() {
				if (!this.deletebtn) {
					uni.showToast({
						title: "批量删除",
						icon: "none"
					})
				}
				this.deletebtn = !this.deletebtn;
			},
			selectAll() {
				console.log("全选")
				if (!this.seletedAll) {
					this.seletedAll = true
					this.NoReviewWords.map(item => {
						this.checkboxValue1.push(item.name)
					})
					console.log(this.checkboxValue1)
					console.log('true')
				} else {
					this.seletedAll = false
					this.checkboxValue1 = []
					console.log('false')
				}
			},
			deleteAll() {
				console.log("删除全部");
				uni.request({
					url: 'http://localhost:8081/vocabulary/deleteAllWords',
					data: {
						itemArr:this.checkboxValue1
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if(res.code==200){
							uni.showToast({
								title:"删除成功",
								icon:"success"
							});
							location.reload()
						}else{
							uni.showToast({
								title:"删除失败",
								icon:"error"
							});
							location.reload()
						}
					},
					fail: () => {
				
					}
				});
			}
		}
	}
</script>

<style lang="scss">
	@import url("https://at.alicdn.com/t/font_2355023_w0x1ji2ax6.css");

	.icon-song {
		color: red;
		font-weight: bolder;
	}

	.no-data {
		font-size: 34rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		margin-top: 50rpx;
	}

	.btn {
		padding: 15rpx;
		background-color: #BBBBBB;
		border-radius: 25rpx;

		&.on {
			/* background-color: #007AFF;
			padding: 15rpx;
			border-radius: 25rpx; */
			color: white;
			background-color: #007AFF;
		}

	}

	.bottom_box {
		background-color: #007AFF;
		width: 90%;
		height: 120rpx;
		position: fixed;
		margin: auto;
		top: -40rpx;
		left: 0;
		right: 0;
		z-index: 10;
		border-radius: 25rpx;
		animation: hideLayer 0.2s linear both;

		&.active {
			animation: showLayer 0.2s linear both;
			top: 30rpx;
		}

		@keyframes showLayer {
			0% {
				transform: translateY(0%);

			}

			100% {
				transform: translateY(80rpx); //这里可以通过变大变小调整偏移量

			}

		}

		@keyframes hideLayer {
			0% {
				transform: translateY(80rpx);

			}

			100% {
				transform: translateY(0);

			}

		}

	}
</style>
