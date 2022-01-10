package com.readingisgood.bookshop.application.exception;

import com.readingisgood.bookshop.domain.exception.BusinessException;
import com.readingisgood.bookshop.infrastructure.exception.InfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    private static final String ERROR_CODE = ".errorCode";
    private static final Locale EN = new Locale("en");

    private final MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBookShopApiBusinessException(BusinessException businessException) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("BusinessException");
        errorResponse.addError(getErrorDetailDto(businessException));
        log.warn("BusinessException Caused By: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException validationException) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("ValidationException");
        errorResponse.addError(getErrorDetailDto(validationException));
        log.warn("ValidationException Caused By: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("BindException");
        List<ErrorDetailDto> errorDetailDtos = getErrorDetailDtos(exception.getBindingResult());
        errorResponse.getErrors().addAll(errorDetailDtos);
        log.warn("Field validation failed. Caused By: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("GenericException");
        ErrorDetailDto errorDetailDTO = new ErrorDetailDto();
        errorDetailDTO.setMessage(exception.getMessage());
        errorDetailDTO.setKey("GenericError");
        errorResponse.addError(errorDetailDTO);
        log.error("GenericException Occurred.", exception);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<ErrorResponse> handleBookShopApiSystemException(InfrastructureException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("InfrastructureException");
        ErrorDetailDto errorDetailDTO = new ErrorDetailDto();
        errorDetailDTO.setMessage(getMessage(exception.getKey(), exception.getArgs(), exception.getMessage()));
        errorDetailDTO.setKey("InfrastructureError");
        errorResponse.addError(errorDetailDTO);
        log.error("InfrastructureException Occurred.", exception);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("MethodArgumentNotValidException");

        exception.getBindingResult().getFieldErrors().forEach(i -> {

            ErrorDetailDto fieldError = new ErrorDetailDto();
            fieldError.setMessage(getMessage(i.getDefaultMessage(), i.getArguments(), ""));
            fieldError.setKey(i.getField());
            errorResponse.addError(fieldError);
        });

        log.warn("Field validation failed. Caused By: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookShopApiNotFoundException(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setException("NotFoundException");
        errorResponse.addError(getErrorDetailDto(exception));
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    private List<ErrorDetailDto> getErrorDetailDtos(BindingResult bindingResult) {
        List<ErrorDetailDto> errorDetailDtos = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(i -> {
            ErrorDetailDto fieldError = new ErrorDetailDto();
            fieldError.setMessage(getMessage(i.getDefaultMessage(), i.getArguments(), ""));
            fieldError.setKey(i.getField());
            errorDetailDtos.add(fieldError);
        });
        return errorDetailDtos;
    }

    private ErrorDetailDto getErrorDetailDto(BusinessException exception) {
        ErrorDetailDto errorDetailDto = new ErrorDetailDto();
        errorDetailDto.setKey(exception.getKey());
        errorDetailDto.setMessage(getMessage(exception.getKey(), exception.getArgs(), exception.getMessage()));
        errorDetailDto.setErrorCode(getErrorCode(exception.getKey()));
        errorDetailDto.setArgs(exception.getArgs());
        return errorDetailDto;
    }

    private String getMessage(String key, Object[] args, String defaultMessage) {
        return Optional.of(getMessage(() -> messageSource.getMessage(key, args, EN)))
                .filter(StringUtils::isNotBlank)
                .orElse(defaultMessage);
    }

    private String getErrorCode(String key) {
        return Optional.of(getMessage(() -> messageSource.getMessage(key + ERROR_CODE, new Object[]{}, EN)))
                .filter(StringUtils::isNotBlank)
                .orElse("");
    }

    private String getMessage(Supplier<String> supplier) {
        String message = StringUtils.EMPTY;
        try {
            message = supplier.get();
        } catch (Exception ignored) {
        }
        return message;
    }
}
