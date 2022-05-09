<template>
	<view>
		<!-- <u-navbar title="编辑姓名" @rightClick="rightClick" :autoBack="true"></u-navbar> -->
		<view class="iconfont icon-back" style="font-size: 34rpx; margin: 30rpx 0 0 50rpx;" @click="back"></view>
		<u--input class="EditName" border="surround" clearable :value="name" @confirm="submit"></u--input>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: ''
			}
		},
		methods: {
			submit(value){
				this.name = value
				console.log(this.name)
			},
			back(){
				uni.navigateTo({
					url: '../setting/setting?username='+this.name,
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
</script>

<style>
	@import url("https://at.alicdn.com/t/font_2355023_693k4ud3obq.css");
	.EditName {
		width: 90%;
		margin: 30rpx auto;
		background-color: #ffffff;
		color: #00FF00
	}
</style>
