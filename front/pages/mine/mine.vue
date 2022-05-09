<template>
	<view class="mine_content">
		<view class="nav">
			<view v-if="user!=null" class="iconfont icon-settings" @click="setting"></view>
		</view>
		<view class="main">
			<view class="navatar">
				<view class="login" v-if="isLogin" @click="login">登 录</view>
				<u-avatar v-else :src="src" shape="circle" size="150rpx"></u-avatar>
			</view>
			<view style="font-size:34rpx;font-weight: 500;  margin: 10rpx 0 10rpx 0;">{{user==null?'':user.username}}
			</view>
			<view style="font-size:18rpx; color: #BBBBBB;">{{user==null?'写点什么~?':user.slogan}}</view>
		</view>
		<!-- <view class="message_box">
			<view class="attention_box">
				<view class="attention">{{attention}}</view>
				<view>关注</view>
			</view>
			<view class="thumbs_box">
				<view class="thumbs">{{thumbs}}</view>
				<view>点赞</view>
			</view>
			<view class="collect_box">
				<view class="collect">{{collect}}</view>
				<view>收藏</view>
			</view>
		</view> -->

		<view class="vocabulary_card">
			<view style="font-size: 42rpx;padding: 20rpx 0 0 30rpx;">单词本</view>

			<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" style="height: 300rpx;">
				<!-- <view id="demo1" class="item" @click="openBook">
					<view class="cover"><text>研</text></view>
					<view class="cover_name">考研词汇</view>
				</view>
				<view id="demo2" class="item">
					<view class="cover"><text>雅</text></view>
					<view class="cover_name">雅思词汇</view>
				</view>
				<view id="demo3" class="item">
					<view class="cover">
						<view class="iconfont icon-add" style="font-size: 60rpx;"></view>
					</view>
					<view class="cover_name" @click="createBook">新建单词本</view>
				</view> -->
				<u-swipe-action>
					<u-swipe-action-item :options="options1" v-for="(item,index) in list" @click="deleteBook(item)">
						<view class="item" @click="openBook(item)">
							<view class="cover"><text>{{item.covername}}</text></view>
							<view class="cover_name">{{item.bookname}}</view>
						</view>
					</u-swipe-action-item>
					<view class="item">
						<view class="cover">
							<view class="iconfont icon-add" style="font-size: 60rpx;"></view>
						</view>
						<view class="cover_name" @click="createBook()">新建单词本</view>
					</view>
				</u-swipe-action>
				<!-- <view class="item-box">
					<view class="item" v-for="(item,index) in list" :key="index">
						<view class="cover"><text>{{item.covername}}</text></view>
						<view class="cover_name">{{item.bookname}}</view>
					</view>
					<view class="item">
						<view class="cover">
							<view class="iconfont icon-add" style="font-size: 60rpx;"></view>
						</view>
						<view class="cover_name" @click="createBook()">新建单词本</view>
					</view>
				</view> -->
			</scroll-view>
			<u-modal :show="show" :title="title" @confirm="addBook()" @cancel="closeBook()"
				:showCancelButton="showCancelButton" :closeOnClickOverlay="true" @close="closeBook()">
				<view class="slot-content" style="color: black;display: flex;">
					<text>名称：</text>
					<input type="text" style="border-bottom: 5rpx solid #000000;" v-model="inputContent" />
				</view>
			</u-modal>
		</view>
		<!-- <view class="collect_card">
			<view style="font-size: 42rpx;padding: 20rpx 0 0 30rpx;">收藏本</view>
			<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" style="height: 300rpx;">
				<view id="demo1" class="item">
					<view class="cover"><text>藏</text></view>
					<view class="cover_name">收藏句子</view>
				</view>
				<view id="demo2" class="item">
					<view class="cover">
						<view class="iconfont icon-add" style="font-size: 60rpx;"></view>
					</view>
					<view class="cover_name">新建收藏本</view>
				</view>
			</scroll-view>
		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				attention: 0,
				thumbs: 0,
				collect: 0,
				scrollTop: 0,
				src: "https://cdn.uviewui.com/uview/album/1.jpg",
				isLogin: true,
				user: null,
				inputContent: '',
				options1: [{
					text: '删除'
				}],
				list: [
					// {
					// 	id: 1,
					// 	covername: "研",
					// 	bookname: "考研词汇"
					// },
					// {
					// 	id: 2,
					// 	covername: "雅",
					// 	bookname: "雅思词汇"
					// }
				],
				show: false,
				title: '新建单词本',
				showCancelButton: true,
				timer: null
			}
		},
		created() {
			const user = uni.getStorageSync("access-admin");
			this.user = JSON.parse(user);
			if (this.user != null) {
				this.isLogin = !this.isLogin;
				this.src = this.user.imageUrl;
			}
			const list = uni.getStorageSync("access-list" + this.user.id);
			this.list = JSON.parse(list);
		},
		onUnload() {
			if (this.timer) {
				clearTimeout(this.timer);
				this.timer = null;
			}
		},
		methods: {
			login() {
				uni.navigateTo({
					url: '../login/login'
				})
			},
			setting() {
				uni.navigateTo({
					url: './setting/setting'
				})
			},
			openBook(item) {
				uni.navigateTo({
					url: './vocabulary/vocabulary?bookid=' + item.id + '&bookname=' + item.bookname
				})
			},
			createBook() {
				// console.log("创建单词本")
				if (this.user == null) {
					uni.showToast({
						title: '请先登录,即将跳转登录',
						icon: 'none'
					});
					this.timer = setTimeout(() => {
						uni.navigateTo({
							url: '../login/login'
						});
					}, 3000);
				} else {
					this.show = true;
				}
			},
			addBook() {
				// console.log(this.list)
				let add = {
					id: this.list.length + 1,
					covername: this.inputContent.charAt(0),
					bookname: this.inputContent
				}
				this.list.push(add);
				uni.setStorageSync('access-list' + this.user.id, JSON.stringify(this.list));
				this.show = false;
				uni.showToast({
					title: '添加成功',
					icon: 'success'
				})
			},
			closeBook() {
				this.show = false;
			},
			deleteBook(item) {
				console.log("删除" + item.id)
				uni.showModal({
					title: '提示',
					content: '确定删除？',
					cancelText: "取消", // 取消按钮的文字  
					confirmText: "确认", // 确认按钮的文字  
					showCancel: true, // 是否显示取消按钮，默认为 true
					confirmColor: '#f55850',
					cancelColor: '#39B54A',
					success: (res) => {
						console.log('confirm')
						if (res.confirm) {
							this.list = this.list.filter((i) => {
								return i != item
							})
							uni.setStorageSync('access-list' + this.user.id, JSON.stringify(this.list));
						} else {
							console.log('cancel') //点击取消之后执行的代码
						}
					}
				})

			}
		}
	}
