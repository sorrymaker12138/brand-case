package com.itheima.web.servlet;/*
 *
 * @Param
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.menu;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.RequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAllOfMenu(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        List<menu> menus = brandService.selectAllOfMenu();
        String s = JSON.toJSONString(menus);

        //3.写入数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }
    public void selectAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {

        //1.调用查询
        List<Brand> brands = brandService.selectAll();

        //2.将java集合转为json
        String s = JSON.toJSONString(brands);

        //3.写入数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }

    public void add(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //接收数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        System.out.println("接收到的数据 = " + s);

        Brand brand = JSON.parseObject(s, Brand.class);

        brandService.addBrand(brand);

        //响应数据
        resp.getWriter().write("success");

    }

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        //1.接收数据 [1,2,3]
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        //转为int数组
        int[] ids = JSON.parseObject(s, int[].class);

        brandService.deleteByIds(ids);

        resp.getWriter().write("success");
    }


    /**
     * 获取页数的数据
     */
    public void selectByPages(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        //获取数据当前页码与展示条数 url?currentPage=1&pageSize=5
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));

        PageBean<Brand> pageBean = brandService.selectByPages(currentPage, pageSize);
        String StrJSON = JSON.toJSONString(pageBean);

        //返回数据的JSON格式
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(StrJSON);
    }

    /**
     * 分页条件查询
     */
    public void selectByPagesAndCondition(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        //获取数据当前页码与展示条数 url?currentPage=1&pageSize=5
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));

        req.setCharacterEncoding("UTF-8");
        //接收brand数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //s = new String(s.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(s);
        Brand brand = JSONObject.parseObject(s, Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPagesAndCondition(currentPage, pageSize,brand);
        String StrJSON = JSON.toJSONString(pageBean);

        //返回数据的JSON格式
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(StrJSON);
    }


    /**
     * 修改品牌
     */
    public void updateBrand(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Brand brand = JSONObject.parseObject(s, Brand.class);
        brandService.updateBrand(brand);

        //返回数据的JSON格式
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write("success");
    }

    /**
     * 删除品牌信息
     */
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        int id = Integer.parseInt(req.getParameter("id"));

        brandService.delete(id);

        //返回数据的JSON格式
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write("success");
    }
}
