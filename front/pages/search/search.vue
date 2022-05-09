<template>
	<view class="content">
		<!-- 		<view class="return">
			<view class="iconfont icon-back" @click="back()"></view>
		</view> -->
		<!-- 搜索框 -->
		<view class="home-list">
			<view class="word">{{keyword}}</view>
			<u-divider text="分割线" :dot="true"></u-divider>
			<view class="result_box">
				<view class="title"
					style="display: flex; justify-content: space-between;align-items: center; padding: 0 30rpx 0 30rpx;">
					<view class="result_title">搜索结果</view>
					<view v-if="exist==0" class="iconfont icon-join" @click="addVocabulary"></view>
					<view v-else class="iconfont icon-addSuccess"></view>
				</view>
				<u-picker :show="show" :columns="columns" keyName="bookname" @confirm="chooseBook" @cancel="closeBook">
				</u-picker>
				<view class="result_header">
					<!-- 单词发音和图片展示 -->
					<view class="pronounce">
						<view class="us">
							<view>英 / {{result.simple.word[0].ukphone}} /</view>
							<view class="iconfont icon-song" @click="Vioce(1)"></view>
						</view>
						<view class="uk">
							<view>美 / {{result.simple.word[0].usphone}} /</view>
							<view class="iconfont icon-song" @click="Vioce(0)"></view>
						</view>
					</view>
					<view v-if="result.pic_dict!=null" class="img" style="margin-left: 200rpx;">
						<image :src="result.pic_dict.pic[0].url" mode="aspectFill"
							style="width: 150rpx; height: 150rpx;background-color: #fdfbe8;border-radius: 25rpx;">
						</image>
					</view>
				</view>
				<!-- 选项卡 -->
				<u-sticky offset-top="50rpx">
					<u-tabs :list="listCard" :current="current" @change="change"></u-tabs>
				</u-sticky>
				<!-- 简明选项卡 -->
				<view v-if="current == 0" class="simple_content">
					<!-- 单词释义 -->
					<view class="explain_box" style="padding: 10rpx; text-align: justify;">
						<view class="explain_item" v-for="(item,index) in result.ec.word[0].trs" :key="index">
							{{item.tr[0].l.i[0]}}
						</view>
					</view>
					<!-- 单词时态 -->
					<view class="tense_box">
						<view class="tense_item" v-for="(item,index) in result.ec.word[0].wfs" :key="index">
							<text class="item_name" style="color: #777779;">{{item.wf.name}}</text>
							<text class="item_value" style="font-weight: bold;">{{item.wf.value}}</text>
						</view>
					</view>
				</view>
				<!-- collins柯林斯选项卡 -->
				<view v-if="current == 1" class="collins_content">
					<view class="sentense" v-for="(item,index) in result.collins.collins_entries[0].entries.entry"
						:key="index"
						style="background-color: #e8e4fb; margin-top: 30rpx; padding: 30rpx; border-radius: 25rpx; box-shadow: 0 10rpx #ffffff;">
						<view class="tran_entry">
							<view class="pos_entry" v-for="(proOne,i) in item.tran_entry[0]" :key="i"
								style="display: flex;font-style:italic;">
								<view class="pos">{{proOne.pos}}</view>
								<view class="pos_tips" style="margin-left: 8rpx; font-size: 20rpx;">{{proOne.pos_tips}}
								</view>
							</view>
						</view>
						<view class="exam_sents" v-for="(proTwo,i) in item.tran_entry[0].exam_sents" :key="i">
							<view class="eng_sent" style="font-weight: bold; text-align: justify;">
								{{proTwo[0].eng_sent}}
							</view>
							<view class="chn_sent" style="font-size: 25rpx; color: #939397;">"{{proTwo[0].chn_sent}}"
							</view>
						</view>
						<view class="tran" style="text-align: justify;">
							{{item.tran_entry[0].tran}}
						</view>
					</view>
				</view>
				<!-- 网络释义选项卡 -->
				<view v-if="current == 2" class="webtrans_box">
					<view class="trans_content" style="width: 90%; margin: 0 auto;">
						<view class="webtrans_items" v-for="(item,index) in result.web_trans" :key="index">
							<view class="web_translation" v-for="(proOne,i) in item" :key="i"
								style="background-color:#1CBBB4; margin-top: 15rpx; padding: 15rpx; border-radius: 25rpx;">
								<view class="key" style="font-size: 33rpx; font-weight: bold;">{{proOne.key}}</view>
								<view class="trans">
									<view class="trans_items" v-for="(proTwo,i) in proOne.trans" :key="i">
										<li style="list-style-type: circle;">{{proTwo.value}}</li>
										<view class="exam_sentence" v-for="(proThr,i) in proTwo.summary" :key="i"
											style="font-style: italic;">
											{{proThr[0]}}
										</view>
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				<!-- 英英释义选项卡 -->
				<view v-if="current == 3" class="ee_box">
					<view class="ee_content" style="width: 90%; margin: 0 auto;">
						<view class="ee_items" v-for="(item,index) in result.ee.word.trs" :key="index"
							style="margin-top: 10rpx;">
							<view class="ee_trs">
								<!-- 词性 -->
								<view class="ee_pos"
									style="padding:15rpx; background-color: #ebebfb; width: 50rpx;border-radius: 30%;">
									{{item.pos}}
								</view>
								<view class="ee_tr" v-for="(proOne,one) in item.tr" :key="one" style="margin-top: 20rpx;background-color: #f2f6ff;
								padding: 15rpx; border-radius: 25rpx;">
									<!-- 英文释义 -->
									<view class="ee_explain" style="font-weight: bold;">
										{{proOne.l.i}}
									</view>
									<!-- 例句 -->
									<view class="sen_one" v-for="(proTwo,two) in proOne" :key="two">
										<view class="sen_two" v-for="(proThr,Three) in proTwo" :key="Three">
											<view class="sen_three" v-for="(profor,four) in proThr.f" :key="four">
												<view class="exam_sen"
													style="font-size: 25rpx;font-style: italic;color: #868588;">
													"{{profor[0].i}}"
												</view>
											</view>
										</view>
									</view>
									<text>同义词</text>
									<view class="simi_one" v-for="fortwo in proOne">
										<view class="simi_two" v-for="forthr in fortwo">
											<view class="similar_words" style="font-weight: bold;color: #3951f8;">
												{{forthr.similar}}
											</view>
										</view>
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	// import {
	// 	mapState
	// } from 'vuex'
	export default {
		data() {
			return {
				keyword: '',
				// historyList: [],
				listCard: [{
					name: '简明'
				}, {
					name: '柯林斯'
				}, {
					name: '网络释义'
				}, {
					name: '英英释义'
				}],
				current: 0,
				result: null,
				is_history: true,
				is_search: true,
				isShow: true,
				show: false,
				columns: [
					// [{
					// 	id: 1,
					// 	bookname: '考研词汇'
					// }, {
					// 	id: 2,
					// 	bookname: '雅思词汇',
					// }]
				],
				exist: 0,
				user: null
			}
		},
		// computed: {
		// 	...mapState(['historyLists'])
		// },
		onLoad(e) {
			this.keyword = e.q
			this.getWord(e.q)
		},
		created() {
			const user = uni.getStorageSync("access-admin");
			if (user != "") {
				this.user = JSON.parse(user);
				const list = uni.getStorageSync("access-list" + this.user.id);
				this.columns = [JSON.parse(list)];
				// console.log(this.columns)
				this.wordIsExist()
			}
		},
		methods: {
			//清除搜索栏
			clear: function() {
				this.keyword = '';
				this.is_search = true;
			},
			//返回主页
			back: function() {
				uni.switchTab({
					url: '../index/index'
				})
			},
			//搜索单词
			getWord(word) {
				uni.request({
					url: '/web/jsonapi',
					dataType: 'jsonp',
					data: {
						jsonversion: '2',
						client: 'mobile',
						q: word,
						dicts: '{"count":99,"dicts":[["ec","ce","newcj","newjc","kc","ck","fc","cf","multle","jtj","pic_dict","tc","ct","typos","special","tcb","baike","lang","simple","wordform","exam_dict","ctc","web_search","auth_sents_part","ec21","phrs","input","wikipedia_digest","ee","collins","ugc","media_sents_part","syno","rel_word","longman","ce_new","le","newcj_sents","blng_sents_part","hh"],["ugc"],["longman"],["newjc"],["newcj"],["web_trans"],["fanyi"]]}',
						network: 'wifi',
						abtest: '2',
						xmlVersion: '5.1'
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						//将获取到的数据解析为json格式
						let message = JSON.parse(res.data);
						this.result = message;
						console.log(message.meta.dicts[0])
						if (message.meta.dicts[0] == "meta" || message.meta.dicts[0] == "typos") {
							uni.showToast({
								title: "未搜索单该单词",
								icon: "error"
							})
						} else {
							// //设置显示搜索结果
							// this.is_search = false;
							// //添加到历史记录
							// this.$store.dispatch('set_history', {
							// 	name: this.keyword
							// })
						}
					}
				});
			},
			// 切换选项卡
			change(item) {
				this.current = item.index;
			},
			//获取单词音频
			Vioce(i) {
				const innerAudioContext = uni.createInnerAudioContext();
				innerAudioContext.autoplay = true;
				innerAudioContext.src = 'http://dict.youdao.com/dictvoice?type=' + i + '&audio=' + this.keyword;
				innerAudioContext.onPlay(() => {
					console.log('开始播放');
				});
				innerAudioContext.onError((res) => {
					console.log(res.errMsg);
					console.log(res.errCode);
				})
			},
			addVocabulary() {
				if (this.user != null) {
					this.show = true;
				} else {
					uni.showToast({
						title: "请先登录",
						icon: "error"
					})
				}

			},
			showPanel() {
				this.isShow = !this.isShow;
			},
			hidePanel() {
				this.isShow = !this.isShow;
			},
			chooseBook(e) {
				// console.log(e.value[0].id + ' ' + e.value[0].bookname);
				uni.request({
					url: 'http://localhost:8081/vocabulary/add',
					data: {
						bookid: e.value[0].id,
						userid: this.user.id,
						name: this.keyword,
						english: this.result.simple.word[0].ukphone,
						american: this.result.simple.word[0].usphone,
						apology: this.result.ec.word[0].trs[0].tr[0].l.i[0],
						isReview: 0,
						isAdd: 1
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						uni.showToast({
							title: "添加成功",
							icon: "success"
						})
						this.show = false;
						location.reload()
					}
				});
			},
			closeBook() {
				this.show = false;
			},
			wordIsExist() {
				//查询单词是否加入单词本中
				uni.request({
					url: 'http://localhost:8081/vocabulary/queryIsExist',
					data: {
						userid: this.user.id,
						name: this.keyword,
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						this.exist = res.data;
					}
				});
			}
		}
	}
