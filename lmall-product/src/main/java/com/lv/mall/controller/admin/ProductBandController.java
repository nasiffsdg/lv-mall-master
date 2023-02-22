package com.lv.mall.controller.admin;

import com.lv.mall.entity.ProductBand;
import com.lv.mall.service.ProductBandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProductBand)表控制层
 *
 * @author lv
 * @since 2023-02-22 23:41:16
 */
@RestController
@RequestMapping("productBand")
public class ProductBandController {
    /**
     * 服务对象
     */
    @Resource
    private ProductBandService productBandService;

    /**
     * 查询
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<List<ProductBand>> queryByPage() {
        return ResponseEntity.ok(this.productBandService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ProductBand> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.productBandService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productBand 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Boolean> add(ProductBand productBand) {
        return ResponseEntity.ok(this.productBandService.save(productBand));
    }

    /**
     * 编辑数据
     *
     * @param productBand 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Boolean> edit(ProductBand productBand) {
        return ResponseEntity.ok(this.productBandService.updateById(productBand));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.productBandService.removeById(id));
    }

}

