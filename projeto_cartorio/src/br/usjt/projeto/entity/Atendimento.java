package br.usjt.projeto.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	private TipoStatus status;

	private LocalDateTime dataInicio;

	private LocalDateTime dataTermino;

	@NotNull(message = "Campo previsaoInicio não pode ser nulo.")
	private LocalDateTime previsaoInicio;

	@NotNull(message = "Campo previsaoTermino não pode ser nulo.")
	private LocalDateTime previsaoTermino;

	@ManyToOne
	@JoinColumn(name = "id_senha")
	private Senha senha;

	@ManyToOne
	@JoinColumn(name = "id_subservico")
	private SubServico subservico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoStatus getStatus() {
		return status;
	}

	public void setStatus(TipoStatus status) {
		this.status = status;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public LocalDateTime getPrevisaoInicio() {
		return previsaoInicio;
	}

	public void setPrevisaoInicio(LocalDateTime previsaoInicio) {
		this.previsaoInicio = previsaoInicio;
	}

	public LocalDateTime getPrevisaoTermino() {
		return previsaoTermino;
	}

	public void setPrevisaoTermino(LocalDateTime previsaoTermino) {
		this.previsaoTermino = previsaoTermino;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public SubServico getSubservico() {
		return subservico;
	}

	public void setSubservico(SubServico subservico) {
		this.subservico = subservico;
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", status=" + status + ", dataInicio=" + dataInicio + ", dataTermino="
				+ dataTermino + ", previsaoInicio=" + previsaoInicio + ", previsaoTermino=" + previsaoTermino
				+ ", senha=" + senha + ", subservico=" + subservico + "]";
	}

}
