<template>
	<view>
		<view class="top_box"
			style="display: flex; justify-content: space-between; align-items: center;padding: 30rpx;">
			<!-- <view class="iconfont icon-back" @click="back"></view> -->
			<view class="setting_box">
				<view class="iconfont icon-shezhi" style="color: #000000; font-size: 40rpx;" @click="choose = true">
				</view>
				<u-picker :show="choose" :columns="columns" @confirm="Confirm" @cancel="Close" :title="title">
				</u-picker>
			</view>
		</view>
		<view class="content">
			<swiper class="item-container" previous-margin="45rpx" next-margin="45rpx" @change="swiperChange"
				@transition="lastItem">
				<swiper-item :class="currentIndex == index ? 'swiper-item' : 'swiper-item-side'"
					v-for="(item, index) in lastDataList" :key="index" lazy-load
					:style="dontFirstAnimation ? 'animation: none;' : ''">
					<view v-if="show" class="item" :class="currentIndex == index ? 'item-img' : 'item-img-side'"
						@click="showData">
						<view class="item_1-id">
							{{currentIndex+1}} / {{lastDataList.length}}
						</view>
						<view class="item_1">
							<view class="item_1-name">
								{{item.name}}
							</view>
						</view>
						<view class="item_2" :style="{'bottom': currentIndex == index? '38rpx': '20rpx'}">
							<view class="iconfont icon-song" @click.stop="voice(1,item.name)"
								style="font-weight: bolder;"></view>
							<view class="item_2-voice">
								<text class="item-highlight">/ {{item.english}} /</text>
							</view>
						</view>
					</view>
					<view v-else class="box" :class="currentIndex == index ? 'item-img' : 'item-img-side'"
						@click="showData">
						<view class="detailData">
							<view class="item-apology">
								{{item.apology}}
							</view>
						</view>
					</view>
				</swiper-item>
			</swiper>
		</view>
		<view class="btn_box">
			<view :class="know==true ? 'item' : 'item on'"
				@click.stop="review(lastDataList[currentIndex].vocabularyid,1)">认识</view>
			<view :class="unknow==true ? 'item' : 'item on'"
				@click.stop="review(lastDataList[currentIndex].vocabularyid,-1)">不认识
			</view>
			<view :class="unsure==true ? 'item' : 'item on'"
				@click.stop="review(lastDataList[currentIndex].vocabularyid,0)">不确定</view>
		</view>
	</view>
</template>

<script>
	export default {
		onLoad(e) {
			const user = uni.getStorageSync("access-admin");
			this.user = JSON.parse(user);
			const limit = uni.getStorageSync("page-limit");
			this.getAllWord(e.bookid, JSON.parse(limit));
		},
		data() {
			return {
				currentIndex: 0,
				vocIndex: 0,
				dontFirstAnimation: true,
				movHeight: '360rpx', //卡片高度
				//传入的数据示例
				lastDataList: [],
				show: true,
				choose: false,
				limit: '',
				columns: [
					['5', '10', '15', '20']
				],
				know: true,
				unknow: true,
				unsure: true,
				title: '背诵单词量',
				timer: null
			}
		},
		onUnload() {
			if (this.timer) {
				clearTimeout(this.timer);
				this.timer = null;
			}
		},
		methods: {
			lastItem(e) {
				if (e.detail.dx == 0) {
					uni.showToast({
						title: "恭喜你，今天的任务已经完成了",
						icon: "none"
					});
					this.timer = setTimeout(() => {
						uni.switchTab({
							url: '../../mine'
						});
					}, 3000);
				}
			},
			swiperChange(e) {
				this.dontFirstAnimation = false
				this.currentIndex = e.detail.current;
				this.unknow = true;
				this.know = true;
				this.unsure = true;
			},
			getAllWord(bookid, page) {
				uni.request({
					url: 'http://localhost:8081/vocabulary/getNoReviewedWordByLimit',
					data: {
						bookid: bookid,
						userid: this.user.id,
						limit: page
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data)
						this.lastDataList = res.data.data;
						this.length = this.lastDataList.length;
					},
					fail: () => {

					}
				});
			},
			back() {
				uni.navigateTo({
					url: '../vocabulary'
				})

			},
			review(id, type) {
				console.log(id, type)
				uni.request({
					url: 'http://localhost:8081/vocabulary/review',
					data: {
						vocid: id,
						isReview: 1,
						type: type,
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data)
					},
					fail: () => {

					}
				});
				switch (type) {
					case 1:
						this.unsure = true;
						this.know = false;
						this.unknow = true;
						break;
					case 0:
						this.unsure = false;
						this.know = true;
						this.unknow = true;
						break;
					case -1:
						this.unknow = false;
						this.know = true;
						this.unsure = true;
						break;
					default:
						this.unknow = true;
						this.know = true;
						this.unsure = true;
						break;
				}
			},
			showData() {
				this.show = !this.show
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
			Confirm(e) {
				// console.log('confirm', e)
				this.limit = e.value[0];
				// console.log(this.limit);
				uni.setStorageSync('page-limit', JSON.stringify(this.limit));
				this.choose = false;
				location.reload()
			},
			Close() {
				this.choose = false
			},

		}
	}
