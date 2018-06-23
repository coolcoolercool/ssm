package cn.itheima.controller;

import cn.itheima.pojo.Items;
import cn.itheima.service.ItemsService;
import cn.itheima.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

/**
 * author: zzw5005
 * date: 2018/6/23 15:53
 */

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/list")
    public ModelAndView itemList() throws Exception{
        List<Items> list = itemsService.list();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("itemList", list);

        modelAndView.setViewName("itemList");

        return modelAndView;
    }

    /*
    * springMvc中默认支持的参数类型，也就是说在controller方法中可以加入这些也可以不加入这些，
    * 加不加看个人需求们都行
    * HttpServletReuquest
    * HttpServletResponse
    * HttpSession
    * Model
    * */
    @RequestMapping("/itemEdit")
    public String itemEdit(HttpServletRequest request,
                           Model model) throws Exception{
        String idStr = request.getParameter("id");
        //这里对idStr进行了类型转换
        Items items = itemsService.findItemsById(Integer.parseInt(idStr));


        //Model模型:模型中放入了返回给页面的数据
        //model底层其实就是用的request域来床底数据，但是对request域进行了扩展，
        model.addAttribute("item", items);

        //如果springMvc方法返回一个简单的string字符串，
        //那么springmvc就会认为这个字符串就是页面的名称

        return "editItem";
    }

    //springMvc可以直接收基本数据类型，包括string，pringMvc可以帮你自动
    //进行类型转换。
    //controller方法接收的参数的变量名称必须等于页面撒谎给你input框的nam属性值
    //public String update(Integer id, String name,
    // Float price, String detail) throws Exception{
    //springMvc可以直接接收pojo类型，要求页面上input框的name属性名称必须等于属性名称
    /*@RequestMapping("updateitem")
    public String update(Integer id, String name,Float price, String detail) throws Exception{
        *//*itemsService.updateItems(items);*//*
        Items items = new Items();
        items.setId(id);
        items.setName(name);
        items.setPrice(price);
        items.setDetail(detail);
        items.setCreatetime(new Date());

        itemsService.updateItems(items);

        return "success";
    }*/

    @RequestMapping("updateitem")
    public String update(Items items) throws Exception{
        itemsService.updateItems(items);

        return "success";
    }


    //如果controller中接收的是Vo，那么页面上的input框的nam属性值等于vo的属性，属性
    @RequestMapping("/search")
    public String search(QueryVo vo) throws Exception{
        System.out.println(vo);
        return "";
    }



}





















