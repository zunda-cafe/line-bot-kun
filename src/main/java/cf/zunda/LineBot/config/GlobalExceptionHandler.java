package cf.zunda.LineBot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ykonno.
 * @since 2017/06/14.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NestedRuntimeException.class)
    public String handleGlobalException(NestedRuntimeException e){
      log.error("error occurred", e);
      return "end.";
    }
}
