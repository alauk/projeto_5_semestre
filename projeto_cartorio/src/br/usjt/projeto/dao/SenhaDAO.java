package br.usjt.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Fila;
import br.usjt.projeto.entity.Senha;

@Repository
public class SenhaDAO {
	
	@PersistenceContext
	EntityManager manager;

	public Senha carregar(Fila fila) {
		Query query =  manager.createQuery("select s from Senha s where id = :fila");
		query.setParameter("fila", fila.getId());
		return (Senha) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> getLastSenha(Fila fila) {
		Query query =  manager.createQuery("select s from Senha s inner join s.fila where s.fila.sigla = :sigla");
		query.setParameter("sigla", fila.getSigla());
		return query.getResultList();
	}

	public Senha gerarSenha(Senha novaSenha) {
		manager.persist(novaSenha);
		return novaSenha;
	}

}
