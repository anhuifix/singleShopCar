package com.mask.singleshopcar.Model;

/**
 * 拆车厂
 * Created by zhuangAh on 2016-11-7.
 */

public class DIFactoryModel extends BaseEntity {

    /**
     * 工厂名
     */
    private String factoryName;
    /**
     * 图片,厂图
     */
    private String imageUrl;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 国家
     */
    private String country;
    /**
     * 城市
     */
    private String city;
    /**
     * 公司备注
     */
    private String remark;
    /**
     * 是否选中收藏
     */
    private boolean isSelect;
    /**
     * 是否选中了,购物车需要
     */
    private boolean isChoosed;
    /**
     * 是否企业认证
     */
    private boolean enterpriseCertification;
    /**
     * 是否有拆解车资格
     */
    private boolean dismaCar;
    /**
     * 是
     */
    private boolean edtor;

    public boolean isDismaCar() {
        return dismaCar;
    }

    public void setDismaCar(boolean dismaCar) {
        this.dismaCar = dismaCar;
    }

    public boolean isEnterpriseCertification() {
        return enterpriseCertification;
    }

    public void setEnterpriseCertification(boolean enterpriseCertification) {
        this.enterpriseCertification = enterpriseCertification;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
