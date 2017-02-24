package cn.liuruichao.base;

import cn.liuruichao.common.Result;


public class BaseController {
    protected static final String ERROR_PAGE_404 = "errors/404";
    protected static final String ERROR_PAGE_500 = "errors/404";
    protected Result result;

    protected <E> Result<E> newSuccessResult(String message, E data) {
        Result result = new Result<E>();
        result.setStatus(1);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    protected <E> Result<E> newFaildResult(String message) {
        Result result = new Result<E>();
        result.setStatus(-1);
        result.setMessage(message);
        return result;
    }
}
