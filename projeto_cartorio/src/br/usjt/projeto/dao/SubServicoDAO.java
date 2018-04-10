package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.entity.SubServico;

@Repository
public class SubServicoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<SubServico> carregarByServico(Servico servico) {
		Query query = manager.createQuery("select s from SubServico s where s.servico = :servico");
		query.setParameter("servico", servico);
		return query.getResultList();
	}
	
}
