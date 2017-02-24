package cn.liuruichao.controller;

import cn.liuruichao.base.BaseController;
import cn.liuruichao.common.Result;
import cn.liuruichao.model.Block;
import cn.liuruichao.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CommonController
 *
 * @author liuruichao
 * @date 15/9/4 下午10:04
 */
@Controller
@RequestMapping("")
public class CommonController extends BaseController {
    private final Logger logger = Logger.getLogger(CommonController.class);

    @Resource
    private UserService userService;

    @RequestMapping("")
    public String hello() {
        return "list";
    }

    @RequestMapping("/blockdata.json")
    @ResponseBody
    public Result getBlockData(@RequestParam(defaultValue = "1") Integer pageIndex) {
        List<Block> list = userService.getBlockList(pageIndex);
        return newSuccessResult("success", list);

    }
}
