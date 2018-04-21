package br.usjt.projeto.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Fila;
import br.usjt.projeto.entity.Senha;

@Repository
public class SenhaDAO {
	
	@PersistenceContext
	EntityManager manager;
	private Connection conn;
	
	@Autowired
	public SenhaDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

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
	
	public int pegarTotalSenhasServico(int idServico) {
		Date data = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		int contador = 0;
		String sqlSelect = "select * FROM senha senha" + 
				" LEFT JOIN atendimento atd ON atd.id_senha = senha.id" + 
				" LEFT JOIN subservico sub_serv ON atd.id_subservico = sub_serv.id" +
				" WHERE sub_serv.id_servico = ? AND senha.data_abertura LIKE ? ?";
		try(PreparedStatement pst = conn.prepareStatement(sqlSelect);) {
			pst.setInt(1, idServico);
			pst.setString(2, fmt.format(data));
			pst.setString(3, "%");
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()) {
					contador = contador + 1;
				}
				return contador;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void finalizarSenha(Senha senha) {
		manager.merge(senha);
	}

}
