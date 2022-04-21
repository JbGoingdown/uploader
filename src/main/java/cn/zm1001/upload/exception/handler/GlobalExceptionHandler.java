package cn.zm1001.upload.exception.handler;

import cn.zm1001.upload.exception.UploaderException;
import cn.zm1001.util.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Dongd_Zhou
 * @desc 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常
     */
    @ExceptionHandler(UploaderException.class)
    public R handleServiceException(UploaderException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("#global# ## ## 上传异常：{}", requestURI, e);
        return R.error(e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("#global# ## ## 发生未知异常：{}", requestURI, e);
        return R.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("#global# ## ## 发生系统异常：{}", requestURI, e);
        return R.error(e.getMessage());
    }
}
