package cn.liuruichao.mapper;

import cn.liuruichao.model.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文章分类
 *
 * @author liuruichao
 * @date 15/9/5 下午12:19
 */
public interface CategoryMapper {
    @Select("SELECT cat_id, cat_name, is_delete, sort FROM category WHERE cat_id = #{id}")
    Category findCategory(@Param("id") int id);

    @Insert("INSERT INTO category VALUES(NULL, #{catName}, #{isDelete}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "catId", keyColumn = "CAT_ID")
    void saveCategory(Category category);

    @Select("SELECT cat_id, cat_name, is_delete, sort FROM category WHERE is_delete = 0 ORDER BY sort")
    List<Category> findAllCategory();

    @Update("UPDATE category SET is_delete = 1 WHERE cat_id = #{id}")
    void delCategory(@Param("id") int id);

    @Update("UPDATE category SET cat_name = #{categoryName} WHERE cat_id = #{id}")
    void updateCategory(@Param("categoryName") String categoryName, @Param("id") int catId);
}
