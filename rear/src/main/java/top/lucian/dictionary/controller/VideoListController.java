package top.lucian.dictionary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import top.lucian.dictionary.entity.VideoList;
import top.lucian.dictionary.service.VideoListService;

@RestController
@CrossOrigin
@RequestMapping("/videoList")
public class VideoListController {

    @Autowired
    private VideoListService videoListService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<VideoList> listAll() {
        return videoListService.listAll();
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public VideoList getById(Integer id) {
        return videoListService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param videoList 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("insert")
    public int insert(@RequestBody VideoList videoList) {
        return videoListService.insertIgnoreNull(videoList);
    }    
      
    /**
     * 修改，忽略null字段
     *
     * @param videoList 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(@RequestBody VideoList videoList) {
        return videoListService.updateIgnoreNull(videoList);
    }
    
    /**
     * 删除记录
     *
     * @param videoList 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("delete")
    public int delete(@RequestBody VideoList videoList) {
        return videoListService.delete(videoList);
    }
    
}