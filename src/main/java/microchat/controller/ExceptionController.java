package microchat.controller;

import microchat.exception.FriendException;
import microchat.exception.UserException;
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

    @ExceptionHandler(UserException.class)
    public Result userExceptionHandler(UserException ex) {
        return new Result(500, "user error", ex.getMessage());
    }

    @ExceptionHandler(FriendException.class)
    public Result friendExceptionHandler(FriendException ex) {
        return new Result(500, "friend error", ex.getMessage());
    }
}
