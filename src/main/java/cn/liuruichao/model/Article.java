package cn.liuruichao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Article
 *
 * @author liuruichao
 * @date 15/9/5 下午4:36
 */
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer articleId;
    private String title;
    private String content;
    private Integer readNum;
    private Boolean isDelete;
    private Integer catId;
    private Date createTime;
    private Date modifyTime;

    public Article(){
    }

    public Article(Integer articleId, String title, String content, Integer readNum, Boolean isDelete, Integer catId) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.readNum = readNum;
        this.isDelete = isDelete;
        this.catId = catId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCatId() {
        return catId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
}
