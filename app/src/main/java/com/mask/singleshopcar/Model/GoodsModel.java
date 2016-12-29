package com.mask.singleshopcar.Model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 求购商品
 * Created by zhuangAh on 2016-11-7.
 */

public class GoodsModel extends BaseEntity {

    private static final long serialVersionUID = -6615971672037849412L;
    /**
     * 商品名字
     */
    private String name;

    /**
     * 商品数量
     */
    private int qty;

    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 产品国家
     */
    private String country;
    /**
     */
    private String city;
    /**
     * 店铺名字
     */
    private String shopName;
    /**
     * 图片路径
     */
    private String imageSrc;
    /**
     * 是否被选中
     */
    private boolean isSelect = false;
    /**
     * 店铺Id
     */
    private String shopId;
    /**
     * 是否选中
     */
    protected boolean isChoosed;

    /**
     * 图片集合
     */
    private List<ImageVoModel> imageVoModelList;

    /**
     * 商品地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 货源介绍
     */
    private String SupplyOfGoodsRemark;
    /**
     * 结算方式
     */
    private String distributionWay;
    /**
     * 商品选择多少个
     */
    private int selectQty;

    /**
     * 是否是商家
     */
    private boolean isBusiness;
    /**
     * 公司Id
     */
    private String factoryId;
    /**
     * 公司Name
     */
    private String factoryName;
    /**
     * 公司备注
     */
    private String factoryRemary;
    /**
     * 公司图片路径
     */
    private String factoryImage;

    /**
     * 备用1
     */
    private String reserve1;

    /**
     * 备用2
     */
    private String reserve2;

    /**
     * 备用3
     */
    private String reserve3;

    /**
     * 备用4
     */
    private String reserve4;

    /**
     * 备用5
     */
    private String reserve5;


    public String getDistributionWay() {
        return distributionWay;
    }

    public void setDistributionWay(String distributionWay) {
        this.distributionWay = distributionWay;
    }

    public String getSupplyOfGoodsRemark() {
        return SupplyOfGoodsRemark;
    }

    public void setSupplyOfGoodsRemark(String supplyOfGoodsRemark) {
        SupplyOfGoodsRemark = supplyOfGoodsRemark;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public List<ImageVoModel> getImageVoModelList() {
        return imageVoModelList;
    }

    public void setImageVoModelList(List<ImageVoModel> imageVoModelList) {
        this.imageVoModelList = imageVoModelList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public int getSelectQty() {
        return selectQty;
    }

    public void setSelectQty(int selectQty) {
        this.selectQty = selectQty;
    }


    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getFactoryImage() {
        return factoryImage;
    }

    public void setFactoryImage(String factoryImage) {
        this.factoryImage = factoryImage;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryRemary() {
        return factoryRemary;
    }

    public void setFactoryRemary(String factoryRemary) {
        this.factoryRemary = factoryRemary;
    }
}
