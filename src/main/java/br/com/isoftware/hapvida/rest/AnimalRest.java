package br.com.isoftware.hapvida.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isoftware.hapvida.entidade.Animal;
import br.com.isoftware.hapvida.servico.AnimalServico;

@RestController
@RequestMapping(path = "/animal")
public class AnimalRest extends RestGenerico<Integer, Animal, AnimalServico> {

}
