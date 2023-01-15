package com.itheima.service.impl;/*
 *
 * @Param
 */

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.menu;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.建立sqlSessionFactory工厂
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.释放资源
        sqlSession.close();

        return brands;
    }

    /**
     * 添加Brand
     * @return
     */
    @Override
    public void addBrand(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.addBrand(brand);
        sqlSession.commit();//提交事务

        //5.释放资源
        sqlSession.close();

    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }

    /**
     * 实现分页查找
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Brand> selectByPages(int currentPage, int pageSize) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        int total = mapper.totalCount();
        int begin = (currentPage - 1) * pageSize;
        List<Brand> brands = mapper.selectByPages(begin,pageSize);

        sqlSession.close();

        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(total);
        return brandPageBean;
    }

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageBean<Brand> selectByPagesAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //处理brand条件
        List<Brand> brands;
        if(brand == null){
            brands = mapper.selectByPages(begin,size);
        }else {
            String brandName = brand.getBrandName();
            if (brandName != null && brandName.length() > 0) {
                brand.setBrandName("%" + brandName + "%");
            }

            String companyName = brand.getCompanyName();
            if (companyName != null && companyName.length() > 0) {
                brand.setCompanyName("%" + companyName + "%");
            }


            //5.查询当前页数据
            brands = mapper.selectByPagesAndCondition(begin, size, brand);
        }
        //6.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);
        sqlSession.close();

        //7.封装PageBean
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(totalCount);
        return brandPageBean;
    }


    /**
     * 实现修改品牌
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.updateBrand(brand);
        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * 删除品牌信息
     * @param id
     */
    @Override
    public void delete(int id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.delete(id);
        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * 测试级联选择器
     */
    @Override
    public List<menu> selectAllOfMenu() {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3.获取SqlMapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<menu> menus = mapper.selectAllOfMenu();

        sqlSession.close();
        return menus;
    }
}
