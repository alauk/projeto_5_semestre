package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Atendimento;

@Repository
public class AtendimentoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public Atendimento gerarAtendimento(Atendimento newAtendimento) {
		manager.persist(newAtendimento);
		return newAtendimento;
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimento() {
		return manager.createQuery("select a from Atendimento a").getResultList();
	}

}
