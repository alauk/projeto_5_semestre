package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Servico;

@Repository
public class ServicoDAO {
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Servico> carregaServicos() {
		return manager.createQuery("select s from Servico s").getResultList();
	}

	public Servico carregarBySigla(String siglaServico) {
		Query query = manager.createQuery("select s from Servico s where s.sigla = :sigla");
		query.setParameter("sigla", siglaServico);
		return (Servico) query.getSingleResult();
	}

	public Servico carregarById(int servico) {
		Query query = manager.createQuery("select s from Servico s where s.id = :id");
		query.setParameter("id", servico);
		return (Servico) query.getSingleResult();
	}
}
