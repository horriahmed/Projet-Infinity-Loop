package fr.dauphine.javaavance.phineloops;
import java.io.File;
import java.util.ArrayList;

public class Generateur {
	
	public Generateur() {}
	
	public void generate(int width, int height, String outputFile) {
		Grille g= new Grille(width, height);
		ArrayList<Contrainte> list_contraintes=g.getContraintes();
		for(int i=0;i<g.getGrid().length;i++) {//met tts les cases de la grille avec des pièces vides 
			for(int j=0;j<g.getGrid()[i].length;j++) {
				g.getGrid()[i][j]=new Piece();
			}
		}
		
		for(int i=1;i<(g.getGrid().length);i++) {//sauf première ligne
			for(int j=1;j<g.getGrid()[i].length;j++) {//sauf 1ere colonne 
				g.addContrainte(new ContrainteBinaire(g.getGrid()[i-1][j],g.getGrid()[i][j],2,0));//contrainte haut et bas (SD)
				g.addContrainte(new ContrainteBinaire(g.getGrid()[i][j-1],g.getGrid()[i][j],1,3));//contrainte cote à cote (EO)
			}
			g.addContrainte(new ContrainteBinaire(g.getGrid()[i-1][0],g.getGrid()[i][0],2,0));//contrainte haut et bas (SD) pour la 1ère colonne 
		}
		for(int j=1;j<g.getGrid()[0].length;j++) {
			g.addContrainte(new ContrainteBinaire(g.getGrid()[0][j-1],g.getGrid()[0][j],1,3));//contrainte cote à cote (EO) pour la première ligne 
		}
		
		for(int k=0;k<list_contraintes.size();k++) {
            double r=(Math.random()*1);/// entre 0 et 1 
            if(r>=0.5){ // on prends 50% de la grille 
				Piece p1=list_contraintes.get(k).getP1();
				Piece p2=list_contraintes.get(k).getP2();
				System.out.println("Contrainte entre piece "+"("+g.getAbs(p1)+","+g.getOrd(p1)+") et piece "+"("+ g.getAbs(p2)+","+g.getOrd(p2)+") :"+list_contraintes.get(k).affiche());
				list_contraintes.get(k).getP1().connexion(list_contraintes.get(k).getsens1());//ajout contrainte connexion sens donné par la contrainte pour la pièce p1
				list_contraintes.get(k).getP2().connexion(list_contraintes.get(k).getsens2());//ajout contrainte connexion sens donné par la contrainte pour la pièce p2
            }
		
		}
		
		
		//rendre iterable?
		for(int i=0;i<g.getGrid().length;i++) {
			for(int j=0;j<g.getGrid()[i].length;j++) {
				System.out.println("Num piece et orientation AVANT update de piece ("+i+","+j+") : "+g.getGrid()[i][j].affiche2()+"   tabconnex= "+g.getGrid()[i][j].affiche());
				g.getGrid()[i][j].update_numpiece();
				g.getGrid()[i][j].randomOrientation();
				System.out.println("Num piece et orientation APRES update de piece ("+i+","+j+") : "+g.getGrid()[i][j].affiche2()+"   tabconnex= "+g.getGrid()[i][j].affiche());
				g.getGrid()[i][j].update_connexions();
				System.out.println("tab connexions APRES update prenant en compte l'orientation ("+i+","+j+") : tabconnex= "+g.getGrid()[i][j].affiche());
			}
		}
		g.Write(new File(outputFile));
	}
	
	
	
	//TEST: Generateur g=new Generateur();
	//g.generate(3,4,"/Users/hafsaaouame/Desktop/⁩fichier.txt");
		
}
