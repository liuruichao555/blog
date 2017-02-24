package cn.liuruichao.service.impl;

import cn.liuruichao.mapper.UserMapper;
import cn.liuruichao.model.Block;
import cn.liuruichao.model.User;
import cn.liuruichao.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author liuruichao
 * @date 15/9/4 下午10:08
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public User login(String username, String password) {
        User loginUser = null;
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            User dbUser = userMapper.findUser(username);
            if (dbUser != null
                    && dbUser.getPassword().equals(DigestUtils.md5Hex(password))) {
                loginUser = dbUser;
            }
        }
        return loginUser;
    }

    @Override
    public List<Block> getBlockList(Integer pageIndex) {
        int pageSize = 10;
        int startIndex = (pageIndex - 1) * pageSize;
        List<Block> list = userMapper.findBlockList(startIndex, pageSize);
        if (list.size() < 10) {
            int count = userMapper.getBlockCount();
            startIndex = count - pageSize;
            list = userMapper.findBlockList(startIndex, pageSize);
        }
        return list;
    }
}
