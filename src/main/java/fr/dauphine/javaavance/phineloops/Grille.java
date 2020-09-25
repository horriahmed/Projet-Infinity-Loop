package fr.dauphine.javaavance.phineloops;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Grille {
	private int hauteur;
	private int largeur;
	        Piece grid[][];
	private boolean solver;
	private ArrayList<Contrainte> list_contraintes;//0=Nord;1=Est;2=Sud;3=Ouest
 
	
	public Grille() {
		this.hauteur=0;
		this.largeur=0;
		grid =new Piece[this.hauteur][this.largeur];
		this.solver=false;
		list_contraintes=new ArrayList<>();
	}
	
	public Grille(int hauteur, int largeur) {
		this.hauteur=hauteur;
		this.largeur=largeur;
		grid =new Piece[this.hauteur][this.largeur];
		this.solver=false;
		list_contraintes=new ArrayList<>();
	}
	
	public void Write(File file) {
		PrintWriter write = null;
		 try {
			 write =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
			write.println(this.hauteur);
			write.println(this.largeur);
			
			
			for(int i=0;i<this.hauteur;i++) {
				for(int j=0;j<this.largeur;j++) {
					write.println(grid[i][j].getNumero_piece()+"\t"+grid[i][j].getOrientation());
				}
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("fichier non trouvé");
		}finally {
			write.close();
		}
	}
	
	public void Read(String nomFichier) {
		Scanner read = null ;
		LinkedList<Piece> listPiece=new LinkedList<Piece>();
		
		File fichier = new File(nomFichier);
		try {
			read = new Scanner(fichier);
			
			this.hauteur=Integer.parseInt(read.nextLine());
			this.largeur=Integer.parseInt(read.nextLine());
			//System.out.println(this.hauteur+"\t"+this.largeur);
			
			while(read.hasNextLine()) {
			String[] ligne = read.nextLine().split("\\s+");
			listPiece.add(new Piece(Integer.parseInt(ligne[0]),Integer.parseInt(ligne[1])));
			
			//System.out.println(Integer.parseInt(ligne[0])+"\t"+Integer.parseInt(ligne[1]));
			}
			
			System.out.println(listPiece);
			/*for(Piece p:listPiece) {
				System.out.println(p.affiche2());
			}*/
			
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("fichier non trouvé");
		}finally {
			read.close();
		}
		
		// creation d'une grille 
		
		this.grid= new Piece[this.hauteur][this.largeur];
		
		int k=0;
		for(int i=0;i<this.hauteur;i++) {
			for(int j=0;j<this.largeur;j++) {
				this.grid[i][j]=listPiece.get(k);
				k++;
			}
		}
		
		for(int i=1;i<(grid.length);i++) {//sauf première ligne
			for(int j=1;j<grid[i].length;j++) {//sauf 1ere colonne 
				this.addContrainte(new ContrainteBinaire(grid[i-1][j],grid[i][j],2,0));//contrainte haut et bas (SD)
				this.addContrainte(new ContrainteBinaire(grid[i][j-1],grid[i][j],1,3));//contrainte cote à cote (EO)
			}
			this.addContrainte(new ContrainteBinaire(grid[i-1][0],grid[i][0],2,0));//contrainte haut et bas (SD) pour la 1ère colonne 
		}
		for(int j=1;j<grid[0].length;j++) {
			this.addContrainte(new ContrainteBinaire(grid[0][j-1],grid[0][j],1,3));//contrainte cote à cote (EO) pour la première ligne 
		}
		for(int i=0;i<list_contraintes.size();i++) {
			Piece p1=list_contraintes.get(i).getP1();
			Piece p2=list_contraintes.get(i).getP2();
			//int j=i+1;
			//System.out.println("Contrainte "+j+" entre piece "+"("+this.getAbs(p1)+","+this.getOrd(p1)+") "+p1.affiche2()+" et piece "+"("+ this.getAbs(p2)+","+this.getOrd(p2)+") "+p2.affiche2()+" :"+list_contraintes.get(i).affiche());
			
		}
		
	}

	
	
	
	
	public Piece[][] getGrid() {
		return grid;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	public int getLargeur() {
		return largeur;
	}
	
	public boolean isSolver() {
		return solver;
	}
	
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	
	public String toString() {
		String s="";
		s+=this.hauteur+"\n"+this.largeur+"\n\n";
		
		for(int i=0;i<this.hauteur;i++) {
			for(int j=0;j<this.largeur;j++) {
				s+=this.grid[i][j].toString();
				
			}
			s+="\n";
		}
		
		return s;
	}
	
	public int getAbs(Piece p) {
		int abs=-1;
		for(int i=0;i<this.hauteur;i++) {
			for(int j=0;j<this.largeur;j++) {
				if((this.grid[i][j])==p) abs=i;
			}
		}
		return abs;	
	}
	
	public int getOrd(Piece p) {
		int ord=-1;
		for(int i=0;i<this.hauteur;i++) {
			for(int j=0;j<this.largeur;j++) {
				if((this.grid[i][j])==p) ord=j;
			}
		}
		return ord;	
	}
	
	public ArrayList<Contrainte> getContraintes(){
		return list_contraintes;
	}
	
	public void addContrainte(Contrainte c) {
		list_contraintes.add(c);
	}
//	public static void main(String[] args) {
//		
//		Grille grille = new Grille(3, 3);
//		
//		for(int i=0;i<grille.hauteur;i++) {
//			for(int j=0;j<grille.largeur;j++) {
//				Piece p =new Piece();
//				p.RandomNumPiece();
//				p.RandomOrientation();
//				
//				grille.grid[i][j]=p;
//			}
//		}
//		
//		File fichierTxt =new File("gille1.txt");
//		
// 	grille.Write(fichierTxt);
//		
//		Grille grille = new Grille(3, 3);
//		
//		grille.Read("gille1.txt");
//		System.out.println(grille);
//		
//		
//	}
	
	public void setGrid(int x, int y, Piece p){
		grid[x][y] = p;
	}
	

}

