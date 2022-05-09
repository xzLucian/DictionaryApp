<template>
	<view class="study_box">
		<view class="navBar">
			<view class="choose" style="color:#dc9407;">精选</view>
			<view class="iconfont icon-menu" style="font-size: 48rpx;"></view>
		</view>
		<view class="u-demo-block">
			<u-swiper :list="list3" previousMargin="30" nextMargin="30" circular :autoplay="false" radius="5">
			</u-swiper>
		</view>
		<u-divider text="分割线" :dot="true"></u-divider>
		<view class="public" style="font-size: 38rpx;margin-left: 50rpx;">视频学习</view>
		<view class="video_box">
			<view class="video_section" v-for="(item,index) in videolists" :key="index">
				<video id="item.name" :src="item.videoUrl" :poster="item.poster" controls></video>
				<view class="title" style="width: 100%;margin: 10rpx 0 10rpx 0; font-weight: bold;">{{item.title}}
				</view>
				<view class="introduce">
					<view class="mess_people">
						<u-avatar :src="imageUrl" shape="circle" size="50rpx"></u-avatar>
						<view class="peopleName" style="margin-left: 10rpx;font-size: 24rpx; color: #000000;">
							{{item.name}}
						</view>
					</view>
					<view class="mess_video">
						<view class="iconfont icon-video" style="font-size: 22rpx; margin: 0 10rpx 0 0;"></view>
						<view class="watchNum" style="font-size: 22rpx;">{{item.watchNum}}次观看</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				videolists: [],
				list3: [],
				src: '',
				poster: '',
				imageUrl: 'https://cdn.uviewui.com/uview/album/1.jpg'
			}
		},
		onLoad() {
			this.getVideo();
			this.getCarousel();
		},
		methods: {
			//获取轮播图
			getCarousel() {
				uni.request({
					url: 'http://localhost:8081/carousel/list',
					data: {

					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data[0].imageUrl);
						for (var i = 0; i < 3; i++) {
							this.list3.push(res.data[i].imageUrl);
						}
					}
				});
			},
			getVideo() {
				uni.request({
					url: 'http://localhost:8081/videoList/list',
					data: {

					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.videolists = res.data;
						console.log(res.data);
					},
					fail: () => {
						uni.showToast({
							title: "服务器开小差了呢，请您稍后再试",
							icon: 'none'
						});
					}
				});
			}
		}
	}
</script>

<style>
	@import url("https://at.alicdn.com/t/font_2355023_6607y4sg0o8.css");

	.navBar {
		width: 90%;
		margin: 20rpx auto;
		display: flex;
		align-items: center;
		justify-content: space-between;
		font-size: 38rpx;
		padding: 5rpx;
	}

	.item_box {
		margin: 20rpx auto;
		width: 90%;
		display: flex;
		justify-content: space-around;
		font-size: 20rpx;
	}

	.box {
		display: flex;
		/* establish flex container */
		flex-direction: column;
		/* stack flex items vertically */
		justify-content: center;
		/* center items vertically, in this case */
		align-items: center;
		/* center items horizontally, in this case */
	}

	.item {
		margin: 15rpx;
		width: 100rpx;
		height: 100rpx;
		background-color: #fffae2;
		border-radius: 25rpx;
	}

	.video_box {
		width: 73%;
		margin: 0 auto;

	}

	.video_section {
		margin-top: 50rpx;
	}

	.introduce {
		width: 100%;
		display: flex;
		justify-content: space-between;
	}

	.mess_video,
	.mess_people {
		display: flex;
		align-items: center;
	}
</style>
