package top.lucian.dictionary.entity;

import lombok.Data;

@Data
public class Waterfall {
	private Integer id;
	/** 用户ID */
	private Integer userid;
	/** 图片地址 */
	private String imageUrl;
	/** 文章标题 */
	private String title;
	/** 发布的内容 */
	private String content;
	/** 点赞数量 */
	private Integer clickNumber;
	/** 是否被收藏 */
	private Integer isCollect;
	/** 判断是否被点赞 */
	private Integer isClick;

}