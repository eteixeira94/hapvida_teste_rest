package br.com.isoftware.hapvida.servico;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.empty;

import java.io.Serializable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.isoftware.hapvida.entidade.EntidadeGenerica;
import br.com.isoftware.hapvida.enums.Operacao;
import lombok.SneakyThrows;

public abstract class GenericoServicoTest<ID extends Serializable, T extends EntidadeGenerica<ID>, S extends ServicoGenerico<ID, T, ?>> {

    @Autowired
    protected S servico;

    @Test
    @SneakyThrows
    void deveSalvar_ComSucesso() {
        T entidade = montarEntidade(null, Operacao.CRIAR);
        servico.salvar(entidade);
        assertThat(entidade.getId(), notNullValue());
    }

    @Test
    @SneakyThrows
    void deveAtualizar_ComSucesso() {
        T entidade = montarEntidade(null, Operacao.CRIAR);
        servico.salvar(entidade);
        montarEntidade(entidade, Operacao.ATUALIZAR);
        servico.salvar(entidade);

        T db = servico.consultarPor(entidade.getId());

        assertThat(db, equalTo(db));
    }

    @Test
    @SneakyThrows
    void deveDeletar_ComSucesso() {
        T entidade = montarEntidade(null, Operacao.DELETAR);
        servico.salvar(entidade);
        servico.deletar(entidade.getId());

        entidade.setId(null);
        List<T> resultado = servico.consultarPor(entidade);

        assertThat(resultado, empty());
    }

    abstract T montarEntidade(T entidade, Operacao operacao);
}
