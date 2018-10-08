package com.zl.sell.controller;

import com.zl.sell.pojo.ProductCategory;
import com.zl.sell.pojo.ProductInfo;
import com.zl.sell.service.ProductCategoryService;
import com.zl.sell.service.ProductInfoService;
import com.zl.sell.utils.ResultVOUtil;
import com.zl.sell.vo.ProductInfoVO;
import com.zl.sell.vo.ProductsVO;
import com.zl.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 买家查询的商品列表 (1.查询所有上架商品; 2.查询类目; 3.数据拼装)
     * @return ProductList
     */
    @RequestMapping("/list")
    public ResultVO list() {
        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findAllUp();

        // 2.查询类目
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        // 3.数据拼装
        List<ProductsVO> productsVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductsVO productsVO = new ProductsVO();
            productsVO.setCategoryName(productCategory.getCategoryName());
            productsVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVOList.add(productInfoVO);
                }
            }
            productsVO.setProductInfoVOList(productInfoVOList);
            productsVOList.add(productsVO);
        }

        ResultVO resultVO = ResultVOUtil.success(productsVOList);
        return resultVO;
    }

}
