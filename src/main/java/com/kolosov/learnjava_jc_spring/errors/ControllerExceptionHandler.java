package com.kolosov.learnjava_jc_spring.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends BasicExceptionHandler {

    public ControllerExceptionHandler(ErrorMessageHandler errorMessageHandler) {
        super(errorMessageHandler);
    }

    @ExceptionHandler(Throwable.class)
    public String exception(Exception exception, Model model) {
        log.error(exception.getMessage());

        ErrorType errorType = findErrorTypeByExceptionClass(exception.getClass());
        String errorTitle = errorType.title;
        String errorHttpStatus = errorType.httpStatus.name();
        String errorMessage = exception.getMessage();

        model.addAttribute("errorTitle", errorMessage);
        model.addAttribute("errorHttpStatus", errorHttpStatus);
        model.addAttribute("errorMessage", errorMessage);

        return "error";
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
