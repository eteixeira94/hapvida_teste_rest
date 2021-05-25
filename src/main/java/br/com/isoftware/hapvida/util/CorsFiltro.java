package br.com.isoftware.hapvida.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;;

public class CorsFiltro extends CorsFilter {

    public CorsFiltro(CorsConfigurationSource source) {
        super((CorsConfigurationSource) source);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest requisicao, HttpServletResponse resposta,
            FilterChain filterChain) throws ServletException, IOException {

        if (!resposta.containsHeader("Access-Control-Allow-Origin")) {
            resposta.addHeader("Access-Control-Allow-Origin", "*");
        }

        resposta.addHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(requisicao.getMethod())) {
            resposta.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            resposta.addHeader("Access-Control-Max-Age", "3600");
        }

        resposta.addHeader("Access-Control-Allow-Headers",
                "Accept, Accept-Language, Content-Language, Content-Type, Authorization, Access-Control-Allow-Origin, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

        super.doFilterInternal(requisicao, resposta, filterChain);
    }
}
