package com.lv.mall.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.lv.mall.entity.ProductAttrGroup;

/**
 * (ProductAttrGroup)表数据库访问层
 *
 * @author lv
 * @since 2023-02-23 18:08:51
 */
public interface ProductAttrGroupMapper extends BaseMapper<ProductAttrGroup> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductAttrGroup> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProductAttrGroup> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductAttrGroup> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProductAttrGroup> entities);

}

