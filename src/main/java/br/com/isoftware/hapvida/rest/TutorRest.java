package br.com.isoftware.hapvida.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isoftware.hapvida.entidade.Tutor;
import br.com.isoftware.hapvida.servico.TutorServico;

@RestController
@RequestMapping(path = "/tutor")
public class TutorRest extends RestGenerico<Integer, Tutor, TutorServico> {

}
