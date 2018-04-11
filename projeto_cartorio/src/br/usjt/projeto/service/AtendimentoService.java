package br.usjt.projeto.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.projeto.dao.AtendimentoDAO;
import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Senha;
import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.entity.SubServico;
import br.usjt.projeto.entity.TipoStatus;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoDAO dao;

	@Autowired
	private SubServicoService subServicoService;

	@Autowired
	private ServicoService servicoService;

	public void gerarAtendimento(Senha novaSenha, String siglaServico) {
		Servico servico = servicoService.carregarBySigla(siglaServico);
		List<SubServico> subServicos = subServicoService.carregarByServico(servico);
		for (SubServico subServico : subServicos) {
			Atendimento atendimento = new Atendimento();
			atendimento.setStatus(TipoStatus.ABERTO);
			atendimento.setPrevisaoInicio(new Date());
			atendimento.setPrevisaoTermino(new Date());
			atendimento.setSenha(novaSenha);
			atendimento.setSubservico(subServico);
			dao.gerarAtendimento(atendimento);
			
		}

	}
	
	public List<Atendimento> listarAtendimento() throws IOException{
		return dao.listarAtendimento();
	}

}
