package top.lucian.dictionary.service;

import top.lucian.dictionary.entity.VideoList;
import top.lucian.dictionary.dao.VideoListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoListService {

    @Autowired
    private VideoListMapper videoListMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<VideoList> listAll() {
    	return videoListMapper.listAll();
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public VideoList getById(Integer id) {
    	return videoListMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param videoList 新增的记录
     * @return 返回影响行数
     */
    public int insert(VideoList videoList) {
    	return videoListMapper.insert(videoList);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param videoList 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(VideoList videoList) {
    	return videoListMapper.insertIgnoreNull(videoList);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param videoList 修改的记录
     * @return 返回影响行数
     */
    public int update(VideoList videoList) {
    	return videoListMapper.update(videoList);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param videoList 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(VideoList videoList) {
    	return videoListMapper.updateIgnoreNull(videoList);
    }
	
    /**
     * 删除记录
     *
     * @param videoList 待删除的记录
     * @return 返回影响行数
     */
    public int delete(VideoList videoList) {
    	return videoListMapper.delete(videoList);
    }
	
}