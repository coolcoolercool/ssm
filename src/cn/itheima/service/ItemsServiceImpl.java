package cn.itheima.service;

import cn.itheima.dao.ItemsMapper;
import cn.itheima.pojo.Items;
import cn.itheima.pojo.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: zzw5005
 * date: 2018/6/23 16:21
 */

@Service
public class ItemsServiceImpl implements ItemsService{
    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<Items> list() throws Exception {
        //如果不需要任何查询条件，直接将example对象new出来即可
        ItemsExample example = new ItemsExample();
        List<Items> list = itemsMapper.selectByExampleWithBLOBs(example);

        return list;
    }

    @Override
    public Items findItemsById(Integer id) throws Exception {
       Items items = itemsMapper.selectByPrimaryKey(id);

        return items;
    }

    @Override
    public void updateItems(Items items) throws Exception {
        //detail是大文本类型，带有BLOB的会保存detail的部分
        itemsMapper.updateByPrimaryKeyWithBLOBs(items);
    }
}

























