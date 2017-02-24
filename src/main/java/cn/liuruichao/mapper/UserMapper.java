package cn.liuruichao.mapper;

import cn.liuruichao.model.Block;
import cn.liuruichao.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper
 *
 * @author liuruichao
 * @date 15/9/4 下午10:04
 */
public interface UserMapper {
    @Select("SELECT user_id, username, password FROM user WHERE username = #{username}")
    User findUser(@Param("username") String username);

    @Select("select id, tx_length from block order by timestamp limit #{startIndex}, #{pageSize}")
    List<Block> findBlockList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    @Select("select count(*) from block")
    int getBlockCount();
}
