package com.itheima.service;/*
 *
 * @Param
 */

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.menu;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandService{
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     * @return
     */
    void addBrand(Brand brand);


    /**
     * 批量删除
     */
    void deleteByIds(int[] ids);

    /**
     * 获取页数的数据
     */
    PageBean<Brand> selectByPages(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Brand> selectByPagesAndCondition(int currentPage, int pageSize, Brand brand);

    /**
     * 修改品牌
     */
    void updateBrand(Brand brand);

    /**
     * 删除品牌信息
     */
    void delete(int id);

    /**
     * 测试级联选择器
     */
    List<menu> selectAllOfMenu();
}
