/*
 * 
 */
package com.mask.singleshopcar.Model;


/**
 * Entity - 图片
 * 
 * @author xiayb
 * @version 1.0
 */
public class ImageModel extends BaseEntity {

	private static final long serialVersionUID = 5193977577912120320L;

	/**
	 * 类型
	 */
	public enum Type {

		/** 商品 */
		goods,

		/** 客户 */
		customer,

		/** 供应商 */
		supplier,

		/** 店铺 */
		shop,

	}

	/** 企业ID */
	private String enterprise;

	/** 类型 */
	private Type type;

	/** 关联ID */
	private String bizId;

	/** 原图 */
	private String srcImage;

	/** 缩略图 */
	private String thumbImage;

	/** 顺序 */
	private Integer sort;

	/** 视频 */
	private String video;

	/** 描述 */
	private String description;

	public ImageModel() {
		super();
	}

	/**
	 * @param srcImage 原图
	 */
	public ImageModel(String srcImage) {
		this.srcImage = srcImage;
	}

	/**
	 * @return 企业ID
	 */
	public String getEnterprise() {
		return enterprise;
	}
	
	/**
	 * @param enterprise 企业ID
	 */
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	
	/**
	 * @return 类型
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * @param type 类型
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * @return 关联ID
	 */
	public String getBizId() {
		return bizId;
	}
	
	/**
	 * @param bizId 关联ID
	 */
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
	/**
	 * @return 原图
	 */
	public String getSrcImage() {
		return srcImage;
	}
	
	/**
	 * @param srcImage 原图
	 */
	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}
	
	/**
	 * @return 缩略图
	 */
	public String getThumbImage() {
		return thumbImage;
	}
	
	/**
	 * @param thumbImage 缩略图
	 */
	public void setThumbImage(String thumbImage) {
		this.thumbImage = thumbImage;
	}
	
	/**
	 * @return 顺序
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * @param sort 顺序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * @return 视频
	 */
	public String getVideo() {
		return video;
	}
	
	/**
	 * @param video 视频
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	
	/**
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
