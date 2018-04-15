package br.usjt.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.service.AtendimentoService;

public class AtendimentoController {

	@Autowired
	private AtendimentoService atendimentoService;
	
	@RequestMapping("/registro_atendimento")
	public String gerarRegistroAtendimento(Atendimento atendimento, Model model) {
		try {
			atendimento = atendimentoService.carregar(atendimento.getId());
			model.addAttribute("atendimento", atendimento);
			return "RegistraAtendimento";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/fechar_atendimento")
	public String fecharAtendimento(Atendimento atendimento, Model model) {
		try {
			//finaliza o atendimento do subserviço
			atendimento = atendimentoService.carregar(atendimento.getId());
			model.addAttribute("atendimento", atendimento);
			
			//busca os atendimentos referente a mesma senha que essa sendo atendida
			
			
			//verifica se existe um próximo subserviço para essa senha
			
			return "FechaAtendimento";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}
