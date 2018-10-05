package com.zl.sell.controller;

import com.zl.sell.vo.ProductsVO;
import com.zl.sell.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @RequestMapping("/list")
    public ResultVO list() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        ProductsVO productsVO = new ProductsVO();
        resultVO.setData(productsVO);
        return resultVO;
    }

}
