<template>
	<view>
		<!-- <u-navbar title="设置" @rightClick="rightClick" :autoBack="true"></u-navbar> -->
		<u-cell-group>
			<u-cell size="large" title="头像" isLink @click="show=true">
				<u-avatar :src="src" shape="circle" size="100rpx" slot="right-icon">
				</u-avatar>
			</u-cell>
			<u-cell size="large" title="昵称" :value="nickname" isLink @click="edit()"></u-cell>
			<u-cell size="large" title="修改密码"figma
			 isLink @click="modifyPassword()"></u-cell>
			<!-- 性别 -->
			<u-cell size="large" title="性别" :value="ChooseSex" isLink @click="sexColumn=true"></u-cell>
			<u-picker :show="sexColumn" :columns="sexColumns" @confirm="sexConfirm" @cancel="sexClose"></u-picker>
			<!-- 生日 -->
			<u-cell size="large" title="生日" :value="birthday" isLink @click="BirthdayColumn=true"></u-cell>
			<u-datetime-picker :show="BirthdayColumn" v-model="value1" mode="date" @confirm="birthdayConfirm"
				@cancel="birthdayClose"></u-datetime-picker>
			<!-- 地区 -->
			<u-cell size="large" title="地区" :value="zone" isLink @click="zoneColumn=true"></u-cell>
			<u-picker :show="zoneColumn" ref="uPicker" :columns="columns" @confirm="zoneConfirm" @cancel="zoneClose"
				@change="changeHandler">
			</u-picker>
			<!-- 简介 -->
			<u-cell size="large" title="简介"></u-cell>

			<u--textarea v-model="introduction" placeholder="请输入内容" count style="margin: 20rpx auto; width: 90%;">
			</u--textarea>
			<view class="btn" style="display: flex;">
				<u-button type="primary" size="normal" text="保存" shape="circle" @click="submit"></u-button>
				<u-button type="primary" size="normal" text="退出" shape="circle" @click="logout"></u-button>
			</view>
		</u-cell-group>
		<!-- 头像弹窗 -->
		<u-action-sheet :actions="list" :closeOnClickOverlay="true" :closeOnClickAction="true" :show="show"
			@close="close" @select="selectClick"></u-action-sheet>


	</view>
</template>

<script>
	export default {
		onLoad: function(e) {
			this.newNickName = e.username;
			this.newPass = e.password;
		},
		data() {
			return {
				sexColumn: false,
				sexColumns: [
					['男', '女']
				],
				ChooseSex: '男',
				BirthdayColumn: false,
				value1: Number(new Date()),
				birthday: '',
				zoneColumn: false,
				columns: [
					['中国', '美国'],
					['深圳', '厦门', '上海', '拉萨']
				],
				columnData: [
					['深圳', '厦门', '上海', '拉萨'],
					['得州', '华盛顿', '纽约', '阿拉斯加']
				],
				zone: '',
				introduction: '',
				// src: uni.getStorageSync('src'),
				src: "https://cdn.uviewui.com/uview/album/1.jpg",
				list: [{
					name: '修改头像'
				}],
				show: false,
				user: null,
				nickname: 'Cesar',
				newNickName: '',
				Pass: '',
				newPass: '',
				imageUrl: ''
			}
		},
		created() {
			const user = uni.getStorageSync("access-admin");
			this.user = JSON.parse(user);
			this.Pass = this.user.password;
			if (this.user != null) {
				this.src = this.user.imageUrl;
				if (this.nickname == this.newNickName || this.newNickName == undefined || this.newNickName == '') {
					this.nickname = this.user.username;
				} else {
					this.nickname = this.newNickName;
				}
				if (this.Pass == this.newPass || this.newPass == undefined || this.newPass == '') {
					this.Pass = this.user.password;
				} else {
					this.Pass = this.newPass;
				}
				this.ChooseSex = this.user.sex;
				this.zone = this.user.city;
				this.birthday = this.user.birthday;
				this.introduction = this.user.slogan;
			}
		},
		methods: {
			modifyPassword() {
				uni.navigateTo({
					url: '../modifyPass/modifyPass'
				})
			},
			// 回调参数为包含columnIndex、value、values
			sexConfirm(e) {
				// console.log('confirm', e)
				this.ChooseSex = e.value[0];
				this.sexColumn = false
			},
			sexClose() {
				this.sexColumn = false
			},
			birthdayConfirm(e) {
				const timeFormat = uni.$u.timeFormat
				this.birthday = timeFormat(e.value, 'yyyy-mm-dd');
				this.BirthdayColumn = false
			},
			birthdayClose() {
				this.BirthdayColumn = false
			},
			changeHandler(e) {
				const {
					columnIndex,
					value,
					values, // values为当前变化列的数组内容
					index,
					// 微信小程序无法将picker实例传出来，只能通过ref操作
					picker = this.$refs.uPicker
				} = e
				// 当第一列值发生变化时，变化第二列(后一列)对应的选项
				if (columnIndex === 0) {
					// picker为选择器this实例，变化第二列对应的选项
					picker.setColumnValues(1, this.columnData[index])
				}
			},
			// 回调参数为包含columnIndex、value、values
			zoneConfirm(e) {
				// console.log('confirm', e)
				this.zone = e.value[0] + ' ' + e.value[1]
				this.zoneColumn = false
			},
			zoneClose() {
				this.zoneColumn = false
			},
			edit() {
				uni.navigateTo({
					url: '../editName/editName'
				})
			},
			close() {
				this.show = false
			},
			selectClick(index) {
				uni.chooseImage({
					count: 1, //限制图片上传数量，封顶9张
					success: (res) => { //拿到图片的返回数据
						console.log(res.tempFiles)
						this.src = res.tempFilePaths[0]
						const tempFilePaths = res.tempFilePaths[0];
						uni.uploadFile({
							url: 'http://localhost:8081/customer/upload',
							filePath: tempFilePaths,
							name: 'file',
							success: (res) => {
								console.log(res.data)
								var jsonObject = JSON.parse(res.data);
								this.src = jsonObject.data.src;
							}

						});
					},
					fail() {
						console.log("您取消了上传头像")
					}
				})
			},
			logout() {
				uni.showModal({
					title: '提示',
					content: '确定退出？',
					cancelText: "取消", // 取消按钮的文字  
					confirmText: "确认", // 确认按钮的文字  
					showCancel: true, // 是否显示取消按钮，默认为 true
					confirmColor: '#f55850',
					cancelColor: '#39B54A',
					success: (res) => {
						if (res.confirm) {
							console.log('comfirm') //点击确定之后执行的代码
							uni.removeStorageSync("access-admin")
							uni.switchTab({
								url: '../mine',
								success() {
									console.log("跳转成功")
									location.reload()
								}
							})
						} else {
							console.log('cancel') //点击取消之后执行的代码
						}
					}
				})
			},
			submit() {
				uni.request({
					url: 'http://localhost:8081/customer/updateUserInfo',
					data: {
						id: this.user.id,
						username: this.nickname,
						password: this.Pass,
						imageUrl: this.src,
						slogan: this.introduction,
						sex: this.ChooseSex,
						birthday: this.birthday, //String类型
						city: this.zone,
						token: '',
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						console.log(res.data)
						if (res.data) {
							uni.showToast({
								title: "保存成功",
								icon: "success"
							})
							uni.setStorageSync('access-admin', JSON.stringify(res.data));
						}
					},
					fail: () => {

					}
				});
			}
		}
	}
</script>

<style>

</style>
