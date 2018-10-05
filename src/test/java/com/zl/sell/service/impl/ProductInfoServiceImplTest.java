package com.zl.sell.service.impl;

import com.zl.sell.enums.ProductStatusEnum;
import com.zl.sell.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo res = productInfoService.findOne("000001");
        System.out.println(res);
        assertNotNull(res);
    }

    @Test
    public void findAllUp() {
        List<ProductInfo> res = productInfoService.findAllUp();
        System.out.println(res.size());
        assertNotEquals(0, res.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> res = productInfoService.findAll(request);
        System.out.println(res.getTotalElements());
        System.out.println(res.getTotalPages());
        assertNotNull(res);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("000002");
        productInfo.setProductName("西湖牛肉粥");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("非常好喝的粥");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo res = productInfoService.save(productInfo);
        assertNotNull(res);
    }
}