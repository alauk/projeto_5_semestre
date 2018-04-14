package br.usjt.projeto.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.projeto.entity.Atendimento;

@Repository
public class AtendimentoDAO {
	

	@PersistenceContext
	private EntityManager manager;
	private Connection conn;

	
	
	@Autowired
	public AtendimentoDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	public Atendimento gerarAtendimento(Atendimento newAtendimento) {
		manager.persist(newAtendimento);
		return newAtendimento;
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimento() {
		return manager.createQuery("select a from Atendimento a where a.status = 'ABERTO' group by a.senha").getResultList();
	}
	
	public double tempoMedioAtendimento(int totalSenha, int tempoTotalAtendimento) {
		return -1;
	}
	
	public double pegarTempoTotalAtendimentoServico(int idServico) {
		double tempoTotal = 0;
		double tempoInicio;
		double tempoFim;
		long horas;
		long minutos;
		String sqlSelect = "SELECT atd.id, atd.id_senha, atd.id_subservico, atd.data_inicio, atd.data_termino, atd.status, sub_serv.id_servico FROM atendimento atd"
				+ "JOIN subservico sub_serv ON atd.id_subservico = sub_serv.id"
				+ "WHERE sub_serv.id_servico = :id_servico";
		
		try (PreparedStatement pst = conn.prepareStatement(sqlSelect);){
			pst.setInt(1, idServico);
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()) {
					long horasInicio = (rs.getDate("data_inicio").getTime());
					long horasFim = (rs.getDate("data_termino").getTime());
					tempoTotal = tempoTotal + ((horasFim - horasInicio) / 3600000);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tempoTotal;
	}

}
