package br.com.isoftware.hapvida.rest;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.isoftware.hapvida.entidade.EntidadeGenerica;
import br.com.isoftware.hapvida.servico.ServicoGenerico;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;

public abstract class RestGenerico<ID extends Serializable, T extends EntidadeGenerica<ID>, S extends ServicoGenerico<ID, T, ?>> {

    @Getter
    @Autowired
    private S servico;

    @PostMapping(value = "/salvar")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o registro salvo no banco de dados.") })
    public ResponseEntity<?> salvar(@Valid @RequestBody T t) {
        this.getServico().salvar(t);
        return ResponseEntity.ok(t);
    }

    @PostMapping(value = "/salvar/lista")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna os registros salvos no banco de dados.") })
    public ResponseEntity<?> salvarLista(@Valid @RequestBody List<T> entidades) {
        this.getServico().salvar(entidades);
        return ResponseEntity.ok(entidades);
    }

    @DeleteMapping(value = "/deletar/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna boleano quando a operação é executada com sucesso.") })
    public ResponseEntity<?> deletar(@PathVariable(value = "id") ID id) {
        this.getServico().deletar(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping(value = "/consultar/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o registro pelo id de identificação no banco de dados.") })
    public ResponseEntity<?> consultarPor(@PathVariable(value = "id") ID id) {
        T entidade = this.getServico().consultarPor(id);
        return ResponseEntity.ok(entidade);
    }

    @PostMapping(value = "/consultar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os registros conforme filtro do body da requisição no banco de dados.") })
    public ResponseEntity<?> consultarPor(@RequestBody T e) {
        List<T> entidades = this.getServico().consultarPor(e);
        return ResponseEntity.ok(entidades);
    }

    @PostMapping(value = "/consultar/paginado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os registros paginados conforme filtro do body da requisição no banco de dados.") })
    public ResponseEntity<?> filtrar(@RequestBody T entidade, Pageable pageable) {
        Page<T> page = this.getServico().filtrar(pageable, entidade);

        return ResponseEntity.ok(page);
    }
}
