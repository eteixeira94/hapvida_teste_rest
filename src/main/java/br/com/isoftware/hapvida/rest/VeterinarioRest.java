package br.com.isoftware.hapvida.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isoftware.hapvida.entidade.Veterinario;
import br.com.isoftware.hapvida.servico.VeterinarioServico;

@RestController
@RequestMapping(path = "/veterinario")
public class VeterinarioRest extends RestGenerico<Integer, Veterinario, VeterinarioServico> {

}
