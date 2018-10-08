package com.zl.sell.service;

import com.zl.sell.dto.CartDTO;
import com.zl.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductInfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findAllUp();  // 查询所有上架的商品

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
