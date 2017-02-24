package cn.liuruichao.service;

import cn.liuruichao.model.Block;
import cn.liuruichao.model.User;

import java.util.List;

/**
 * UserService
 *
 * @author liuruichao
 * @date 15/9/4 下午10:08
 */
public interface UserService {
    User login(String username, String password);

    List<Block> getBlockList(Integer pageIndex);
}
