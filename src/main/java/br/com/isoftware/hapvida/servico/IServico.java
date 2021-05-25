package br.com.isoftware.hapvida.servico;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.isoftware.hapvida.entidade.EntidadeGenerica;

public interface IServico<K, E extends EntidadeGenerica<K>, T extends JpaRepository<E, K>> {

    public T getRepositorio();

    public void salvar(E entidade);

    public void salvar(List<E> entidades);

    public default void doSalvar(E entidade) {
    }

    public default void posSalvar(E entidade) {
    }

    public void deletar(E entidade);

    public void deletar(K id);

    public default void doDeletar(E entidade) {
    }

    public default void posDeletar(E entidade) {
    }

    public E consultarPor(K id);

    public List<E> consultarPor(E entidade);

    public default void posConsultarPor(E entidade) {
    }

    public default Page<E> doFiltrar(Pageable paginacao, E entidade) {
        Example<E> filtroBasico = this.criarFiltroBasico(entidade);
        return this.getRepositorio().findAll(filtroBasico, paginacao);
    }

    public Page<E> filtrar(Pageable paginacao, E entidade);

    public default void posFiltrar(Page<E> pagina) {
    }

    public List<E> todos();

    public default Example<E> criarFiltroBasico(E e) {
        return Example.of(e, ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
    }
}
