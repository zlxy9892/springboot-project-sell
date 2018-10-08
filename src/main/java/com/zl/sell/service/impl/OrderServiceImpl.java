package com.zl.sell.service.impl;

import com.zl.sell.dao.OrderDetailDao;
import com.zl.sell.dao.OrderMasterDao;
import com.zl.sell.dto.CartDTO;
import com.zl.sell.dto.OrderDTO;
import com.zl.sell.enums.OrderStatusEnum;
import com.zl.sell.enums.PayStatusEnum;
import com.zl.sell.enums.ResultEnum;
import com.zl.sell.exception.SellException;
import com.zl.sell.pojo.OrderDetail;
import com.zl.sell.pojo.OrderMaster;
import com.zl.sell.pojo.ProductInfo;
import com.zl.sell.service.OrderService;
import com.zl.sell.service.ProductInfoService;
import com.zl.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();    // 生成订单的唯一ID
        BigDecimal orderPriceAmount = new BigDecimal(BigInteger.ZERO);  // 订单的总价格

        // 查询订单中包含的所有商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            // 计算总价
            BigDecimal oneProductAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()));
            orderPriceAmount.add(oneProductAmount);
            // 商详入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);
        }

        // 写入订单数据库（orderMaster & orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
//        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderPriceAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        // 扣库存 (此处会出现多人同时扣库存导致"超卖"的问题，可使用redis的锁机制解决)
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        // 发送websocket消息
//        webSocket.sendMessage(orderDTO.getOrderId());

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
