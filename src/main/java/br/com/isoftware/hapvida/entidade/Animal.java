package br.com.isoftware.hapvida.entidade;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.isoftware.hapvida.entidade.enums.RacaEnum;
import br.com.isoftware.hapvida.util.HapvidaDateDeserializer;
import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(value = "Nome do Animal")
	@Column(name = "nome", nullable = false)
	@NotNull(message = "Nome é obrigatório.")
	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;

	@ApiModelProperty(value = "Espécie do Animal")
	@Column(name = "especie", nullable = false, length = 30)
	@NotNull(message = "Espécie é obrigatório.")
	@NotBlank(message = "Espécie não pode ser vazio.")
	private String especie;

	@Column(name = "raca", nullable = false)
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Raça do Animal")
	@NotNull(message = "Raça é obrigatório.")
	@NotBlank(message = "Raça não pode ser vazio.")
	private RacaEnum raca;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_nascimento", nullable = false, length = 10)
	@JsonDeserialize(using = HapvidaDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@ApiModelProperty(value = "Data de Nascimento do Animal")
	@NotNull(message = "Data de Nascimento é obrigatório.")
	private Date dataNascimento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "animal")
	@JsonBackReference("animals-consulta-reference")
	private Set<ConsultaHasAnimal> consultaHasAnimals;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "animal_has_tutor", joinColumns = {
			@JoinColumn(name = "animal_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "tutor_id", nullable = false, updatable = false) })
	@JsonBackReference("tutors-reference")
	private Set<Tutor> tutors;
}
