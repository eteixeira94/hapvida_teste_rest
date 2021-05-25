package br.com.isoftware.hapvida.entidade;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public @Data class ConsultaHasAnimalId implements java.io.Serializable {

	@Column(name = "consulta_id", nullable = false)
	private Long consultaId;

	@Column(name = "animal_id", nullable = false)
	private Integer animalId;

	@Column(name = "veterinario_id", nullable = false)
	private Integer veterinarioId;
}
