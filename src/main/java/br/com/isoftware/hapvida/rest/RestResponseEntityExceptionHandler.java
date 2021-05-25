package br.com.isoftware.hapvida.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.isoftware.hapvida.entidade.vo.ErroVO;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class, IllegalArgumentException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        ErroVO erroVO = new ErroVO(ex.getMessage());
        return handleExceptionInternal(ex, erroVO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Throwable.class })
    protected ResponseEntity<Object> handleGeneric(Throwable ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        ErroVO erroVO = new ErroVO("Erro genérico. Favor entrar em contato com o suporte do sistema.");
        return handleExceptionInternal(new RuntimeException(ex), erroVO, new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());

        StringBuilder builder = new StringBuilder();
        for (String erro : errorList) {
            builder.append(erro).append("\n");
        }

        ErroVO erroVO = new ErroVO(builder.toString());

        return handleExceptionInternal(ex, erroVO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
