package cn.itheima.controller;

import cn.itheima.pojo.Items;
import cn.itheima.service.ItemsService;
import cn.itheima.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * author: zzw5005
 * date: 2018/6/23 15:53
 */

@Controller
/*窄化请求映射:为了防止你和你的队友在controller方法起名的时候重名,所以相当于在url中多加了
* 一层目录,为了防止重名  例如:当前list的访问路径 localhost:8080/ssm/items/list.action
* 字符串前面分割符最好加上
* */
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    /*@RequestMapping(value="/list", method=RequestMethod.GET)*/
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
    *
    * 通过@PathVariable可以接收url中传入的参数
    * @RequestMapping("/itemEdit/{id}")中接收参数使用大括号加上变量名称，
    * @PathVariable中的变量名称要和RequestMapping中变量名称相同
    * */
    @RequestMapping("/itemEdit/{id}")
    public String itemEdit(@PathVariable("id") Integer id, HttpServletRequest request,
                           Model model) throws Exception{
        //String idStr = request.getParameter("id");
        //这里对idStr进行了类型转换
        Items items = itemsService.findItemsById(id);


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

        Items items = new Items();
        items.setId(id);
        items.setName(name);
        items.setPrice(price);
        items.setDetail(detail);
        items.setCreatetime(new Date());

        itemsService.updateItems(items);

        return "success";
    }*/

    /*@RequestMapping("updateitem")
    public String update(Items items, Model model) throws Exception{
        itemsService.updateItems(items);

        model.addAttribute("id",items.getId());
        //springMvc中请求转发:返回的字符串以forward:开头的都是请求转发
        //后面forward:itemEdit.action表示相对路径，相对路径就是相对于当前的目录，
        //当前为类上面指定的items目录。
        //在当前目录下可以使用相对路径随意跳转到某个方法中，后面forward:/itemEdit.action路径中以
        //斜杠开头的为绝对路径，绝对路径从项目名后面开始算
        return "forward:/items/itemEdit.action";
    }*/

    @RequestMapping("updateitem")
    public String update(MultipartFile pictureFile, Items items,Model model,
     HttpServletRequest request) throws Exception{
        //1.获取图片完整名称
        String fileStr = pictureFile.getOriginalFilename();
        //2.使用随机生成的字符串+源图片扩展名组成新的图片名称，防止图片重名
        String newfileName = UUID.randomUUID().toString() +
                fileStr.substring(fileStr.lastIndexOf("."));
        //3.将图片保存到硬盘
        pictureFile.transferTo(new File("F:\\javacode\\test\\" + newfileName));
        //4.将图片名称保存到数据库
        items.setPic(newfileName);
        itemsService.updateItems(items);

        return "redirect:itemEdit/"+items.getId();
        /* return "success";*/
    }

    /*@RequestMapping("updateitem")
    public String update(Items items) throws Exception{
        itemsService.updateItems(items);

        return "success";
    }*/


    //如果controller中接收的是Vo，那么页面上的input框的nam属性值等于vo的属性，属性
    @RequestMapping("/search")
    public String search(QueryVo vo) throws Exception{
        System.out.println(vo);
        return "";
    }

    @RequestMapping("/delAll")
    public String delAll(QueryVo vo) throws Exception{
        //如果是批量删除，一堆复选框，那么可以提交数组。(只有input复选框被选中的时候才能提交)
        System.out.println(vo);

        return "";
    }

    @RequestMapping("/updateAll")
    public String updateAll(QueryVo vo) throws Exception{
        System.out.println(vo);

        return "";
    }

    //导入jackson的jar包 在controller的方法中可以使用@requestBody，让springMvc将json
    //格式字符串自动转换java中的pojo
    //页面json的key要等与java中pojo的属性名称
    //controller方法返回pojo类型的对象并且用@RequestBody注解，
    //springMvc会自动将pojo对象转换成json格式字符串
    @RequestMapping("sendJson/")
    @ResponseBody
    public Items json(@RequestBody Items items) throws Exception{
        System.out.println(items);
        return items;
    }


}





















