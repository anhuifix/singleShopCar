/*
 * 
 */
package com.mask.singleshopcar.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 基类
 *
 * @version 1.0
 * @zhaungAH <p>所有实体类的基类。
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -2786529174941871554L;
    /**
     * ID
     */
    protected String id;

    /**
     * 已删除
     */
    protected Boolean isDeleted = false;

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 修改日期
     */
    protected Date modifyDate;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 编辑者
     */
    private String editor;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 已删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param 已删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 修改日期
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate 修改日期
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @return 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return 编辑者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param 编辑者
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * 设置创建者 & 编辑者
     *
     * @param operator 操作人
     */
    public void setCreatorAndEditor(String operator) {
        setCreator(operator);
        setEditor(operator);
    }

    /**
     * 重写equals方法
     *
     * @param obj 对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        return getId() != null ? getId().equals(other.getId()) : false;
    }

    /**
     * 重写hashCode方法
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

}
