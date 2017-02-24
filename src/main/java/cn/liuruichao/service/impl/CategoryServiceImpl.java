package cn.liuruichao.service.impl;

import cn.liuruichao.common.StatusCode;
import cn.liuruichao.mapper.CategoryMapper;
import cn.liuruichao.model.Category;
import cn.liuruichao.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author liuruichao
 * @date 15/9/5 下午12:25
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryById(int id) {
        return categoryMapper.findCategory(id);
    }

    @Override
    public Category updateCategory(String categoryName, int catId) {
        Category category = null;
        if (!StringUtils.isEmpty(categoryName) && catId > 0) {
            categoryMapper.updateCategory(categoryName, catId);
            category = categoryMapper.findCategory(catId);
        }
        return category;
    }

    @Override
    public int addCategory(String categoryName) {
        int result = StatusCode.ERROR_CODE;
        if (!StringUtils.isEmpty(categoryName)) {
            Category category = new Category();
            category.setCatName(categoryName);
            category.setIsDelete(false);
            categoryMapper.saveCategory(category);
            if (category.getCatId() != null) {
                result = category.getCatId();
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.findAllCategory();
    }

    @Override
    public void delCategoryById(int[] ids) {
        if (ids != null && ids.length > 0) {
            for (int id : ids) {
                if (id > 0) {
                    categoryMapper.delCategory(id);
                }
            }
        }
    }
}
