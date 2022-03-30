package it.polito.tdp.ricorsione.model;

import java.util.*;

public class Regine {
	List<List<Integer>> tutte; //tutte le soluzioni
	
	
	public List<List<Integer>> cercaRegine(int N) {
		this.tutte= new ArrayList<List<Integer>>();
		List<Integer> parziale = new ArrayList<Integer>(); //meglio array perchè mi trova prima un elemento con la get
		//non uso l'aggiunta in mezzo (che è costosa per l'arrayList ma non per la Linked)
		regine_ricorsiva(parziale, 0, N);
		return this.tutte;
	}
	
	private void regine_ricorsiva(List<Integer> parziale, int livello, int N) {
		//siamo nel caso terminale?
		//livello=i -> devo riempire la riga i
		if(livello==N) {
		  System.out.println(parziale);
		  this.tutte.add(new ArrayList<Integer>(parziale));
	    } else {
	    	//ho già parziale[0] fino a parziale[livello-1] gia' decisi
	    	//devo decidere parziale di livello (<N) tra tutti i valori (colonne) possibili da 0 a N-1
	    	for(int col=0; col<N; col++) {
	    	  if(compatibile(col, parziale, livello)) {
	    	   parziale.add(col);
	    	   regine_ricorsiva(parziale, livello+1, N);
	    	   parziale.remove(parziale.size()-1); //backtracking dopo che ho fatto un tentativo 
	    //a prescindere che sia andato a buon fine o meno, devo rimettere tutto come prima per farne un altro
	    	}
	      }
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
