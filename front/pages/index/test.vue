<template>
	<view class="content">
		<!-- 轮播图 -->
		<swiper class="image-container" previous-margin="45rpx" next-margin="45rpx" circular autoplay @change="swiperChange">
			<swiper-item :class="currentIndex == index ? 'swiper-item' : 'swiper-item-side'" v-for="(item, index) in imgList" :key="index" lazy-load :style="dontFirstAnimation ? 'animation: none;' : ''">
				<view class="item" :class="currentIndex == index ? 'item-img' : 'item-img-side'">
					<view class="item_1">
						<image class="image" :src="item.image || errorImage" mode=""></image>
						<view class="item_1-right">
							{{item.name}}
							<text class="item_1-post">{{item.post}}</text>
							<view class="item_1-site">{{item.site}}</view>
						</view>
					</view>
					<view class="item_2" :style="{'bottom': currentIndex == index? '38rpx': '20rpx'}">
						<view class="item_2-left">
							满意度
							<text class="item-highlight">99%</text>
						</view>
						<view class="item_2-right">
							累计咨询
							<text class="item-highlight">9999</text>
						</view>
					</view>
				</view>
			</swiper-item>
		</swiper>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				errorImage: this.$mAssetsPath.errorImage,
				imgList: [{
					name: '茉莉',
					site: '中原区第一人民医院',
					post: '神经内科·主任医师',
					image: ''
				}, {
					name: '茉莉',
					site: '中原区第一人民医院',
					post: '神经内科·主任医师',
					image: ''
				}, {
					name: '茉莉',
					site: '中原区第一人民医院',
					post: '神经内科·主任医师',
					image: ''
				}],
				currentIndex: 0,
				dontFirstAnimation: true
			}
		},
		methods: {
			swiperChange(e) {
				this.dontFirstAnimation = false
				this.currentIndex = e.detail.current
			},
		}
	}
</script>
 
<style lang="scss" scoped>
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
			margin: 30rpx;
		}
		
		.item_1-right {
			font-size: 36rpx;
			font-weight: bold;
			margin-left: 20rpx;
			padding-top: 10rpx;
		}
		
		.item_1-post {
			font-size: 24rpx;
			margin-left: 16rpx;
		}
		
		.item_1-site {
			font-size: 24rpx;
			opacity: .6;
			margin-top: 20rpx;
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
			justify-content: flex-end;
			position: absolute;
			font-size: 24rpx;
			font-weight: bold;
		}
		
		.item_2-left {
			margin-right: 48rpx;
		}
		
		.item_2-right {
			margin-right: 102rpx;
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
		@keyframes to-mini
		{
			from {
				height: 240rpx;
			}
			to {
				height: 208rpx;
			}
		}
		@keyframes to-big
		{
			from {
				height: 208rpx;
			}
			to {
				height: 240rpx;
			}
		}
	}
</style>

