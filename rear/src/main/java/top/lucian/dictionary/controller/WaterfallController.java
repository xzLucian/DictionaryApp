package top.lucian.dictionary.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.lucian.dictionary.entity.Waterfall;
import top.lucian.dictionary.service.WaterfallService;

@RestController
@CrossOrigin
@RequestMapping("/waterfall")
public class WaterfallController {

    @Autowired
    private WaterfallService waterfallService;

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @RequestMapping("list")
    public List<Waterfall> listAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        PageHelper.startPage(pageNo, 7);
        PageInfo<Waterfall> pageInfo = new PageInfo<>(waterfallService.listAll());
        return pageInfo.getList();
    }

    /**
     * 计算总数
     *
     * @return
     */
    @RequestMapping("count")
    public int countAll() {
        return waterfallService.countAll();
    }

    /**
     * 更新收藏
     *
     * @param id
     * @param index
     * @return
     */
    @RequestMapping("updateLikes")
    public int updateLike(@RequestParam(value = "articleid") int id, @RequestParam(value = "index") int index) {
        int i = waterfallService.updateLike(id, index);

        return 200;
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("getById")
    public Waterfall getById(Integer id) {
        return waterfallService.getById(id);
    }

    /**
     * 新增，忽略null字段
     *
     * @param waterfall 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("insert")
    public int insert(@RequestBody Waterfall waterfall) {
        return waterfallService.insertIgnoreNull(waterfall);
    }

    /**
     * 修改，忽略null字段
     *
     * @param waterfall 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("update")
    public int update(@RequestBody Waterfall waterfall) {
        return waterfallService.updateIgnoreNull(waterfall);
    }

    /**
     * 删除记录
     *
     * @param waterfall 待删除的记录
     * @return 返回影响行数
     */
    @RequestMapping("delete")
    public int delete(@RequestBody Waterfall waterfall) {
        return waterfallService.delete(waterfall);
    }

}