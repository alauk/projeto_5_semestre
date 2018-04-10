package br.usjt.projeto.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
		Senha novaSenha = new Senha();
		novaSenha.setDataAbertura(new Date());
		novaSenha.setFila(fila);
		Senha ultimaSenha = getLastSenha(fila);
		if (isEmpty(ultimaSenha)) {
			return geraPrimeiraSenhaDia(novaSenha, fila, siglaServico);
		}
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime dataUltimaSenha = LocalDateTime.ofInstant(ultimaSenha.getDataAbertura().toInstant(),
				ZoneId.systemDefault());
		if (!(now.getMonth().equals(dataUltimaSenha.getMonth())
				&& now.getDayOfMonth() == dataUltimaSenha.getDayOfMonth())) {
			return geraPrimeiraSenhaDia(novaSenha, fila, siglaServico);
		}
		novaSenha.setNumero(ultimaSenha.getNumero() + 1);
		novaSenha.setCodigo(createCodigo(fila, siglaServico, novaSenha.getNumero()));
		return dao.gerarSenha(novaSenha);
	}

	public Senha carregar(Fila fila) {
		return dao.carregar(fila);
	}

	public Senha getLastSenha(Fila fila) {
		List<Senha> senhasFila = dao.getLastSenha(fila);
		if (senhasFila.isEmpty()) {
			return null;
		}
		return senhasFila.get(senhasFila.size() - 1);
	}

	private Senha geraPrimeiraSenhaDia(Senha primeiraSenha, Fila fila, String siglaServico) {
		primeiraSenha.setNumero(1);
		primeiraSenha.setCodigo(createCodigo(fila, siglaServico, 1));
		return dao.gerarSenha(primeiraSenha);
	}

	private String createCodigo(Fila fila, String siglaServico, Integer numero) {
		return fila.getSigla() + siglaServico + numero;
	}

}
