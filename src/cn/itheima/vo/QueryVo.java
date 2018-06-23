package cn.itheima.vo;

import cn.itheima.pojo.Items;

import java.util.List;

/**
 * author: zzw5005
 * date: 2018/6/23 16:21
 */

public class QueryVo {
    //商品对象
    private Items items;

    //批量删除使用
    private Integer[] ids;
    //批量修改使用
    private List<Items> itemsList;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }
}
