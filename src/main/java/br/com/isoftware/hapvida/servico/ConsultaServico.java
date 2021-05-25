package br.com.isoftware.hapvida.servico;

import org.springframework.stereotype.Service;

import br.com.isoftware.hapvida.entidade.Consulta;
import br.com.isoftware.hapvida.repositorio.ConsultaRepositorio;

@Service
public class ConsultaServico extends ServicoGenerico<Long, Consulta, ConsultaRepositorio> {

}
