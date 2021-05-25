package br.com.isoftware.hapvida.entidade;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.isoftware.hapvida.entidade.enums.StatusConsulta;
import br.com.isoftware.hapvida.util.HapvidaDateTimeDeserializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "consulta")
@EqualsAndHashCode(callSuper = true, exclude = { "consultaHasAnimals" })
@ToString(callSuper = true, exclude = { "consultaHasAnimals" })
@NoArgsConstructor
public @Data class Consulta extends EntidadeGenerica<Long> {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_consulta", nullable = false, length = 10)
	@JsonDeserialize(using = HapvidaDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@ApiModelProperty(value = "Data da Consulta")
	@NotNull(message = "Data de Consulta é obrigatório.")
	private Date dataConsulta;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 45)
	@ApiModelProperty(value = "Status da Consulta")
	private StatusConsulta status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "consulta", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonBackReference("consulta-itens-reference")
	private Set<ConsultaHasAnimal> consultaHasAnimals;
}
