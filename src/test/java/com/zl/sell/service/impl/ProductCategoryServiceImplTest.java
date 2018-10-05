package com.zl.sell.service.impl;

import com.zl.sell.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(1);
        System.out.println(productCategory);
        assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> res = productCategoryService.findAll();
        System.out.println(res.size());
        assertNotEquals(0, res.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> res = productCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        System.out.println(res.size());
        assertNotEquals(0, res.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专项", 10);
        ProductCategory res = productCategoryService.save(productCategory);
        assertNotNull(res);
    }
}