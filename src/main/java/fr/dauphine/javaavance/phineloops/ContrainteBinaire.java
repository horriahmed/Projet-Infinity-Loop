package fr.dauphine.javaavance.phineloops;
public class ContrainteBinaire implements Contrainte {
	private Piece p1;
	private Piece p2;
	private int sens1; //sens pour p1: 0=Nord;1=Est;2=Sud;3=Ouest
	private int sens2; //sens pour p2: 0=Nord;1=Est;2=Sud;3=Ouest
	
	public ContrainteBinaire(Piece p1, Piece p2, int sens1, int sens2) {
		this.p1=p1;
		this.p2=p2;
		this.sens1=sens1;
		this.sens2=sens2;
	}

	@Override
	public Piece getP1() {
		// TODO Auto-generated method stub
		return p1;
	}

	@Override
	public Piece getP2() {
		// TODO Auto-generated method stub
		return p2;
	}

	@Override
	public int getsens1() {
		// TODO Auto-generated method stub
		return sens1;
	}

	@Override
	public int getsens2() {
		// TODO Auto-generated method stub
		return sens2;
	}
	
	public String affiche() {
		String s="";
		//s+="Contrainte entre "+p1.affiche2()+" et "+p2.affiche2()+" ( "+sens1+","+sens2+" )";
		s+="sens: "+sens1+","+sens2;
		return s;
		
	}
	
	public boolean satisfaite() {
		
		return p1.connecte(sens1) == p2.connecte(sens2);
		
	}
	

}
