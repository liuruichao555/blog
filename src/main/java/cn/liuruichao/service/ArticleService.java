package cn.liuruichao.service;

import cn.liuruichao.common.PageBean;
import cn.liuruichao.model.Article;

/**
 * ArticleService
 *
 * @author liuruichao
 * @date 15/9/5 下午4:55
 */
public interface ArticleService {
    Article getArticleById(int id);

    int addArticle(String title, String content, int catId);

    void delArticleById(int[] ids);

    Article updateArticle(int articleId, String title, String content, int catId);

    PageBean<Article> getPage(int curPage);

    PageBean<Article> getPage(int curPage, int catId);
}
