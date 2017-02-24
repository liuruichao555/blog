package cn.liuruichao.controller;

import cn.liuruichao.base.BaseController;
import cn.liuruichao.common.Result;
import cn.liuruichao.common.ResultMessages;
import cn.liuruichao.model.Article;
import cn.liuruichao.model.Category;
import cn.liuruichao.service.ArticleService;
import cn.liuruichao.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AdminController
 *
 * @author liuruichao
 * @date 15/9/5 下午10:37
 */
@Controller
@RequestMapping("/lrc/manager/admin")
public class AdminController extends BaseController {
    private final Logger logger = Logger.getLogger(AdminController.class);
    @Resource
    private CategoryService categoryService;
    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/article/toUpdate/{id}", method = RequestMethod.GET)
    public String toUpdateArticle(
            @PathVariable int id,
            HttpServletRequest request) {
        Article article = articleService.getArticleById(id);
        article.setContent(article.getContent()
                                    .replaceAll("&", "&amp;")
                                    .replaceAll("<", "&lt;")
                                    .replaceAll(">", "&gt;")
                                    .replaceAll("\"", "&quot;"));
        List<Category> allCategory = categoryService.getAllCategory();
        request.setAttribute("allCategory", allCategory);
        request.setAttribute("article", article);
        return "admin/article/update";
    }

    @RequestMapping(value = "/article/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateArticle(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "catId", required = true) int catId) {
        try {
            Article article = articleService.updateArticle(id, title, content, catId);
            newSuccessResult(ResultMessages.OPERATOR_SUCCESS, article);
        } catch (Exception e) {
            logger.error("AdminController.updateArticle() is error : ", e);
            newFaildResult(ResultMessages.OPERATOR_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/article/toAdd", method = RequestMethod.GET)
    public String toAddArticle(HttpServletRequest request) {
        List<Category> allCategory = categoryService.getAllCategory();
        request.setAttribute("allCategory", allCategory);
        return "admin/article/add";
    }

    @RequestMapping(value = "/article/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addArticle(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "catId", required = true) int catId) {
        try {
            int articleId = articleService.addArticle(title, content, catId);
            newSuccessResult(ResultMessages.OPERATOR_SUCCESS, articleId);
        } catch (Exception e) {
            logger.error("AdminController.addArticle() is error : ", e);
            newFaildResult(ResultMessages.OPERATOR_ERROR);
        }
        return result;
    }
}
