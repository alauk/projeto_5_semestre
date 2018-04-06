package br.usjt.projeto.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.SenhaDAO;
import br.usjt.projeto.entity.Fila;
import br.usjt.projeto.entity.Senha;

@Service
public class SenhaService {

	@Autowired
	private SenhaDAO dao;

	@Autowired
	private FilaService filaService;

	public Senha gerarSenha(String siglaFila, String siglaServico) {
		Fila fila = filaService.carregar(siglaFila);
		Senha ultimaSenha = carregar(fila);
		Senha novaSenha = new Senha();
		novaSenha.setData_abertura(new Date());
		novaSenha.setFila(fila);
		novaSenha.setNumero(ultimaSenha.getNumero() + 1);
		novaSenha.setCodigo(fila.getSigla() + siglaServico + novaSenha.getNumero());
		return dao.gerarSenha(novaSenha);
	}

	public Senha carregar(Fila fila) {
		return dao.carregar(fila);
	}

}
