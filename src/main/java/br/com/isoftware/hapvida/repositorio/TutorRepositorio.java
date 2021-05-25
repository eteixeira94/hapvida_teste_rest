package br.com.isoftware.hapvida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.isoftware.hapvida.entidade.Tutor;

@Repository
public interface TutorRepositorio extends JpaRepository<Tutor, Integer> {

}
