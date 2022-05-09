<template>
	<view class="translate">
		<view class="nav">
			<view class="iconfont icon-Chinese"></view>
			<view class="iconfont icon-exchange"></view>
			<view class="iconfont icon-English"></view>
		</view>
		<view class="content">
			<view class="show_textarea" style="display: flex;">
				<textarea class="input_box" v-model="text" placeholder-style="color:#b7babb;font-size:38rpx;"
					placeholder="输入要翻译的文本" style="font-size: 38rpx;" />
				<view v-if="text" class="iconfont icon-close" @click="clear"
					style="font-size: 50rpx;margin-left: 35rpx;color: #BBBBBB;">
				</view>
			</view>
			<view class="iconfont icon-trans" @click="exchange"
				style="font-size: 80rpx;display: flex; justify-content: center;justify-items: center;"></view>
			<view v-if="!has_result" class="hidden_textarea">
				<textarea class="result_box" v-model="result" />
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				text: "",
				result: "",
				has_result:true
			}
		},
		methods: {
			clear() {
				this.text = "";
				this.result = "";
				this.has_result = true;
			},
			change: function() {
				let temp = this.one;
				this.one = this.two;
				this.two = temp;
			},
			//翻译接口
			exchange() {
				if (this.text == "") {
					uni.showToast({
						title:"请输入文本",
						icon:"error"
					})
				} else {
					uni.request({
						url: '/trans/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=null',
						data: {
							'type': "AUTO",
							'i': this.text,
							"doctype": "json",
							"version": "2.1",
							"keyfrom": "fanyi.web",
							"ue": "UTF-8",
							"action": "FY_BY_CLICKBUTTON",
							"typoResult": "true"
						},
						header: {
							'Content-Type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							this.result = res.data.translateResult[0][0].tgt;
							this.has_result = false;
						}
					});
				}
			}
		}
	}
</script>

<style>
	@import url("https://at.alicdn.com/t/font_2355023_qv7axs6srhh.css");

	.nav {
		width: 30%;
		height: 100rpx;
		line-height: 100rpx;
		background-color: #f7f7f7;
		margin: 20rpx auto;
		display: flex;
		justify-content: center;
		border-radius: 25rpx;
	}

	.icon-Chinese,
	.icon-exchange,
	.icon-English {
		margin: 0 10rpx 0 10rpx;
		font-size: 52rpx;
	}

	.show_textarea,
	.hidden_textarea {
		width: 90%;
		margin: 30rpx auto;
		background-color: #feffff;
		border-radius: 25rpx;
		padding: 20rpx;
	}
</style>
