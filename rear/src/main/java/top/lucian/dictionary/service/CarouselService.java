package top.lucian.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucian.dictionary.entity.Carousel;
import top.lucian.dictionary.dao.CarouselMapper;

import java.util.List;

@Service
public class CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<Carousel> listAll() {
    	return carouselMapper.listAll();
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    public Carousel getById(Integer id) {
    	return carouselMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
    public int insert(Carousel carousel) {
    	return carouselMapper.insert(carousel);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
    public int insertIgnoreNull(Carousel carousel) {
    	return carouselMapper.insertIgnoreNull(carousel);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
    public int update(Carousel carousel) {
    	return carouselMapper.update(carousel);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
    public int updateIgnoreNull(Carousel carousel) {
    	return carouselMapper.updateIgnoreNull(carousel);
    }
	
    /**
     * 删除记录
     *
     * @param carousel 待删除的记录
     * @return 返回影响行数
     */
    public int delete(Carousel carousel) {
    	return carouselMapper.delete(carousel);
    }
	
}