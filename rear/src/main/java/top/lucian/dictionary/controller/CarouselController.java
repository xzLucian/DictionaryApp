package top.lucian.dictionary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.lucian.dictionary.entity.Carousel;
import top.lucian.dictionary.service.CarouselService;


@RestController
@RequestMapping("/carousel")
@CrossOrigin
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("/list")
    public List<Carousel> listAll() {
        return carouselService.listAll();
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Carousel getById(Integer id) {
        return carouselService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("insert")
    public int insert(@RequestBody Carousel carousel) {
        return carouselService.insertIgnoreNull(carousel);
    }    
      
    /**
     * 修改，忽略null字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(@RequestBody Carousel carousel) {
        return carouselService.updateIgnoreNull(carousel);
    }
    
    /**
     * 删除记录
     *
     * @param carousel 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("delete")
    public int delete(@RequestBody Carousel carousel) {
        return carouselService.delete(carousel);
    }
    
}