package br.com.isoftware.hapvida.entidade;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.isoftware.hapvida.util.HapvidaDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "animal")
@EqualsAndHashCode(callSuper = true, exclude = { "consultaHasAnimals", "tutors" })
@ToString(callSuper = true, exclude = { "consultaHasAnimals", "tutors" })
@NoArgsConstructor
public @Data class Animal extends EntidadeGenerica<Integer> {

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "especie", nullable = false, length = 30)
	private String especie;

	@Column(name = "raca", nullable = false)
	private String raca;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_nascimento", nullable = false, length = 10)
	@JsonDeserialize(using = HapvidaDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "animal")
	private Set<ConsultaHasAnimal> consultaHasAnimals;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "animal_has_tutor", joinColumns = {
			@JoinColumn(name = "animal_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "tutor_id", nullable = false, updatable = false) })
	private Set<Tutor> tutors;
}
