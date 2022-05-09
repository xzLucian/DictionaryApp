<template>
	<view class="content">
		<view class="logo">
			<image src="../../static/login-registration/logo.png" mode=""></image>
		</view>
		<view class="uni-form-item uni-column">
			<input type="text" name="username" v-model="userInfo.username" placeholder="请输入用户名" @blur="checkUserName()" />
		</view>
		<view class="uni-form-item uni-column">
			<input type="password" name="password" v-model="userInfo.password" placeholder="请输入密码" />
		</view>
		<view class="uni-form-item uni-column column-with-btn">
			<input type="text" name="code" placeholder="请输入图片验证码" v-model="captchaImg" />
			<view class="canvas-img-code" @click="refresh()">
				<canvas :style="{width:width+'px',height:height+'px'}" canvas-id="imgcanvas"
					@error="canvasIdErrorCallback"></canvas>
			</view>
		</view>
		<button type="primary" @click="submit">注册</button>
		<view class="links">已有账号？<view class="link-highlight" @tap="gotoLogin">点此登陆</view>
		</view>
	</view>
</template>

<script>
	var _this;
	export default {
		data() {
			return {
				captchaImg: '',
				seconds: 10,
				codeBtn: {
					text: '获取验证码',
					waitingCode: false,
					count: this.seconds
				},
				verCode: "", //验证码
				width: 120,
				height: 45,
				userInfo: {
					username: '',
					password: '',
				},
				timer: null,
				isExist: true
			}
		},
		onLoad() {

		},
		onShow() {
			_this = this;
			setTimeout(function() {
				_this.init();
			}, 1000)
		},
		methods: {
			// 初始化验证码
			init: function() {
				console.log('start');
				var context = uni.createCanvasContext('imgcanvas', this),
					w = this.width,
					h = this.height;
				context.setFillStyle("white");
				context.setLineWidth(5);
				context.fillRect(0, 0, w, h);
				var pool = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "I", "M", "N", "O", "P", "Q",
						"R", "S",
						"T", "U", "V", "W", "S", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
					],
					str = '';
				for (var i = 0; i < 4; i++) {
					var c = pool[this.rn(0, pool.length - 1)];
					var deg = this.rn(-30, 30);
					context.setFontSize(18);
					context.setTextBaseline("top");
					context.setFillStyle(this.rc(80, 150));
					context.save();
					context.translate(30 * i + 15, parseInt(h / 1.5));
					context.rotate(deg * Math.PI / 180);
					context.fillText(c, -15 + 5, -15);
					context.restore();
					str += c;
				}
				uni.setStorage({
					key: 'imgcode',
					data: str,
				});
				for (var i = 0; i < 40; i++) {
					context.beginPath();
					context.arc(this.rn(0, w), this.rn(0, h), 1, 0, 2 * Math.PI);
					context.closePath();
					context.setFillStyle(this.rc(150, 200));
					context.fill();
				}
				context.draw();
				console.log('end');
			},
			rc: function(min, max) {
				var r = this.rn(min, max);
				var g = this.rn(min, max);
				var b = this.rn(min, max);
				return "rgb(" + r + "," + g + "," + b + ")";
			},
			rn: function(max, min) {
				return parseInt(Math.random() * (max - min)) + min;
			},
			refresh: function() {
				this.init();
			},
			canvasIdErrorCallback: function(e) {
				console.error(e.detail.errMsg)
			},
			gotoLogin: function() {
				uni.navigateTo({
					url: 'login'
				})
			},
			checkUserName() {
				uni.request({
					url: 'http://localhost:8081/customer/checkUserName',
					data: {
						username: this.userInfo.username,
					},
					success: (res) => {
						this.isExist = res.data;
						console.log(this.isExist)
					},
					fail: () => {

					}
				});
			},
			submit() {
				const code = uni.getStorageSync("imgcode");
				if (this.captchaImg == code) {
					if (this.isExist) {
						uni.showToast({
							title: "用户名已经存在",
							icon: "error"
						});
						// this.userInfo.username = '';
						// this.userInfo.password = '';
						// this.captchaImg = '';
					} else {
						uni.request({
							url: 'http://localhost:8081/customer/register',
							data: {
								username: this.userInfo.username,
								password: this.userInfo.password,
							},
							success: (res) => {
								uni.showToast({
									title: "注册成功",
									icon: "success"
								});
								this.timer = setTimeout(() => {
									this.gotoLogin()
								}, 2000)
							},
							fail: () => {
								uni.showToast({
									title: "注册失败",
									icon: "error"
								});
							}
						});
					}
				} else {
					uni.showToast({
						title: "验证码错误",
						icon: "error"
					});
				}
			}
		},
		computed: {

		},
		onUnload() {
			if (this.timer) {
				clearTimeout(this.timer);
				this.timer = null;
			}
		}
	}
</script>

<style lang="scss" scoped>
	$color-primary: #b49950;

	.content {
		padding: 60upx 100upx 100upx;
	}

	.logo {
		text-align: center;

		image {
			height: 200upx;
			width: 200upx;
			margin: 0 0 40upx;
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
