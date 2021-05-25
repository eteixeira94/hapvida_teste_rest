package br.com.isoftware.hapvida.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tutor")
@EqualsAndHashCode(callSuper = true, exclude = { "animals" })
@ToString(callSuper = true, exclude = { "animals" })
@NoArgsConstructor
public @Data class Tutor extends EntidadeGenerica<Integer> {

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "telefone", nullable = false, length = 11)
	private String telefone;

	@Column(name = "email", nullable = false)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "animal_has_tutor", joinColumns = {
			@JoinColumn(name = "tutor_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "animal_id", nullable = false, updatable = false) })
	private Set<Animal> animals;

}
