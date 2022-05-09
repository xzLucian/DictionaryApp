<template>
	<view class="content">
		<view>
			<view class="uni-form-item uni-column">
				<input type="password" name="oldPassword" v-model="inputOldPass" placeholder="请输入旧密码" />
			</view>
			<view class="uni-form-item uni-column">
				<input type="password" name="newPassword" v-model="inputNewPass" placeholder="请输入新密码" />
			</view>
			<!-- changeSuccess = !changeSuccess -->
			<button type="primary" @click="submit">提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				inputOldPass: '',
				inputNewPass: '',
				oldPass: '',
				customer: null
			}
		},
		methods: {
			submit() {
				if (this.inputOldPass == this.oldPass) {
					console.log("旧密码输入正确")
					uni.navigateTo({
						url: '../setting/setting?password='+this.inputNewPass,
						success: res => {
							console.log("success!");
						},
						fail: () => {
							console.log("defeat!");
						},
						complete: () => {}
					});

				} else {
					console.log("旧密码输入错误")
				}
			},
			gotoLogin: function() {
				uni.navigateTo({
					url: '../../login/login'
				})
			}
		},
		created() {
			const user = uni.getStorageSync("access-admin");
			this.customer = JSON.parse(user);
			console.log(this.customer.password)
			this.oldPass = this.customer.password;
		},
	}
</script>

<style lang="scss" scoped>
	$color-primary: #b49950;

	.content {
		padding: 100upx;
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
