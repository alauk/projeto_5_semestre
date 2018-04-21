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

import br.usjt.projeto.entity.Atendimento;
import br.usjt.projeto.entity.Servico;
import br.usjt.projeto.entity.SubServico;

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
	public List<Atendimento> listarAtendimento(String dataHoje) {
		Query query = manager.createQuery("select a from Atendimento a where a.status = 'ABERTO' and data_inicio like :hoje group by a.senha");
		query.setParameter("hoje", dataHoje + "%");
		return query.getResultList();
	}
	
	public long tempoMedioAtendimentoServico(int idServico, int qtdSenhas) {
		System.out.println("------------------------------------------");
		System.out.println("Tempo Médio Atendimento");
		System.out.println("Quantidade Senhas: " + qtdSenhas);
		System.out.println("Tempo médio: " + pegarTempoTotalAtendimentoServico(idServico) / qtdSenhas);
		long tempoMedio = pegarTempoTotalAtendimentoServico(idServico) / qtdSenhas;
		System.out.println("Tempo médio de Atendimento para o Serviço de id: " + idServico);
		System.out.println("Horas: " + (tempoMedio / (1000 * 60 * 60)) + " Minutos: " + ((tempoMedio / 60000) % 60) + " Segundos: " + ((tempoMedio / 1000) % 60) );
		System.out.println("------------------------------------------");
		return pegarTempoTotalAtendimentoServico(idServico) / qtdSenhas;
		
	}
	
	public long pegarTempoTotalAtendimentoServico(int idServico) {
		long tempoTotal = 0;
		long tempoInicio;
		long tempoFim;
		long horas;
		long minutos;
		Date data = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
		String sqlSelect = "SELECT atd.id, atd.id_senha, atd.id_subservico, atd.data_inicio, atd.data_termino, atd.status, sub_serv.id_servico FROM atendimento atd"
				+ " JOIN subservico sub_serv ON atd.id_subservico = sub_serv.id"
				+ " WHERE sub_serv.id_servico = ? AND atd.data_inicio LIKE ? ?";
		//DEVE SER ADICIONADO MAIS UMA CONDIÇÃO PARA VERIFICAR O STATUS DO ATENDIMENTO == FECHADO
		
		try (PreparedStatement pst = conn.prepareStatement(sqlSelect);){
			pst.setInt(1, idServico);
			pst.setString(2, fmt.format(data));
			pst.setString(3, "%");
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()) {
					System.out.println("------------------------------------------");
					System.out.println("Pegar Tempo Total Atendimento Servico");
					long horasInicio = (rs.getTime("data_inicio").getTime());
					System.out.println("hora inicio: " + rs.getTime("data_inicio"));
					System.out.println("hora inicio: " + horasInicio);
					long horasFim = (rs.getTime("data_termino").getTime());
					System.out.println("hora fim: " + rs.getTime("data_termino"));
					System.out.println("hora fim: " + horasFim);
					long diff = horasFim - horasInicio;
					System.out.println("diff: " + diff);
					System.out.println("Total: " + diff + " Horas: " + (diff / (1000 * 60 * 60)) + "Minutos: " + ((diff / 60000) % 60) + "Segundos: " + ((diff / 1000) % 60));
					tempoTotal = tempoTotal + diff;
				}
				System.out.println("Tempo Total Completo: " + tempoTotal + " Horas: " + (tempoTotal / (1000 * 60 * 60)) +
						" Minutos: " + ((tempoTotal / 60000) % 60) + " Segundos: " + ((tempoTotal / 1000) % 60));
				System.out.println("------------------------------------------");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tempoTotal;
	}
	
	public Atendimento carregar(int id) throws IOException {
		return manager.find(Atendimento.class, id);
	}
	
	public void gerarRegistroAtendimento(Atendimento atendimento) {
		manager.merge(atendimento);
	}
	
	public void fecharRegistroAtendimento(Atendimento atendimento) {
		manager.merge(atendimento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Servico> carregarServicos() {
		return manager.createQuery("select s from Servico s").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SubServico> carregarSubServicos(){
		return manager.createQuery("select sub from SubServico sub").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentosFiltrado(Servico idServico, SubServico idSubServico) {
		String sqlQuery = "select a from Atendimento a " 
				+ " INNER JOIN a.subservico sb " 
				+ " INNER JOIN a.senha s"
				+ " INNER JOIN s.fila"
				+ " WHERE a.subservico = :idSubServico AND sb.servico = :idServico";
		Query query = manager.createQuery(sqlQuery);
		query.setParameter("idSubServico", idSubServico);
		query.setParameter("idServico", idServico);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Atendimento> verificarUltimoSubservico(Atendimento atendimento) {
		Query query = manager.createQuery("select a from Atendimento a where a.senha :idAtendimento and a.status='ABERTO'");
		query.setParameter("idAtendimento", atendimento.getSenha());
		return query.getResultList();
	}

}
