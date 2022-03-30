package it.polito.tdp.ricorsione.model;

import java.util.*;

public class ReginePrimaSoluzione {
	List<Integer> soluzione; //la prima soluzione
	
	
	public List<Integer> cercaRegine(int N) {
		this.soluzione=null;
		List<Integer> parziale = new ArrayList<Integer>(); //meglio array perchè mi trova prima un elemento con la get
		//non uso l'aggiunta in mezzo (che è costosa per l'arrayList ma non per la Linked)
		regine_ricorsiva(parziale, 0, N);
		return this.soluzione;
	}
	
	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N) {
		if(livello==N) {
		  System.out.println(parziale);
		  this.soluzione=new ArrayList<Integer>(parziale);
		  return false; //non continuare
	    } else {
	    	for(int col=0; col<N; col++) {
	    	  if(compatibile(col, parziale, livello)) {
	    	   parziale.add(col);
	    	   boolean continua=regine_ricorsiva(parziale, livello+1, N);
	    	   if(!continua)
	    		   return false;
	    	   parziale.remove(parziale.size()-1); 
	    	}
	      }return true;
	    }
	}
	
	private boolean compatibile(Integer col, List<Integer> parziale, int livello) {
		//controllo che non sia sulla stessa riga di altre regine
		if(parziale.indexOf(col)!=-1)
		return false;
		//controllo che non sia sulla stessa diagonale di altre regine
	    for(int riga=0; riga<parziale.size(); riga++) {
	    	  //regina nelle coordinate (R,C)=(riga, parziale.get(riga)) -> se sono uguali si mangiano
	    	  //confrontare con (R,C)=(livello,col)
	    	if((riga+parziale.get(riga))==(livello+col))
	    		return false;
	    	if((riga-parziale.get(riga))==(livello-col))
	    		return false;
	    }
	    return true;
	}
}
