<template>
	<view>
		<!-- <view class="iconfont icon-reBack"
			style="font-size: 56rpx; margin: 40rpx 0 0 40rpx; position: absolute; color: #2f694f;" @click="back"></view> -->
		<!-- 详情页{{id}} -->
		<view class="title_box">
			<view class="title">
				{{article.title}}
			</view>
			<view class="message_content">
				<view class="avatar_box">
					<view class="nav">
						<u-avatar :src="src" shape="circle" size="90rpx" style="margin:0 30rpx 0 0rpx"></u-avatar>
					</view>
					<view>Cesar、希泽</view>
				</view>
				<view class="operate" @click="changeOn">
					<view class="icon-like" id="iconLike">
						<svg width="28" height="28" viewBox="0 0 36 36" xmlns="http://www.w3.org/2000/svg"
							:class="this.isLike!=true ? 'like' : 'like on'">
							<path fill-rule="evenodd" clip-rule="evenodd"
								d="M9.77234 30.8573V11.7471H7.54573C5.50932 11.7471 3.85742 13.3931 3.85742 15.425V27.1794C3.85742 29.2112 5.50932 30.8573 7.54573 30.8573H9.77234ZM11.9902 30.8573V11.7054C14.9897 10.627 16.6942 7.8853 17.1055 3.33591C17.2666 1.55463 18.9633 0.814421 20.5803 1.59505C22.1847 2.36964 23.243 4.32583 23.243 6.93947C23.243 8.50265 23.0478 10.1054 22.6582 11.7471H29.7324C31.7739 11.7471 33.4289 13.402 33.4289 15.4435C33.4289 15.7416 33.3928 16.0386 33.3215 16.328L30.9883 25.7957C30.2558 28.7683 27.5894 30.8573 24.528 30.8573H11.9911H11.9902Z">
							</path>
						</svg>
					</view>
					<view class="icon-attention" id="iconAttention">
						<svg width="28" height="28" viewBox="0 0 28 28" xmlns="http://www.w3.org/2000/svg"
							:class="this.isAttention!=true ? 'attention' : 'attention on'">
							<path fill-rule="evenodd" clip-rule="evenodd"
								d="M14.045 25.5454C7.69377 25.5454 2.54504 20.3967 2.54504 14.0454C2.54504 7.69413 7.69377 2.54541 14.045 2.54541C20.3963 2.54541 25.545 7.69413 25.545 14.0454C25.545 17.0954 24.3334 20.0205 22.1768 22.1771C20.0201 24.3338 17.095 25.5454 14.045 25.5454ZM9.66202 6.81624H18.2761C18.825 6.81624 19.27 7.22183 19.27 7.72216C19.27 8.22248 18.825 8.62807 18.2761 8.62807H14.95V10.2903C17.989 10.4444 20.3766 12.9487 20.3855 15.9916V17.1995C20.3854 17.6997 19.9799 18.1052 19.4796 18.1052C18.9793 18.1052 18.5738 17.6997 18.5737 17.1995V15.9916C18.5667 13.9478 16.9882 12.2535 14.95 12.1022V20.5574C14.95 21.0577 14.5444 21.4633 14.0441 21.4633C13.5437 21.4633 13.1382 21.0577 13.1382 20.5574V12.1022C11.1 12.2535 9.52148 13.9478 9.51448 15.9916V17.1995C9.5144 17.6997 9.10883 18.1052 8.60856 18.1052C8.1083 18.1052 7.70273 17.6997 7.70265 17.1995V15.9916C7.71158 12.9487 10.0992 10.4444 13.1382 10.2903V8.62807H9.66202C9.11309 8.62807 8.66809 8.22248 8.66809 7.72216C8.66809 7.22183 9.11309 6.81624 9.66202 6.81624Z">
							</path>
						</svg>
					</view>
					<view class="icon-collect" id="iconCollect">
						<svg width="28" height="28" viewBox="0 0 28 28" xmlns="http://www.w3.org/2000/svg"
							:class="this.isCollect!=true ? 'collect' : 'collect on'">
							<path fill-rule="evenodd" clip-rule="evenodd"
								d="M19.8071 9.26152C18.7438 9.09915 17.7624 8.36846 17.3534 7.39421L15.4723 3.4972C14.8998 2.1982 13.1004 2.1982 12.4461 3.4972L10.6468 7.39421C10.1561 8.36846 9.25639 9.09915 8.19315 9.26152L3.94016 9.91102C2.63155 10.0734 2.05904 11.6972 3.04049 12.6714L6.23023 15.9189C6.96632 16.6496 7.29348 17.705 7.1299 18.7605L6.39381 23.307C6.14844 24.6872 7.62063 25.6614 8.84745 25.0119L12.4461 23.0634C13.4276 22.4951 14.6544 22.4951 15.6359 23.0634L19.2345 25.0119C20.4614 25.6614 21.8518 24.6872 21.6882 23.307L20.8703 18.7605C20.7051 17.705 21.0339 16.6496 21.77 15.9189L24.9597 12.6714C25.9412 11.6972 25.3687 10.0734 24.06 9.91102L19.8071 9.26152Z">
							</path>
						</svg>
					</view>
				</view>
			</view>
		</view>
		<!-- 分段器 -->
		<u-subsection :list="list" :current="curNow" @change="sectionChange"></u-subsection>
		<!-- 文章内容 -->
		<view v-if="curNow == 0" class="article_content">
			<u-divider text="分割线" dot></u-divider>
			<view class="content_box" style="width: 90%;margin: 0 auto; margin-bottom: 100rpx;">
				<scroll-view scroll-y="true">
					<u-parse :content="content" :selectable="true"></u-parse>
				</scroll-view>
			</view>
			<u-divider text="分割线" dot></u-divider>
		</view>
		<!-- 评论 -->
		<view v-if="curNow == 1" class="comment_content">
			<view v-if="this.user==null" class="tips"
				style="margin: 20rpx auto; width: 90%;text-align: center;font-size: 34rpx;">
				<navigator url="../../login/login" hover-class="navigator-hover">请先<text
						style="text-decoration:underline;color: blue;">登录</text></navigator>
			</view>
			<view v-else class="com">
				<hb-comment ref="hbComment" @add="add" @del="del" @like="like" :deleteTip="'确认删除？'"
					:cmData="commentData" v-if="commentData"></hb-comment>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: null,
				article: Object,
				id: '',
				src: "https://cdn.uviewui.com/uview/album/1.jpg",
				content: '',
				isLike: false,
				isCollect: false,
				isAttention: false,
				list: ['文章', '评论'],
				curNow: 0,
				commentList: null,
				// commentList: [{
				// 		"id": 1, // 唯一主键commentId
				// 		"owner": true, // 是否是拥有者，为true则可以删除，管理员全部为true
				// 		"hasLike": false, // 是否点赞
				// 		"likeNum": 2, // 点赞数量
				// 		"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797755537/0", // 评论者头像地址
				// 		"nickName": "hello", // 评论者昵称，昵称过长请在后端截断
				// 		"content": "啦啦啦啦", // 评论内容
				// 		"parentId": null, // 所属评论的唯一主键
				// 		"createTime": "2021-07-02 16:32:07" // 创建时间
				// 	},
				// 	{
				// 		"id": 2,
				// 		"owner": false,
				// 		"hasLike": false,
				// 		"likeNum": 2,
				// 		"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797761970/0",
				// 		"nickName": "寂寞无敌",
				// 		"content": "我是评论的评论",
				// 		"parentId": 1,
				// 		"createTime": "2021-07-02 17:05:50"
				// 	},
				// 	{
				// 		"id": 3,
				// 		"owner": false,
				// 		"hasLike": true,
				// 		"likeNum": 1,
				// 		"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797763270/0",
				// 		"nickName": "name111",
				// 		"content": "评论啦啦啦啦啦啦啦啦啦啦",
				// 		"parentId": null,
				// 		"createTime": "2021-07-13 09:37:50"
				// 	},
				// 	{
				// 		"id": 4,
				// 		"owner": true,
				// 		"hasLike": false,
				// 		"likeNum": 0,
				// 		"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797755537/0",
				// 		"nickName": "超长昵称超长...",
				// 		"content": "超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论超长评论",
				// 		"parentId": null,
				// 		"createTime": "2021-07-13 16:04:35"
				// 	},
				// 	{
				// 		"id": 5,
				// 		"owner": false,
				// 		"hasLike": false,
				// 		"likeNum": 0,
				// 		"avatarUrl": "https://inews.gtimg.com/newsapp_ls/0/13797755537/0",
				// 		"nickName": "超长昵称超长...",
				// 		"content": "@寂寞无敌 你怕不是个大聪明",
				// 		"parentId": 1,
				// 		"createTime": "2021-07-14 11:01:23"
				// 	}
				// ]
			}
		},
		onLoad(ev) {
			this.id = ev.id; //article_id
			uni.request({
				url: 'http://localhost:8081/waterfall/getById',
				data: {
					id: this.id,
				},
				header: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				success: (res) => {
					this.article = res.data;
					this.content = res.data.content;
				}
			});
			const user = uni.getStorageSync("access-admin");
			if (user != "") {
				this.user = JSON.parse(user);
				this.getComment(this.id, this.user.id);
			}
		},
		methods: {
			//获取当前评论
			getComment(articleId, userId) {
				uni.request({
					url: 'http://localhost:8081/comment/getComment',
					data: {
						articleId: articleId,
						userId: userId
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.commentData = {
							"readNumer": 428,
							"commentSize": res.data.length,
							"comment": this.getTree(res.data)
						}
					}
				});
			},
			//添加评论
			add(e) {
				// console.log("添加" + e.pId + "发布的" + e.content)
				uni.request({
					url: 'http://localhost:8081/comment/addComment',
					data: {
						articleId: this.id,
						userId: this.user.id,
						pId: e.pId,
						content: e.content
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data);
						location.reload();
					}
				});
			},
			like(commentId) {
				// console.log("您点击了id为" + commentId + "的评论")
				uni.request({
					url: 'http://localhost:8081/comment/likeComment',
					data: {
						commentId: commentId,
						userId: this.user.id,
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data);
						location.reload();
					}
				});
			},
			//删除评论
			del(commentId) {
				console.log("您想要删除id为" + commentId + "的评论")
				uni.request({
					url: 'http://localhost:8081/comment/delComment',
					data: {
						commentId: commentId,
						// userId: this.user.id,
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data);
						location.reload();
					}
				});
			},
			back() {
				uni.switchTab({
					url: '../index'
				})
			},
			change() {
				this.show = !this.show
			},
			tap() {
				this.isClick = 1
			},
			collect() {
				this.isCollect = 1
			},
			sectionChange(index) {
				this.curNow = index;
			},
			getTree(data) {
				let result = [];
				let map = {};
				data.forEach(item => {
					map[item.id] = item;
				});
				data.forEach(item => {
					let parent = map[item.parentId];
					if (parent) {
						(parent.children || (parent.children = [])).push(item);
					} else {
						result.push(item);
					}
				});
				return result;
			},
			changeOn(e) {
				if (e.target.id === 'iconLike') { //判断点击的是点赞
					this.isLike = !this.isLike;
				} else if (e.target.id === 'iconAttention') { //判断点击的是投币
					this.isAttention = !this.isAttention
				} else if (e.target.id === 'iconCollect') { //判断点击的是收藏
					this.isCollect = !this.isCollect
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import url("https://at.alicdn.com/t/font_2355023_6kpsmoal6sa.css");

	.title {
		width: 90%;
		margin: 0 auto;
		font-size: 40rpx;
		font-weight: 500;
		border-radius: 25rpx;
		padding: 15rpx;
		background-color: #007AFF;
		color: white;
	}

	.avatar_box {
		display: flex;
		align-items: center;
	}

	.message_content {
		width: 90%;
		margin: 30rpx auto;
		display: flex;
		align-items: center;
		justify-content: space-around;
		padding: 1em;
		max-width: 30em;
		border-radius: 0.3em;
		box-shadow: 0 0 0 1px hsla(0, 0%, 100%, .3) inset,
			0 .5em 1em rgba(0, 0, 0, 0.6);
		text-shadow: 0 1px 1px hsla(0, 0%, 100%, .3);
		background: hsla(0, 0%, 100%, .3);
	}

	.operate {
		width: 155rpx;
		display: flex;
	}

	.like,
	.attention,
	.collect {
		margin: 0 10rpx 0 10rpx;

		&.on {
			fill: #1e9de7;
		}
	}
</style>
