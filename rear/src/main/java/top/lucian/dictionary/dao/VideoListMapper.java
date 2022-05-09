package top.lucian.dictionary.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import top.lucian.dictionary.entity.VideoList;

@Mapper
public interface VideoListMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<VideoList> listAll();


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	VideoList getById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param videoList 新增的记录
     * @return 返回影响行数
     */
	int insert(VideoList videoList);
	
	/**
     * 新增，忽略null字段
     *
     * @param videoList 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(VideoList videoList);
	
	/**
     * 修改，修改所有字段
     *
     * @param videoList 修改的记录
     * @return 返回影响行数
     */
	int update(VideoList videoList);
	
	/**
     * 修改，忽略null字段
     *
     * @param videoList 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(VideoList videoList);
	
	/**
     * 删除记录
     *
     * @param videoList 待删除的记录
     * @return 返回影响行数
     */
	int delete(VideoList videoList);
	
}