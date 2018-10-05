package com.zl.sell.dao;

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
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = dao.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void insertOneTest() {
        ProductCategory productCategory = new ProductCategory("女生最爱", 3);
        ProductCategory res = dao.save(productCategory);
        System.out.println(res);
        assertNotNull(res);
    }

    @Test
    public void updateOneTest() {
        ProductCategory productCategory = dao.findOne(3);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(4);
        System.out.println(productCategory);
        dao.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> typeList = Arrays.asList(2,3,4,5);
        List<ProductCategory> res = dao.findByCategoryTypeIn(typeList);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
        assertNotEquals(0, res.size());
    }

}