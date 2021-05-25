package br.com.isoftware.hapvida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.isoftware.hapvida.entidade.Consulta;

@Repository
public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {

}
