package br.com.isoftware.hapvida.servico;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.isoftware.hapvida.entidade.Consulta;
import br.com.isoftware.hapvida.entidade.ConsultaHasAnimal;
import br.com.isoftware.hapvida.entidade.enums.StatusConsulta;
import br.com.isoftware.hapvida.repositorio.ConsultaRepositorio;

@Service
public class ConsultaServico extends ServicoGenerico<Long, Consulta, ConsultaRepositorio> {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void salvar(Consulta e) {
        Set<ConsultaHasAnimal> itens = e.getConsultaHasAnimals();
        e.setConsultaHasAnimals(null);

        this.doSalvar(e);
        this.getRepositorio().save(e);

        if (itens != null && !itens.isEmpty()) {
            itens.forEach(i -> i.getId().setConsultaId(e.getId()));
            e.setConsultaHasAnimals(itens);
            this.getRepositorio().save(e);
        }

        this.posSalvar(e);
    }

    @Override
    public void doSalvar(Consulta entidade) {
        if (entidade.getId() == null) {
            entidade.setStatus(StatusConsulta.ABERTA);
        }
    }
}
