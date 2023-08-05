package pillihuaman.com.exception;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@ControllerAdvice
@EnableWebMvc
public class CustomRestExceptionHandlerGeneric extends ResponseEntityExceptionHandler {

    // Custom exception to represent HTTP status codes
    static class HttpException extends RuntimeException {
        private HttpStatus httpStatus;

        public HttpException(HttpStatus httpStatus, String message) {
            super(message);
            this.httpStatus = httpStatus;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }

    // Method to handle all exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Error occurred";

        if (ex instanceof HttpException) {
            HttpException httpException = (HttpException) ex;
            httpStatus = httpException.getHttpStatus();
            message = httpException.getMessage();
        }

        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(httpStatus.value(), ex.getLocalizedMessage(), message);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), httpStatus);
    }

    // 400 - MethodArgumentNotValidException
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + getErrorMessageOrDefault(error));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + getErrorMessageOrDefault(error));
        }
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), getLocalizedMessageOrDefault(ex), errors);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);


/*
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);*/
    }

    // Add other methods to handle specific exceptions and their status codes
    // ...public ErrorResponseApiGeneric(final Integer status, final String message, final List<String> errors) {
    //		this();
    //		this.status = status;
    //		this.message = message;
    //		this.errors = errors;
    //	}

    // For example:
    // 404 - NoHandlerFoundException
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);
    }

    // 405 - HttpRequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        Set<HttpMethod> supportedHttpMethods = ex.getSupportedHttpMethods();
        if (supportedHttpMethods != null) {
            supportedHttpMethods.forEach(t -> builder.append(t).append(" "));
        }
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);
    }

    // 415 - HttpMediaTypeNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(" "));
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getLocalizedMessage(), builder.substring(0, builder.length() - 1));
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);
    }

    // 400 - HttpMessageNotReadableException
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("HttpMessageNotReadableException");
        logger.error(ex.getMessage(), ex);
        try {
            IOUtils.toString(ex.getHttpInputMessage().getBody(), StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        String message = "La trama tiene un formato incorrecto";
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), message);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);
    }

    // 400 - TypeMismatchException
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);
    }

    // 400 - MissingServletRequestPartException
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getRequestPartName() + " part is missing";
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), status);
    }

    // 400 - MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String name = "";
        Class<?> requiredType = ex.getRequiredType();
        if (requiredType != null) name = requiredType.getName();

        String error = ex.getName() + " should be of type " + name;
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // 400 - ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // 201 - Created
    @ExceptionHandler(CreatedException.class)
    protected ResponseEntity<Object> handleCreatedException(CreatedException ex) {
        ErrorResponseApiGeneric errorResponseApi = new ErrorResponseApiGeneric(HttpStatus.CREATED.value(), ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<>(errorResponseApi, new HttpHeaders(), HttpStatus.CREATED);
    }
    private String getErrorMessageOrDefault(ObjectError error) {
        return (error.getDefaultMessage() != null) ? error.getDefaultMessage() : "Validation error";
    }

    private String getLocalizedMessageOrDefault(MethodArgumentNotValidException ex) {
        return (ex.getLocalizedMessage() != null) ? ex.getLocalizedMessage() : "Validation error";
    }


}
