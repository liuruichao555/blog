package cn.liuruichao.service.impl;

import cn.liuruichao.common.PageBean;
import cn.liuruichao.common.StatusCode;
import cn.liuruichao.mapper.ArticleMapper;
import cn.liuruichao.model.Article;
import cn.liuruichao.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ArticleServiceImpl
 *
 * @author liuruichao
 * @date 15/9/5 下午4:55
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Transactional(readOnly = true)
    @Override
    public Article getArticleById(int id) {
        return articleMapper.findArticle(id);
    }

    @Override
    public int addArticle(String title, String content, int catId) {
        int result = StatusCode.ERROR_CODE;
        if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(content) && catId > 0) {
            Article article = new Article();
            article.setIsDelete(false);
            article.setCatId(catId);
            article.setContent(content);
            article.setReadNum(0);
            article.setTitle(title);
            article.setCreateTime(new Date());
            article.setModifyTime(new Date());
            articleMapper.saveArticle(article);
            if (article.getArticleId() != null) {
                result = article.getArticleId();
            }
        }
        return result;
    }

    @Override
    public void delArticleById(int[] ids) {
        if (ids != null && ids.length > 0) {
            for (int id : ids) {
                if (id > 0) {
                    articleMapper.delArticle(id);
                }
            }
        }
    }

    @Override
    public Article updateArticle(int articleId, String title, String content, int catId) {
        Article article = null;
        if (articleId > 0 && !StringUtils.isEmpty(title) && !StringUtils.isEmpty(content)) {
            articleMapper.updateArticle(articleId, title, content, new Date(), catId);
            article = articleMapper.findArticle(articleId);
        }
        return article;
    }

    @Transactional(readOnly = true)
    @Override
    public PageBean<Article> getPage(int curPage) {
        PageBean<Article> pageBean = new PageBean<>(curPage);
        List<Article> data = articleMapper.findAllArticle(pageBean.getStartIndex(), pageBean.getPageSize());
        int totalCount = articleMapper.getArticleCount();
        pageBean.setTotalCount(totalCount);
        pageBean.setData(data);
        return pageBean;
    }

    @Transactional(readOnly = true)
    @Override
    public PageBean<Article> getPage(int curPage, int catId) {
        PageBean<Article> pageBean = new PageBean<>(curPage);
        List<Article> data = articleMapper.findArticleByCatId(catId, pageBean.getStartIndex(), pageBean.getPageSize());
        int totalCount = articleMapper.getArticleCountByCatId(catId);
        pageBean.setTotalCount(totalCount);
        pageBean.setData(data);
        return pageBean;
    }
}
