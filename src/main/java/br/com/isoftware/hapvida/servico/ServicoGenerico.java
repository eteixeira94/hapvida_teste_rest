package br.com.isoftware.hapvida.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.isoftware.hapvida.entidade.EntidadeGenerica;
import lombok.Getter;

@Component
public abstract class ServicoGenerico<K, E extends EntidadeGenerica<K>, T extends JpaRepository<E, K>>
        implements IServico<K, E, T> {

    @Autowired
    @Getter
    private T repositorio;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void salvar(E e) {
        this.doSalvar(e);
        this.repositorio.save(e);
        this.posSalvar(e);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void salvar(List<E> entidades) {
        for (E e : entidades) {
            this.salvar(e);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deletar(E e) {
        this.doDeletar(e);
        this.repositorio.delete(e);
        this.posDeletar(e);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deletar(K id) {
        E entidade = (E) this.repositorio.getOne(id);
        this.deletar(entidade);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public E consultarPor(K id) {
        E e = this.repositorio.findById(id).orElse(null);
        Assert.notNull(e, "Registro não localizado.");
        this.posConsultarPor(e);

        return e;
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<E> consultarPor(E e) {
        Example<E> example = criarFiltroBasico(e);
        List<E> entidades = this.repositorio.findAll(example);

        entidades.forEach(i -> posConsultarPor(i));

        return entidades;
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Page<E> filtrar(Pageable paginacao, E entidade) {
        Page<E> pagina = doFiltrar(paginacao, entidade);
        posFiltrar(pagina);

        return pagina;
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<E> todos() {
        return this.getRepositorio().findAll();
    }
}
