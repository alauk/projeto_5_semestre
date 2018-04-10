package br.usjt.projeto.entity;

import java.util.Date;

import javax.persistence.Column;
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

	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Column(name = "data_termino")
	private Date dataTermino;

	@Column(name = "previsao_inicio")
	@NotNull(message = "Campo previsaoInicio não pode ser nulo.")
	private Date previsaoInicio;

	@Column(name = "previsao_termino")
	@NotNull(message = "Campo previsaoTermino não pode ser nulo.")
	private Date previsaoTermino;

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Date getPrevisaoInicio() {
		return previsaoInicio;
	}

	public void setPrevisaoInicio(Date previsaoInicio) {
		this.previsaoInicio = previsaoInicio;
	}

	public Date getPrevisaoTermino() {
		return previsaoTermino;
	}

	public void setPrevisaoTermino(Date previsaoTermino) {
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
