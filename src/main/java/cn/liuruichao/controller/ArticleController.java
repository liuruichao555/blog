package cn.liuruichao.controller;

import cn.liuruichao.base.BaseController;
import cn.liuruichao.common.PageBean;
import cn.liuruichao.model.Article;
import cn.liuruichao.model.Category;
import cn.liuruichao.service.ArticleService;
import cn.liuruichao.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ArticleController
 *
 * @author liuruichao
 * @date 15/9/5 下午5:19
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Resource
    private ArticleService articleService;
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/{articleId}")
    public String detail(
            @PathVariable int articleId,
            HttpServletRequest request) {
        Article article = articleService.getArticleById(articleId);
        if (article == null) {
            return ERROR_PAGE_404;
        }
        Category category = categoryService.getCategoryById(article.getCatId());
        request.setAttribute("article", article);
        request.setAttribute("category", category);
        return "article/detail";
    }
    
    @RequestMapping("/list/{catId}/{curPage}")
    public String list(
            @PathVariable int catId,
            @PathVariable int curPage,
            HttpServletRequest request) {
        PageBean<Article> pageBean = null;

        if (catId == 0) {
            // 查询全部分类
            pageBean = articleService.getPage(curPage);
        } else {
            pageBean = articleService.getPage(curPage, catId);
        }
        List<Category> allCategory = categoryService.getAllCategory();

        pageBean.setUrl("/article/list/" + catId);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("allCategory", allCategory);
        request.setAttribute("curCategory", catId);
        return "article/list";
    }

    @RequestMapping("/list/{catId}")
    public String list(@PathVariable int catId) {
        // 如果输入分类没有输入页码，默认跳转到第一页
        return "redirect:/article/list/" + catId + "/1";
    }
    
    @RequestMapping("/list")
    public String list() {
        // 如果没有输入分类没有输入页码，默认跳转到全部分类第一页
        return "redirect:/article/list/0/1";
    }
}
