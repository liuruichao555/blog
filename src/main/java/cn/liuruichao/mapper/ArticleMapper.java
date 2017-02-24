package cn.liuruichao.mapper;

import cn.liuruichao.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * ArticleMapper
 *
 * @author liuruichao
 * @date 15/9/5 下午4:42
 */
public interface ArticleMapper {
    @Insert("INSERT INTO article VALUES(NULL, #{title}, #{content}, #{readNum}, #{catId}, 0, #{createTime}, #{modifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "articleId", keyColumn = "article_id")
    int saveArticle(Article article);

    @Select("SELECT * FROM article WHERE article_id = #{id} AND is_delete = 0")
    Article findArticle(@Param("id") int id);

    @Update("UPDATE article SET is_delete = 1 WHERE article_id = #{id}")
    void delArticle(@Param("id") int id);

    @Update("UPDATE article SET " +
            "title = #{title}, " +
            "content = #{content}, " +
            "modify_time = #{modifyTime}, " +
            "cat_id = #{catId} " +
            "WHERE article_id = #{id}")
    void updateArticle(@Param("id") int articleId, @Param("title") String title,
                       @Param("content") String content, @Param("modifyTime") Date modifyTime,
                       @Param("catId") int catId);

    @Select("SELECT count(1) FROM article WHERE is_delete = 0")
    int getArticleCount();

    @Select("SELECT * FROM article WHERE is_delete = 0 ORDER BY modify_time DESC limit #{startIndex}, #{maxResult}")
    List<Article> findAllArticle(@Param("startIndex") int startIndex, @Param("maxResult") int maxResult);

    @Select("SELECT count(1) FROM article WHERE is_delete = 0 AND cat_id = #{catId}")
    int getArticleCountByCatId(@Param("catId") int catId);


    @Select("SELECT * FROM article WHERE is_delete = 0 AND cat_id = #{catId} ORDER BY modify_time DESC" +
            " limit #{startIndex}, #{maxResult}")
    List<Article> findArticleByCatId(
            @Param("catId") int catId, @Param("startIndex") int startIndex, @Param("maxResult") int maxResult);
}
