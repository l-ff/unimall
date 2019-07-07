package com.iotechn.unimall.data.dto.order;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by rize on 2019/7/6.
 */
@Data
public class OrderDTO {

    /**
     * 用户下单渠道
     */
    private String channel;

    private String orderNo;

    private Long userId;

    private Integer status;

    /**
     * 商品总价
     */
    private Integer skuTotalPrice;

    private Integer freightPrice;

    private Integer couponPrice;

    private Long couponId;

    /**
     * 计算优惠后，实际需要支付的价格
     */
    private Integer actualPrice;

    private Integer payPrice;

    /**
     * 支付流水号 (第三方)
     */
    private String payId;

    /**
     * 第三方支付渠道
     */
    private String payChannel;

    private Date gmtPay;

    private String shipNo;

    /**
     * 承运商
     */
    private String shipCode;

    private String province;

    private String city;

    private String county;

    private String address;

    private String mono;

    private Date gmtShip;

    /**
     * 确实收货时间
     */
    private Date gmtConfirm;

    private List<OrderSkuDTO> skuList;

}
