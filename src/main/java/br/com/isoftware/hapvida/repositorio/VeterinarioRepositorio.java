package br.com.isoftware.hapvida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.isoftware.hapvida.entidade.Veterinario;

@Repository
public interface VeterinarioRepositorio extends JpaRepository<Veterinario, Integer> {

}
