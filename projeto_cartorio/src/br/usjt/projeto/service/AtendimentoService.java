package br.usjt.projeto.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	@Autowired
	private SenhaService senhaService;

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

	public List<Atendimento> listarAtendimento() throws IOException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		List<Atendimento> atendimentos = dao.listarAtendimento(fmt.format(new Date()));
		List<Atendimento> listaPreferencial = new ArrayList<>();
		List<Atendimento> listaNormal = new ArrayList<>();
		List<Atendimento> listaFinal = new ArrayList<>();
		for (Atendimento atendimento : atendimentos) {
			if (atendimento.getSenha().getFila().getNome().equals("Normal")) {
				listaNormal.add(atendimento);
			} else {
				listaPreferencial.add(atendimento);
			}
		}
		int posPref = 0;
		int posNormal = 0;
		for (int i = 0; i <= atendimentos.size() - 1; i++) {
			if (listaPreferencial.size() > posPref) {
				if (i == 0 || listaFinal.size() % 2 != 0) {
					listaFinal.add(listaPreferencial.get(posPref));
					posPref++;
				} else if (listaNormal.size() < posNormal) {
					listaFinal.add(listaPreferencial.get(posPref));
					posPref++;
				} else if (listaNormal.size() >= posNormal) {
					listaFinal.add(listaNormal.get(posNormal));
					posNormal++;
				}
			} else if (listaNormal.size() > posNormal) {
				listaFinal.add(listaNormal.get(posNormal));
				posNormal++;
			}
		}

		return listaFinal;
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

	public long pegarTempoTotalAtendimentoServico(int idServico) {
		return dao.pegarTempoTotalAtendimentoServico(idServico);
	}

	public long tempoMedioAtendimentoServico(int idServico, int qtdSenhas) {
		return dao.tempoMedioAtendimentoServico(idServico, qtdSenhas);
	}
	
	public void verificarUltimoSubservico(Atendimento atendimento) {
		List<Atendimento> lista = dao.verificarUltimoSubservico(atendimento);
		if(lista.isEmpty()) {
			senhaService.finalizarSenha(lista.get(0).getSenha());
		}
	}

	public List<Atendimento> listarFiltro(Servico servico1, SubServico subServico1) {
		return null;
	}

	public List<SubServico> carregarSubServicos() {
		// TODO Auto-generated method stub
		return null;
	}
}
