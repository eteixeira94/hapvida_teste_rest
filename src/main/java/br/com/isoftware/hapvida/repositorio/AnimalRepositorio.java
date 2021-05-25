package br.com.isoftware.hapvida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.isoftware.hapvida.entidade.Animal;

@Repository
public interface AnimalRepositorio extends JpaRepository<Animal, Integer> {

}
