package cn.itheima.service;

import cn.itheima.pojo.Items;

import java.util.List;

/**
 * author: zzw5005
 * date: 2018/6/23 16:21
 */

public interface ItemsService {

    public List<Items> list() throws Exception;

    public Items findItemsById(Integer id) throws Exception;

    public void updateItems(Items items) throws Exception;
}
