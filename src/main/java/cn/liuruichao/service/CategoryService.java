package cn.liuruichao.service;

import cn.liuruichao.model.Category;

import java.util.List;

/**
 * CategoryService
 *
 * @author liuruichao
 * @date 15/9/5 下午12:25
 */
public interface CategoryService {
    Category getCategoryById(int id);

    Category updateCategory(String categoryName, int catId);

    int addCategory(String categoryName);

    List<Category> getAllCategory();

    void delCategoryById(int[] ids);
}
