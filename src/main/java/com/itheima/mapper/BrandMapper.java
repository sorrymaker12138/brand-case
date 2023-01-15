package com.itheima.mapper;

import com.itheima.pojo.Brand;
import com.itheima.pojo.menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select  * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("insert into tb_brand values(null,#{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    void addBrand(Brand brand);

    /**
     *  完成批量删除
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     */
    @Select("select * from tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPages(@Param("begin")int begin, @Param("size")int size);

    /**
     * 获取总的条数
     */
    @Select("select count(*) from tb_brand ")
    int totalCount();

    /**
     * 条件 分页查询
     */
    List<Brand> selectByPagesAndCondition(@Param("begin")int begin, @Param("size")int size,@Param("brand") Brand brand);

    /**
     * 获取总的条数
     */
    int selectTotalCountByCondition(Brand brand);

    /**
     * 修改品牌信息
     */
    @Update("update tb_brand set brand_name = #{brandName}, company_name = #{companyName},ordered = #{ordered},status=#{status},description = #{description} where id = #{id}")
    void updateBrand(Brand brand);

    /**
     * 删除前品牌信息
     */
    @Delete("delete from tb_brand where id = #{id}")
    void delete(@Param("id") int id);


    /**
     * 测试级联选择器
     */
    @Select("select * from menu")
    //@ResultMap("menu")
    List<menu> selectAllOfMenu();
}
