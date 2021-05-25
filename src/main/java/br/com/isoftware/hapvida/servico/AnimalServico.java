package br.com.isoftware.hapvida.servico;

import org.springframework.stereotype.Service;

import br.com.isoftware.hapvida.entidade.Animal;
import br.com.isoftware.hapvida.repositorio.AnimalRepositorio;

@Service
public class AnimalServico extends ServicoGenerico<Integer, Animal, AnimalRepositorio> {

}
