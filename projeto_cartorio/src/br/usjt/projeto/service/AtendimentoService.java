package br.usjt.projeto.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
		//COMPLETAR ORDENAÇÃO
		List<Atendimento> atendimentos = dao.listarAtendimento();
		List<Atendimento> atendimentosHoje = new ArrayList<>();
		for (Atendimento atendimento : atendimentos) {
			Senha senha = atendimento.getSenha();
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime dataAbertura = LocalDateTime.ofInstant(senha.getDataAbertura().toInstant(), ZoneId.systemDefault());
			if (now.getMonth().equals(dataAbertura.getMonth()) && now.getDayOfMonth() == dataAbertura.getDayOfMonth()) {
				atendimentosHoje.add(atendimento);
			}
		}
		List<Atendimento> atedimentoOrdenado = new ArrayList<>();
		atendimentosHoje.sort((a1, a2) -> a1.getSenha().getFila().getNome().compareTo(a2.getSenha().getFila().getNome()));
		return atendimentosHoje;
	}
	
	
	public Atendimento carregar(int id) throws IOException {
		return dao.carregar(id);
	}
	
	public void gerarRegistrarAtendimento(Atendimento atendimento) {
		atendimento.setDataInicio(new Date());
		atendimento.setStatus(TipoStatus.EM_ANDAMENTO);
		dao.gerarRegistroAtendimento(atendimento);
	}
	
	public void fecharRegistroAtendimento(Atendimento atendimento) {
		atendimento.setDataTermino(new Date());
		atendimento.setStatus(TipoStatus.FINALIZADO);
		dao.fecharRegistroAtendimento(atendimento);
	}
}
