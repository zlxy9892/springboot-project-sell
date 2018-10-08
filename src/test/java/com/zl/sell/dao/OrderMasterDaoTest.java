package com.zl.sell.dao;

import com.zl.sell.pojo.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("000003");
        orderMaster.setBuyerName("张海啸");
        orderMaster.setBuyerAddress("石杨路108号世茂君望墅");
        orderMaster.setBuyerOpenid("wechat_002");
        orderMaster.setBuyerPhone("18652003202");
        orderMaster.setOrderAmount(new BigDecimal(100.5));
        OrderMaster res = dao.save(orderMaster);
        System.out.println(res);
        assertNotNull(res);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> res = dao.findByBuyerOpenid("wechat_001", pageRequest);
        System.out.println(res.getContent());
        assertNotEquals(0, res.getTotalElements());
    }

}