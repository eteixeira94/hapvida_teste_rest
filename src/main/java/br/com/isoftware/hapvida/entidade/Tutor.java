package br.com.isoftware.hapvida.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "Nome do Tutor")
	@NotNull(message = "Nome é obrigatório.")
	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;

	@Column(name = "telefone", nullable = false, length = 11)
	@ApiModelProperty(value = "Telefone do Tutor")
	@NotNull(message = "Telefone é obrigatório.")
	@NotBlank(message = "Telefone não pode ser vazio.")
	private String telefone;

	@Column(name = "email", nullable = false)
	@ApiModelProperty(value = "Email do Tutor")
	@NotNull(message = "Email é obrigatório.")
	@NotBlank(message = "Email não pode ser vazio.")
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "animal_has_tutor", joinColumns = {
			@JoinColumn(name = "tutor_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "animal_id", nullable = false, updatable = false) })
	@JsonBackReference("animals-reference")
	private Set<Animal> animals;

}
