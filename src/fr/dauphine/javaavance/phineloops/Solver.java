package fr.dauphine.javaavance.phineloops;


import java.io.File;

import org.chocosolver.solver.Model;


public class Solver {
	
	private Grille g;
	public Solver() {
		g=new Grille();
	
	}
	
	public boolean solve(String inputFile, String outputFile) {
		g.Read(inputFile);
		Model m=new Model();
		Verificateur v=new Verificateur();
		if(v.verif(inputFile)) {
			System.out.println("Déjà résolue !");
			return true;
		}
			
		else {
			for(int i=0;i<g.getHauteur();i++) {
				for(int j=0;j<g.getLargeur();j++) {
					//System.out.println("zz"+g.getGrid()[i][j].affiche2());
					g.getGrid()[i][j].getVar(m);
					//System.out.println(g.getGrid()[i][j].affichetab());
				}
			}
			
			for(int i=0;i<(g.getGrid().length);i++) {
				for(int j=0;j<g.getGrid()[i].length;j++) {
						
						if(j-1>=0) {
							
							m.arithm(g.getGrid()[i][j-1].getTab()[1],"=", g.getGrid()[i][j].getTab()[3]).post();//contrainte cote à cote (EO)
						}
						else {
							m.arithm(g.getGrid()[i][j].getTab()[3],"=",0).post();//bord côté gauche
								
						}
						
						if(j+1==g.getLargeur()) {
							m.arithm(g.getGrid()[i][j].getTab()[1],"=",0).post();//bord cote droit
						}
						
						if(i-1>=0){
							m.arithm(g.getGrid()[i-1][j].getTab()[2],"=", g.getGrid()[i][j].getTab()[0]).post();	//NS
						}
						else {
							m.arithm(g.getGrid()[0][j].getTab()[0],"=",0).post();//bord du haut
							
						}
						
						if(i+1==g.getHauteur()) {
							m.arithm(g.getGrid()[i][j].getTab()[2],"=",0).post();//bord du bas
						}
						
				}
	
			}
		}
	
			
			if(m.getSolver().solve()) {
			    
				   System.out.println("found solution: "+m.getSolver().solve());
			    	
			    	for(int i=0;i<g.getHauteur();i++) {
						for(int j=0;j<g.getLargeur();j++) {
							g.getGrid()[i][j].update();
							System.out.println(g.getGrid()[i][j].affiche2());
						}
			
			    	}
				File fichier = new File(outputFile);
		    	g.Write(fichier);
		    	return true;
			}
			return false;
					
			
		}
	
		
}
