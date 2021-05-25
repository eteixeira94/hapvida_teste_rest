package br.com.isoftware.hapvida.entidade;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "consulta_has_animal")
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "animal", "consulta", "veterinario" })
@ToString(exclude = { "animal", "consulta", "veterinario" })
public @Data class ConsultaHasAnimal implements java.io.Serializable {

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "consultaId", column = @Column(name = "consulta_id", nullable = false)),
			@AttributeOverride(name = "animalId", column = @Column(name = "animal_id", nullable = false)),
			@AttributeOverride(name = "veterinarioId", column = @Column(name = "veterinario_id", nullable = false)) })
	private ConsultaHasAnimalId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_id", nullable = false, insertable = false, updatable = false)
	@JsonManagedReference("animals-consulta-reference")
	private Animal animal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consulta_id", nullable = false, insertable = false, updatable = false)
	@JsonManagedReference("consulta-itens-reference")
	private Consulta consulta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "veterinario_id", nullable = false, insertable = false, updatable = false)
	@JsonManagedReference("veterinarios-consulta-reference")
	private Veterinario veterinario;

}
