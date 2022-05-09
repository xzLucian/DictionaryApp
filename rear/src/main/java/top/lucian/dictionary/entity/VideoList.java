package top.lucian.dictionary.entity;

public class VideoList {
	private Integer id;
	/** 视频名称 */
	private String name;
	/** 视频地址 */
	private String videoUrl;
	/** 观看次数 */
	private Integer watchNum;
	/** 视频首图 */
	private String poster;
	/** 视频标题 */
	private String title;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	public String getVideoUrl() {
		return this.videoUrl;
	}
	
	public void setWatchNum(Integer watchNum) {
		this.watchNum = watchNum;
	}
	
	public Integer getWatchNum() {
		return this.watchNum;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getPoster() {
		return this.poster;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        VideoList that = (VideoList) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "VideoList{" +
				"id=" + id +
						",name='" + name + "'" + 
						",videoUrl='" + videoUrl + "'" + 
						",watchNum='" + watchNum + "'" + 
						",poster='" + poster + "'" + 
						",title='" + title + "'" + 
		                '}';
    }
	
}