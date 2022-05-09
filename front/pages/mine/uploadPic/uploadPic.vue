<template>
	<view>
		修改头像
		<u-upload :fileList="fileList3" @afterRead="afterRead" @delete="deletePic" name="3" multiple :maxCount="10"
			:previewFullImage="true" v-on:on-success = "upload"></u-upload>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				fileList3: [{
					url: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
				}],

			}
		},
		methods: {
			upload(){
				console.log("aaa")
			}

		}
	}
</script>

<style>

</style>
