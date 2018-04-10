package br.usjt.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.SubServicoDAO;
import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.entity.SubServico;

@Service
public class SubServicoService {

	@Autowired
	private SubServicoDAO dao;

	public List<SubServico> carregarByServico(Servico servico) {
		return dao.carregarByServico(servico);
	}
}
