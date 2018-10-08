package com.zl.sell.dao;

import com.zl.sell.pojo.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("order_detail_000002");
        orderDetail.setOrderId("000002");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("000001");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(5);
        OrderDetail res = dao.save(orderDetail);
        assertNotNull(res);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = dao.findByOrderId("000001");
        System.out.println(orderDetailList);
        assertNotEquals(0, orderDetailList.size());
    }

}