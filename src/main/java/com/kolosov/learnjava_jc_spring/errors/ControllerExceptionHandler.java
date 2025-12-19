package com.kolosov.learnjava_jc_spring.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends BasicExceptionHandler {

    public ControllerExceptionHandler(ErrorMessageHandler errorMessageHandler) {
        super(errorMessageHandler);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception exception) {
        log.error(exception.getMessage());

        ErrorType errorType = findErrorTypeByExceptionClass(exception.getClass());
        String errorTitle = errorType.title;
        String errorHttpStatus = errorType.httpStatus.name();
        String errorMessage = exception.getMessage();

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorTitle", errorMessage);
        mav.addObject("errorHttpStatus", errorHttpStatus);
        mav.addObject("errorMessage", errorMessage);
        mav.setViewName("error");

        return mav;
    }

//    @ExceptionHandler(BindException.class)
//    public String globalValidationExceptionHandler(BindException exception, WebRequest request) {
//        Map<String, String> errorMap = errorMessageHandler.getErrorMap(exception.getBindingResult());
//
//        ErrorType errorType = findErrorTypeByExceptionClass(exception.getClass());
//        ProblemDetail problemDetail = createProblemDetail(exception, errorType.title, errorType.httpStatus, "Invalid request");
//
//        problemDetail.setProperty("Invalid_params", errorMap);
//
//        return "error";
//    }
}
