package br.com.isoftware.hapvida.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isoftware.hapvida.entidade.Consulta;
import br.com.isoftware.hapvida.servico.ConsultaServico;

@RestController
@RequestMapping(path = "/consulta")
public class ConsultaRest extends RestGenerico<Long, Consulta, ConsultaServico> {

}