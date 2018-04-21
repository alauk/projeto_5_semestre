package br.usjt.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.entity.SubServico;
import br.usjt.projeto.service.AtendimentoService;
import br.usjt.projeto.service.SenhaService;
import br.usjt.projeto.service.ServicoService;
import br.usjt.projeto.service.SubServicoService;

@Controller
public class AtendimentoController {

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private SenhaService senhaService;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private SubServicoService subServicoService;

	@RequestMapping("/lista_atendimento")
	public String listaAtendimento(Model model) {
		try {
			model.addAttribute("atendimentos", atendimentoService.listarAtendimento());
			return "PainelAtendimentoInterno";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/registro_atendimento")
	public String gerarRegistroAtendimento(Atendimento atendimento, Model model) {
		try {
			atendimento = atendimentoService.carregar(atendimento.getId());
			model.addAttribute("atendimento", atendimento);
			return "RegistraAtendimento";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/fechar_atendimento")
	public String fecharAtendimento(Atendimento atendimento, Model model) {
		try {
			atendimento = atendimentoService.carregar(atendimento.getId());
			model.addAttribute("atendimento", atendimento);
			//mudar para service de sub servico
			atendimentoService.verificarUltimoSubservico(atendimento);
			return "FechaAtendimento";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/teste_tempo")
	public String pegarTempoTotalAtendimentoServico(int idServico, Model model) {
		atendimentoService.tempoMedioAtendimentoServico(idServico, senhaService.pegarTotalSenhasServico(idServico));
		return "TesteTempo";
	}

	@RequestMapping("/filtra_atendimento")
	public String filtraAtendimento(Model model, int servico, int subServico) {
		Servico servico1 = servicoService.carregar(servico);
		SubServico subServico1 = subServicoService.carregar(subServico);
		List<Atendimento> atendimentos = atendimentoService.listarFiltro(servico1, subServico1);
		model.addAttribute("atendimentos", atendimentos);
		return "RegistroAtendimento";
	}

	@RequestMapping("/registro_atendimento")
	public String registrarAtendimento(Model model) {
		try {

			List<Servico> servicos = new ArrayList<>();
			List<SubServico> subServicos = new ArrayList<>();
			servicos = servicoService.carregarServicos();
			subServicos = atendimentoService.carregarSubServicos();
			model.addAttribute("servicos", servicos);
			model.addAttribute("subServicos", subServicos);
			return "RegistroAtendimento";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
