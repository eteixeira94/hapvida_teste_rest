package br.com.isoftware.hapvida.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "veterinario")
@EqualsAndHashCode(callSuper = true, exclude = { "consultaHasAnimals" })
@ToString(callSuper = true, exclude = { "consultaHasAnimals" })
@NoArgsConstructor
public @Data class Veterinario extends EntidadeGenerica<Integer> {

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "telefone", nullable = false, length = 11)
	private String telefone;

	@Column(name = "email", nullable = false)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "veterinario")
	private Set<ConsultaHasAnimal> consultaHasAnimals;
}
