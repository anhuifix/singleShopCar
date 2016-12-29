/*
 * 
 */
package com.mask.singleshopcar.Model;


/**
 * VO - 图片
 * 
 * @author xiayb
 * @version 1.0
 */
public final class ImageVoModel extends ImageModel {

	private static final long serialVersionUID = 2800954821180949831L;

	/**
	 * 来源
	 */
	public enum Source {

		/** 拍照 */
		camera,

		/** 图库 */
		gallery,

		/** 数据库 */
		database,

	}

	/** 本地路径 */
	private String path;

	/** 来源 */
	private Source source;

	/** 原图文件名 */
	private String srcImageFileName;

	public ImageVoModel() {
		super();
	}

	/**
	 * @param path 本地路径
	 * @param source 来源
	 */
	public ImageVoModel(String path, Source source) {
		this.path = path;
		this.source = source;
	}

	/**
	 * @param srcImageFileName 原图文件名
	 */
	public ImageVoModel(String srcImageFileName) {
		this.srcImageFileName = srcImageFileName;
	}

	/**
	 * @return 本地路径
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path 本地路径
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return 来源
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * @param source 来源
	 */
	public void setSource(Source source) {
		this.source = source;
	}

	/**
	 * @return 原图文件名
	 */
	public String getSrcImageFileName() {
		return srcImageFileName;
	}

	/**
	 * @param srcImageFileName 原图文件名
	 */
	public void setSrcImageFileName(String srcImageFileName) {
		this.srcImageFileName = srcImageFileName;
	}

}