</script>

<style lang="scss">
	@import url("https://at.alicdn.com/t/font_2355023_693k4ud3obq.css");

	.nav {
		height: 78rpx;
	}

	.login {
		width: 150rpx;
		height: 150rpx;
		border-radius: 50%;
		background-color: #007AFF;
		display: flex;
		justify-content: center;
		align-items: center;
		color: white;
	}

	.icon-settings {
		float: right;
		font-size: 42rpx;
		margin: 18rpx 18rpx 0 0;
	}

	.main {
		/* 竖直排列 */
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.message_box {
		background-color: #2C2C2C;
		border-radius: 25rpx;
		width: 90%;
		margin: 20rpx auto;
		display: flex;
		justify-content: space-around;
		align-items: center;
		height: 200rpx;
	}

	.attention_box,
	.thumbs_box,
	.collect_box {
		background-color: #eef2f4;
		width: 100rpx;
		height: 100rpx;
		display: flex;
		/* establish flex container */
		flex-direction: column;
		/* stack flex items vertically */
		justify-content: center;
		/* center items vertically, in this case */
		align-items: center;
		/* center items horizontally, in this case */
		border-radius: 25rpx;
	}

	.vocabulary_card {
		width: 90%;
		border-radius: 25rpx;
		height: 400rpx;
		margin: 50rpx auto;
		background-color: #2C2C2C;
		color: white;
	}

	.collect_card {
		width: 90%;
		border-radius: 25rpx;
		height: 400rpx;
		margin: 20rpx auto;
		background-color: #2C2C2C;
		color: white;
	}

	.item {
		margin-top: 5rpx;
		height: 150rpx;
		// border-top: 3rpx solid #FFFFFF;
		/* background-color: #0081FF; */
		display: flex;
		align-items: center;
	}

	.cover {
		width: 90rpx;
		height: 90rpx;
		background-color: #e64644;
		font-size: 60rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 0 20rpx 0 20rpx;
		border-radius: 20rpx;
	}
</style>
