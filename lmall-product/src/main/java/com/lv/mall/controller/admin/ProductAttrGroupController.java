package com.lv.mall.controller.admin;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.ProductAttrGroup;
import com.lv.mall.service.ProductAttrGroupService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * (ProductAttrGroup)表控制层
 *
 * @author lv
 * @since 2023-02-23 18:08:51
 */
@RestController
@RequestMapping("product/productAttrGroup")
public class ProductAttrGroupController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductAttrGroupService productAttrGroupService;

    /**
     * 根据三级分类的id查询所有的属性分组
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param id 查询实体
     * @return 所有数据
     */
    @GetMapping("/{id}")
    public R selectAll(Page<ProductAttrGroup> page, @PathVariable Long id, String key) {
        return success(this.productAttrGroupService.getPageById(page, id, key));
    }

    /**
     * 新增数据
     *
     * @param productAttrGroup 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ProductAttrGroup productAttrGroup) {
        return success(this.productAttrGroupService.save(productAttrGroup));
    }

    /**
     * 修改数据
     *
     * @param productAttrGroup 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody ProductAttrGroup productAttrGroup) {
        return success(this.productAttrGroupService.updateById(productAttrGroup));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.productAttrGroupService.removeByIds(idList));
    }
}