</script>

<style lang="scss">
	@import url("https://at.alicdn.com/t/font_2355023_shgeo5vsiun.css");

	.word {
		background-color: #0081FF;
		width: 90%;
		margin: 30rpx auto;
		font-size: 80rpx;
		border-radius: 55px;
		text-align: center;
		background: linear-gradient(145deg, #cacaca, #f0f0f0);
		box-shadow: 21rpx 21rpx 41rpx #bebebe,
			-21rpx -21rpx 41rpx #ffffff;
	}

	.return {
		width: 50rpx;
		height: 50rpx;
		margin: 30rpx 0 15rpx 30rpx;
	}

	.icon-back {
		font-size: 50rpx;
	}

	.history_title {
		width: 90%;
		margin: 20rpx auto;
		display: flex;
		justify-content: space-between;
	}

	.item {
		width: 90%;
		margin: 0 auto;
		height: 50rpx;
		background-color: #007AFF;
	}

	.label_box {
		display: flex;
		/* 设置flex布局 撑满容器的宽度自动换行 */
		flex-wrap: wrap;
		width: 90%;
		margin: 0 auto;
		height: auto;
	}

	.history_label {
		border: 3rpx solid #000000;
		border-radius: 25rpx;
		padding: 10rpx;
		margin: 10rpx;
	}

	.result_header {
		display: flex;
		width: 90%;
		margin: 0 auto;
		height: 150rpx;
	}

	.icon-song {
		color: red;
		font-weight: bold;
	}

	.us,
	.uk {
		display: flex;
		width: auto;
		border: 3rpx solid #000000;
		background-color: #eef2f4;
		border-radius: 25rpx;
		height: 50rpx;
		line-height: 50rpx;
		justify-content: space-between;
		margin-top: 19rpx;
		padding: 0 10rpx 0 15rpx;
	}

	.tense_box,
	.explain_box {
		width: 90%;
		margin: 20rpx auto;
		background-color: #ffffff;
	}

	.tense_item {
		margin: 10rpx;
		display: flex;
		justify-content: space-between;
	}

	.collins_content {
		margin: 0 auto;
		width: 90%;
	}
</style>
