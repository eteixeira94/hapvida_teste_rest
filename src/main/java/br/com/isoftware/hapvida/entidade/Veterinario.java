package br.com.isoftware.hapvida.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Table(name = "veterinario")
@EqualsAndHashCode(callSuper = true, exclude = { "consultaHasAnimals" })
@ToString(callSuper = true, exclude = { "consultaHasAnimals" })
@NoArgsConstructor
public @Data class Veterinario extends EntidadeGenerica<Integer> {

	@Column(name = "nome", nullable = false)
	@ApiModelProperty(value = "Nome do Veterinario")
	@NotNull(message = "Nome é obrigatório.")
	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;

	@Column(name = "telefone", nullable = false, length = 11)
	@ApiModelProperty(value = "Telefone do Veterinario")
	@NotNull(message = "Telefone é obrigatório.")
	@NotBlank(message = "Telefone não pode ser vazio.")
	private String telefone;

	@Column(name = "email", nullable = false)
	@ApiModelProperty(value = "Email do Veterinario")
	@NotNull(message = "Email é obrigatório.")
	@NotBlank(message = "Email não pode ser vazio.")
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "veterinario")
	@JsonBackReference("veterinarios-consulta-reference")
	private Set<ConsultaHasAnimal> consultaHasAnimals;

}
