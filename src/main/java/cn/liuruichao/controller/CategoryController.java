package cn.liuruichao.controller;

import cn.liuruichao.base.BaseController;
import cn.liuruichao.common.Result;
import cn.liuruichao.common.ResultMessages;
import cn.liuruichao.model.Category;
import cn.liuruichao.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CategoryController
 *
 * @author liuruichao
 * @date 15/9/5 下午2:19
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private final Logger logger = Logger.getLogger(CategoryController.class);
    @Resource
    private CategoryService categoryService;

    @RequestMapping("")
    public String category(HttpServletRequest request) {
        List<Category> allCategory = categoryService.getAllCategory();
        request.setAttribute("allCategory", allCategory);
        return "category/list";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Result allCategory() {
        try {
            List<Category> allCategory = categoryService.getAllCategory();
            newSuccessResult(null, allCategory);
        } catch (Exception e) {
            logger.error("CategoryController.allCategory() error : ", e);
            newFaildResult(ResultMessages.SYSTEM_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Result delCategory(
            @RequestParam(value = "catId", required = true) int catId) {
        try {
            categoryService.delCategoryById(new int[] { catId });
            newSuccessResult(ResultMessages.OPERATOR_SUCCESS, null);
        } catch (Exception e) {
            logger.error("CategoryController.delCategory() error : ", e);
            newFaildResult(ResultMessages.OPERATOR_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCategory(
            @RequestParam(value = "categoryName", required = true) String categoryName,
            @RequestParam(value = "catId", required = true) int catId) {
        try {
            Category category = categoryService.updateCategory(categoryName, catId);
            newSuccessResult(ResultMessages.OPERATOR_SUCCESS, category);
        } catch (Exception e) {
            logger.error("CategoryController.updateCategory() error : ", e);
            newFaildResult(ResultMessages.OPERATOR_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Result addCategory(
            @RequestParam(value = "categoryName", required = true) String categoryName) {
        try {
            int catId = categoryService.addCategory(categoryName);
            Category category = categoryService.getCategoryById(catId);
            newSuccessResult(ResultMessages.OPERATOR_SUCCESS, category);
        } catch (Exception e) {
            logger.error("CategoryController.addCategory() error : ", e);
            newFaildResult(ResultMessages.OPERATOR_ERROR);
        }
        return result;
    }
}
