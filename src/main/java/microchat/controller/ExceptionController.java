package microchat.controller;

import microchat.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常处理
 *
 * @author qiang
 * @since 2022/4/7
 */
@ControllerAdvice
@ResponseBody
public class ExceptionController {
    /**
     * 全局异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex) {
        return new Result(500, "server error", ex.getMessage());
    }
}
