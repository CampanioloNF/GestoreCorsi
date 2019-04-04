package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

	public List<Studente> elencaStudenti(String codins) {
	
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? ";
		
		List <Studente> result = new ArrayList <Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setString(1, codins);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS"));
				
				result.add(s);
			}
			    //sempre ricordarsi di chiudere le connessioni
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
		return result;
		
		
	}

	public List<Studente> elencaStudentiByCDS(String codins) {
		
		String sql = "SELECT  s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? "
				+ "GROUP BY s.CDS, s.matricola ";
		
		List <Studente> result = new ArrayList <Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setString(1, codins);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS"));
				
				result.add(s);
			}
			    //sempre ricordarsi di chiudere le connessioni
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
		return result;
		
	}

}
