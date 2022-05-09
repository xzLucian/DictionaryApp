package top.lucian.dictionary.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentVO {
    private Integer id;
    private boolean owner;
    private boolean hasLike;
    private Integer likeNum;
    private String avatarUrl;
    private String nickName;
    private String content;
    private Integer parentId;
    private Date createTime;
}
