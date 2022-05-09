package top.lucian.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.lucian.dictionary.entity.Waterfall;

@Mapper
public interface WaterfallMapper {

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<Waterfall> listAll();

    /**
     * 计算总数
     *
     * @return
     */
    int countAll();

    /**
     * 更新收藏
     *
     * @param id
     * @param isClick
     * @return
     */
    int updateLike(Integer id, Integer isClick);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    Waterfall getById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param waterfall 新增的记录
     * @return 返回影响行数
     */
    int insert(Waterfall waterfall);

    /**
     * 新增，忽略null字段
     *
     * @param waterfall 新增的记录
     * @return 返回影响行数
     */
    int insertIgnoreNull(Waterfall waterfall);

    /**
     * 修改，修改所有字段
     *
     * @param waterfall 修改的记录
     * @return 返回影响行数
     */
    int update(Waterfall waterfall);

    /**
     * 修改，忽略null字段
     *
     * @param waterfall 修改的记录
     * @return 返回影响行数
     */
    int updateIgnoreNull(Waterfall waterfall);

    /**
     * 删除记录
     *
     * @param waterfall 待删除的记录
     * @return 返回影响行数
     */
    int delete(Waterfall waterfall);

}