</script>

<style lang="scss">
	@import url("https://at.alicdn.com/t/font_2355023_brntn1nhe5o.css");

	.content {

		// 轮播图
		.image-container {
			width: 750rpx;
			height: 320rpx;
		}

		.item {
			background: linear-gradient(44deg, #FFCC8F, #FFA657);
			position: relative;
		}

		.image {
			width: 116rpx;
			height: 116rpx;
			border-radius: 50%;
			border: 2rpx solid #FFFFFF;
		}

		.item_1 {
			display: flex;
			align-items: center;
			justify-content: center;
			margin: -50rpx;
			font-size: 54rpx;

			&.on {
				margin: 0rpx;
			}
		}

		.box {
			background: linear-gradient(44deg, #FFCC8F, #FFA657);
		}

		.item-apology {
			padding: 20rpx;
		}



		.item-img {
			width: 630rpx;
			height: 240rpx;
			border-radius: 14rpx;
			animation: to-big .3s;
			box-shadow: 5rpx 10rpx 10rpx rgba($color: #FFAB5E, $alpha: .4);
		}

		.item_2 {
			width: 100%;
			display: flex;
			align-items: center;
			justify-content: center;
			position: absolute;
			font-size: 24rpx;
			font-weight: bold;
		}

		.item_2-voice {
			margin-right: 58rpx;
		}

		.item-highlight {
			font-size: 32rpx;
			color: #0072EB;
			margin-left: 10rpx;
		}

		.swiper-item {
			width: 630rpx;
			height: 240rpx;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.item-img-side {
			width: 630rpx;
			height: 208rpx;
			border-radius: 14rpx;
			animation: to-mini .3s;
		}

		.swiper-item-side {
			width: 630rpx;
			height: 208rpx;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		@keyframes to-mini {
			from {
				height: 240rpx;
			}

			to {
				height: 208rpx;
			}
		}

		@keyframes to-big {
			from {
				height: 208rpx;
			}

			to {
				height: 240rpx;
			}
		}
	}

	.vocabulary_box {
		width: 90%;
		height: 300rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 20rpx auto;
		background-color: #007AFF;
		border-radius: 30rpx;
		font-size: 50rpx;
		font-weight: 800;
		box-shadow: 0px 13px 5px #888888;
	}

	.btn_box {
		width: 90%;
		height: 600rpx;
		margin: 20rpx auto;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.item {
		background-color: white;
		width: 450rpx;
		height: 100rpx;
		text-align: center;
		line-height: 100rpx;
		border-radius: 30rpx;
		margin-top: 30rpx;
		box-shadow: 10px 10px 5px #888888;

		&.on {
			background-color: #007AFF;
		}
	}
</style>
