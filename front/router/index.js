router.beforeEach(to,from,next)=>{
	if(to.path.startsWith('/login')){
		uni.removeStorageSync("access-login")
		next()
	}else{
		let admin = JSON.parse(uni.getStorageSync("access-admin"))
		if(!admin){
			next({path:/login''})
		}else{
			
		}
	}
}