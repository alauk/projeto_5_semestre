package br.usjt.projeto.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Senha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Campo numero não pode ser nulo.")
	private int numero;

	@NotNull(message = "Campo codigo não pode ser nulo.")
	private String codigo;

	private Date data_abertura;

	private Date data_fechamento;

	@ManyToOne
	@JoinColumn(name = "id_fila")
	private Fila fila;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Date getData_fechamento() {
		return data_fechamento;
	}

	public void setData_fechamento(Date data_fechamento) {
		this.data_fechamento = data_fechamento;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	@Override
	public String toString() {
		return "Senha [id=" + id + ", numero=" + numero + ", codigo=" + codigo + ", data_abertura=" + data_abertura
				+ ", data_fechamento=" + data_fechamento + ", fila=" + fila + "]";
	}

}