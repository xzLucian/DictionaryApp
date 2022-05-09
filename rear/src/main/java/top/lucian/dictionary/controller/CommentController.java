package top.lucian.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lucian.dictionary.VO.CommentVO;
import top.lucian.dictionary.entity.Comment;
import top.lucian.dictionary.service.CommentService;
import top.lucian.dictionary.service.CustomerService;
import top.lucian.dictionary.util.Result;
import top.lucian.dictionary.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CustomerService customerService;


    @RequestMapping("/getMessage")
    public void getAll() {
        int count = commentService.count(1);
        System.out.println(count);
    }

    @RequestMapping("/getComment")
    public List<HashMap<String, Object>> getMap(Integer articleId, Integer userId) {
        System.out.println(articleId + "--" + userId);
        List<HashMap<String, Object>> commentVOS = new ArrayList<>();
        List<Comment> comments = commentService.getAllByArticleId(articleId);
        for (Comment c : comments) {
            CommentVO commentVO = new CommentVO();
            HashMap<String, Object> map = new HashMap<>();
            //设置id属性
            commentVO.setId(c.getId());
            map.put("id", commentVO.getId());
            map.put("owner", c.getCommentUserId() != userId ? false : true);
            //先根据commentId获取到like中的值
            String str = commentService.like(commentVO.getId());
            //将like的值转换成整型数组
            int[] ints = StringUtil.stringConvertInt(str);
            //查看当前用户id是否存在数组中
            map.put("hasLike", StringUtil.inArray(ints, userId));
            map.put("likeNum", str == null ? 0 : ints.length);
            //获取评论人员的信息
            Map<String, Object> users = customerService.QueryUserById(c.getCommentUserId());
            map.put("nickName", users.get("username"));
            map.put("avatarUrl", users.get("imageUrl"));
            commentVO.setContent(c.getContent());
            map.put("content", commentVO.getContent());
            commentVO.setParentId(c.getParentId());
            map.put("parentId", commentVO.getParentId());
            commentVO.setCreateTime(c.getCreateTime());
            map.put("createTime", commentVO.getCreateTime());
            commentVOS.add(map);
        }
        return commentVOS;
    }

    @RequestMapping("/addComment")
    public Result addComment(Integer articleId, Integer userId, Integer pId, String content) {
        System.out.println(articleId + "-" + userId + "-" + pId + "-" + content);
        boolean b = commentService.addComment(articleId, userId, pId, content);
        if (b) {
            return new Result(200, "添加成功");
        } else {
            return new Result(500, "添加失败");
        }
    }

    @RequestMapping("/likeComment")
    public Result likeComment(Integer commentId, Integer userId) {
        boolean b = commentService.likeComment(commentId, userId);
        if (b) {
            return new Result(200, "点赞成功");
        } else {
            return new Result(500, "点赞失败");
        }
    }

    @RequestMapping("/delComment")
    public Result delComment(Integer commentId) {
        System.out.println(commentId);
        boolean parent = commentService.delCommentById(commentId);
        boolean child = commentService.deleteChildCommentById(commentId);
        return new Result(200,"删除评论");
    }
}
