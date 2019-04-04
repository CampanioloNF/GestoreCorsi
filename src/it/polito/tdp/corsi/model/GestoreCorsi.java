package it.polito.tdp.corsi.model;

import java.util.*;


import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.model.Corso;

public class GestoreCorsi {

	
	private Map <String, Corso> mappaCorsi; 
	private CorsoDAO daoC;
	
	public GestoreCorsi() {
		
		this.mappaCorsi = new HashMap <String, Corso>();
		daoC = new CorsoDAO();
		
		//popoliamo la mappaCorsi
		
		for(Corso c : daoC.listAll()) {
			this.mappaCorsi.put(c.getCodins(), c);
		}
		
	}

	/**
	 * Dato un periodo didattico ritorna tutti i {@link Corso} con tale periodo
	 * @param periodo
	 * @return ritorna tutti i {@link Corso} con tale periodo
	 */
	
	public List<Corso> getCorsiByPeriodo(int periodo) {
		
		
		//Soluzione 1
		
		//CorsoDAO dao = new CorsoDAO();
		/*List<Corso> corsi = dao.listAll();
		List<Corso> result = new ArrayList<Corso>();
		
		for(Corso c : corsi) {
			if(c.getPd() == periodo) {
				result.add(c);
			}
		}*/
		
		
		
		//Soluzione 2
	
		//CorsoDAO dao = new CorsoDAO();
		//return dao.listCorsiByPD(periodo);
		
		//Soluzione 3
		
		List <Corso> result = new ArrayList <Corso>();
		
		for(Corso c : this.mappaCorsi.values())	{
			
			if(c.getPd()==periodo)
				result.add(c);
		}
		
		return result;
	}
	
	public Map <Corso, Integer> getIscrittiCorsi(int periodo){
	    
		
		Map <String, Integer> mappaNIscritti = daoC.getIscrittiCorsi(periodo);
 
		Map <Corso, Integer> result = new HashMap <Corso, Integer>();
		
		for(String cod : mappaNIscritti.keySet()) {
			
			if(this.mappaCorsi.containsKey(cod))
				result.put(this.mappaCorsi.get(cod), mappaNIscritti.get(cod));
			
		}
		
		return result;
 		
		
		
	}

	
}
