package cn.liuruichao.model;

import java.io.Serializable;

/**
 * Category
 *
 * @author liuruichao
 * @date 15/9/5 下午12:21
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer catId;
    private String catName;
    private Boolean isDelete;
    private Integer sort;

    public Category() {
    }

    public Category(Integer catId, String catName, Boolean isDelete) {
        this.catId = catId;
        this.catName = catName;
        this.isDelete = isDelete;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
