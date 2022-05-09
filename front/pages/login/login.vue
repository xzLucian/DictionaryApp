<template>
	<view class="content">
		<!-- <view class="iconfont icon-back" @click="back()"></view> -->
		<view class="logo">
			<image src="../../static/login-registration/logo.png" mode=""></image>
		</view>
		<view class="uni-form-item uni-column">
			<input type="text" name="username" v-model="userInfo.username" placeholder="请输入用户名" />
		</view>
		<view class="uni-form-item uni-column">
			<input type="password" name="password" v-model="userInfo.password" placeholder="请输入密码" />
		</view>
		<button type="primary" @click="submit">登陆</button>
		<view class="links">
			<view @tap="gotoForgetPassword">忘记密码？</view>
			<view>|</view>
			<view class="link-highlight" @tap="gotoRegistration">注册账号</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {
					username: '',
					password: '',
				},
				timer: null
			}
		},
		onLoad() {

		},
		methods: {
			back() {
				console.log("aaa")
				uni.switchTab({
					url: '../mine/mine'
				})
			},
			submit() {
				uni.request({
					url: 'http://localhost:8081/customer/login',
					data: {
						username: this.userInfo.username,
						password: this.userInfo.password,
					},
					success: (res) => {
						console.log(res.data);
						if (res.data) {
							uni.setStorageSync('access-admin', JSON.stringify(res.data));
							uni.showToast({
								title: "登录成功",
								icon: "success",
							});
							//设置定时器
							this.timer = setTimeout(() => {
								// 这里添加您的逻辑	
								uni.switchTab({
									url: '../mine/mine',
									success() {
										console.log("跳转成功")
										location.reload()
									}
								});
							}, 2000)
						} else {
							uni.showToast({
								title: "用户名或密码输入错误",
								icon: "error"
							})
						}
					},
					fail: () => {

					}
				});
			},
			gotoRegistration: function() {
				uni.navigateTo({
					url: 'registration'
				});
			},
			gotoForgetPassword: function() {
				uni.navigateTo({
					url: 'forget-password'
				});
			},
			onUnload() {
				if (this.timer) {
					clearTimeout(this.timer);
					this.timer = null;
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import url("https://at.alicdn.com/t/font_2355023_693k4ud3obq.css");
	$color-primary: #b49950;

	.content {
		padding: 100upx;
	}

	.logo {
		text-align: center;

		image {
			height: 200upx;
			width: 200upx;
			margin: 0 0 60upx;
		}
	}

	.uni-form-item {
		margin-bottom: 40upx;
		padding: 0;
		border-bottom: 1px solid #e3e3e3;

		.uni-input {
			font-size: 30upx;
			padding: 7px 0;
		}
	}

	button[type="primary"] {
		background-color: $color-primary;
		border-radius: 0;
		font-size: 34upx;
		margin-top: 60upx;
	}

	.links {
		text-align: center;
		margin-top: 40upx;
		font-size: 26upx;
		color: #999;

		view {
			display: inline-block;
			vertical-align: top;
			margin: 0 10upx;
		}

		.link-highlight {
			color: $color-primary
		}
	}
</style>
