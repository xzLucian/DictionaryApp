package top.lucian.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucian.dictionary.dao.CommentMapper;
import top.lucian.dictionary.entity.Comment;
import top.lucian.dictionary.util.StringUtil;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getAllByArticleId(Integer articleId) {
        return commentMapper.getAllByArticleId(articleId);
    }

    public int count(Integer articleId) {
        return commentMapper.count(articleId);
    }

    public String like(Integer commentId) {
        return commentMapper.like(commentId);
    }

    public boolean addComment(Integer articleId, Integer userId, Integer pId, String content) {
        return commentMapper.addComment(articleId, userId, pId, content);
    }

    public boolean likeComment(Integer articleId, Integer userId) {
        //先根据commentId获取到like中的值
        String like = like(articleId);
        //将like的值转换成整型数组
        int[] ints = StringUtil.stringConvertInt(like);
        if (like == null) {
            return commentMapper.likeComment(articleId, userId + "" + ",");
        } else {
            //判断用户是否点过赞
            boolean b = StringUtil.inArray(ints, userId);
            if (b) {
                return b;
            } else {
                return commentMapper.likeComment(articleId, like + userId + "" + ",");
            }
        }
    }

    public boolean delCommentById(Integer commentId) {
        return commentMapper.delCommentById(commentId);
    }

    //删除子评论
    public boolean deleteChildCommentById(Integer pId) {
        return commentMapper.deleteChildCommentById(pId);
    }
}
