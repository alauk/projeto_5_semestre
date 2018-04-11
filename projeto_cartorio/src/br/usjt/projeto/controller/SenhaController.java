package br.usjt.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Senha;
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
		return "GeraSenha";
	}
	
	@RequestMapping("/lista_atendimento")
	public String listarChamadosExibir( BindingResult result, Model model) {
		try {
			
			List<Atendimento> atendimentos = atendimentoService.listarAtendimento();
			model.addAttribute("atendimentos", atendimentos);
			
			return "ListaAtendimento";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/gera_senha")
	public String geraSenha(String fila, String servico, Model model) {
		try {
			Senha novaSenha = service.gerarSenha(fila, servico);
			atendimentoService.gerarAtendimento(novaSenha, servico);
			model.addAttribute("senha", novaSenha);
			return "SenhaGerada";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
