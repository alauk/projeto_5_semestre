package br.usjt.projeto.dao;

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
		Query query =  manager.createQuery("select s from Senha s where s.fila = :fila");
		query.setParameter("fila", fila);
		return (Senha) query.getSingleResult();
	}

	public Senha gerarSenha(Senha novaSenha) {
		manager.persist(novaSenha);
		return novaSenha;
	}

}
