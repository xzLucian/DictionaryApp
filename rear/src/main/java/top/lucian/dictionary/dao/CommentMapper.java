package top.lucian.dictionary.dao;

import org.apache.ibatis.annotations.Mapper;
import top.lucian.dictionary.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    //根据articleId查询所有评论
    List<Comment> getAllByArticleId(Integer articleId);
    //计算评论数量
    int count(Integer articleId);
    //查询点赞数量以及点赞人群
    String like(Integer commentId);
    //添加评论
    boolean addComment(Integer articleId,Integer userId,Integer pId,String content);
    //评论点赞
    boolean likeComment(Integer commentId,String userId);
    //删除父评论
    boolean delCommentById(Integer commentId);
    //删除子评论
    boolean deleteChildCommentById(Integer pId);
}
