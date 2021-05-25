package br.com.isoftware.hapvida.servico;

import org.springframework.stereotype.Service;

import br.com.isoftware.hapvida.entidade.Veterinario;
import br.com.isoftware.hapvida.repositorio.VeterinarioRepositorio;

@Service
public class VeterinarioServico extends ServicoGenerico<Integer, Veterinario, VeterinarioRepositorio> {

}
