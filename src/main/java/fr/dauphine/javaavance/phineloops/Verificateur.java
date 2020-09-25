package fr.dauphine.javaavance.phineloops;
import java.util.ArrayList;

public class Verificateur {
	private Grille g;
	
	public Verificateur() {
		g=new Grille();
	}
	
	public boolean verif(String inputFile) {
		g.Read(inputFile);
		ArrayList<Contrainte> list_contraintes=g.getContraintes();
		for(Contrainte c: list_contraintes) {
			if(!c.satisfaite()) {
				//System.out.println("erreur sur la "+list_contraintes.indexOf(c)+" eme contrainte ");
				return false;
			}
		}
		return true; 
	}
}
