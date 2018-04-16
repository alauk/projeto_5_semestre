package br.usjt.projeto.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.projeto.entity.Senha;
import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.service.AtendimentoService;
import br.usjt.projeto.service.FilaService;
import br.usjt.projeto.service.SenhaService;
import br.usjt.projeto.service.ServicoService;

@Controller
@Transactional
public class SenhaController {

	@Autowired
	private SenhaService service;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private FilaService filaService;
	
	@Autowired
	private AtendimentoService atendimentoService;

	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	@RequestMapping("/form_senha")
	public String formSenha(Model model) {
		model.addAttribute("servicos", servicoService.carregaServicos());
		model.addAttribute("filas", filaService.carregarFilas());
		return "NovaSenha";
	}
	
	@RequestMapping("/gera_senha")
	public String geraSenha(String fila, String servico, Model model) {
		try {
			Senha novaSenha = service.gerarSenha(fila, servico);
			atendimentoService.gerarAtendimento(novaSenha, servico);
			Servico novoServico = servicoService.carregarBySigla(servico);
			model.addAttribute("senha", novaSenha);
			model.addAttribute("servico", novoServico);
			return "SenhaGerada";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	

	@RequestMapping("cont_senhas")
	public String contarSenhasServico(int idServico, Model model) {
		int qtd = service.contarSenhasServico(idServico);
		return "TesteContador";
	}

}
