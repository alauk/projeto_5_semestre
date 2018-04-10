package br.usjt.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Atendimento;

@Repository
public class AtendimentoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gerarAtendimento(Atendimento atendimento) {
		manager.persist(atendimento);
	}

}
