<template>
	<view class="content">

		<view v-if="!changeSuccess">
			<view class="logo">
				<image src="../../static/login-registration/logo.png" mode=""></image>
			</view>
			<view class="title" style="font-size: 30rpx;margin: 0 0 60rpx 0 ; font-weight: 500;">修改密码</view>
			<view class="uni-form-item uni-column">
				<input type="tel" name="username" v-model="username" @blur="checkUserName()" placeholder="请输入用户名" />
			</view>
			<view class="uni-form-item uni-column">
				<input type="password" name="Password" v-model="Pass" placeholder="请输入密码" />
			</view>
			<view class="uni-form-item uni-column">
				<input type="password" name="newPassword" v-model="newPass" placeholder="请输入新密码" />
			</view>
			<button type="primary" @click="submit()">提交</button>
		</view>
		<view v-if="changeSuccess">
			<view class="text-reset">重置成功，新密码已生效！</view>
			<button type="primary" @tap="gotoLogin">立即登陆</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				changeSuccess: false,
				username: '',
				Pass: '',
				newPass: '',
				isExist: true

			}
		},
		onLoad() {

		},
		methods: {
			gotoLogin: function() {
				uni.navigateTo({
					url: 'login'
				})
			},
			checkUserName() {
				uni.request({
					url: 'http://localhost:8081/customer/checkUserName',
					data: {
						username: this.username,
					},
					success: (res) => {
						this.isExist = res.data;
						console.log(res.data)
					},
					fail: () => {

					}
				});
			},
			submit() {
				if (this.isExist) {
					if (this.Pass === this.newPass) {
						uni.request({
							url: 'http://localhost:8081/customer/updatePass',
							data: {
								username: this.username,
								newpass: this.newPass
							},
							success: (res) => {
								this.changeSuccess = !this.changeSuccess;
							},
							fail: () => {

							}
						});
					} else {
						uni.showToast({
							title: "两次输入的密码不一样",
							icon: "error"
						});
						this.Pass = '';
						this.newPass = '';
					}

				} else {
					uni.showToast({
						title: "没有查询到该用户",
						icon: "error"
					});
					// this.username = '';
					// this.Pass = '';
					// this.newPass = '';
				}
			}
		},
		computed: {}
	}
</script>

<style lang="scss" scoped>
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

	.column-with-btn {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;

		button {
			font-size: 24upx;
			margin: 0;
			width: 180upx;
			text-align: center;

			&:after {
				border: none
			}

			&.active {
				background-color: $color-primary;
				color: $uni-text-color-inverse;
			}
		}
	}

	.img-captcha {
		width: 150upx;
		height: 60upx;
	}

	button[type="primary"] {
		background-color: $color-primary;
		border-radius: 0;
		font-size: 34upx;
		margin-top: 60upx;
	}

	.text-reset {
		text-align: center;
		margin-bottom: 100upx;
		font-size: 36upx;
	}
</style>
