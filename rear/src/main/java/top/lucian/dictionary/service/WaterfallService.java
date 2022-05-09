package top.lucian.dictionary.service;

import top.lucian.dictionary.dao.WaterfallMapper;
import top.lucian.dictionary.entity.Waterfall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterfallService {

    @Autowired
    private WaterfallMapper waterfallMapper;
    /**
     * 计算总数
     *
     * @return
     */
    public int countAll() {
        return waterfallMapper.countAll();
    }

    /**
     * 更新收藏
     *
     * @param articleid
     * @param index
     * @return
     */
    public int updateLike(Integer articleid, Integer index) {
        return waterfallMapper.updateLike(articleid, index);
    }
    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Waterfall> listAll() {
    	return waterfallMapper.listAll();
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Waterfall getById(Integer id) {
    	return waterfallMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param waterfall 新增的记录
     * @return 返回影响行数
     */
    public int insert(Waterfall waterfall) {
    	return waterfallMapper.insert(waterfall);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param waterfall 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Waterfall waterfall) {
    	return waterfallMapper.insertIgnoreNull(waterfall);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param waterfall 修改的记录
     * @return 返回影响行数
     */
    public int update(Waterfall waterfall) {
    	return waterfallMapper.update(waterfall);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param waterfall 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Waterfall waterfall) {
    	return waterfallMapper.updateIgnoreNull(waterfall);
    }
	
    /**
     * 删除记录
     *
     * @param waterfall 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Waterfall waterfall) {
    	return waterfallMapper.delete(waterfall);
    }
	
}