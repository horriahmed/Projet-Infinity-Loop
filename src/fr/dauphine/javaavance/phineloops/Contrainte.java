package fr.dauphine.javaavance.phineloops;
public interface Contrainte {
	public Piece getP1();
	public Piece getP2();
	public int getsens1();
	public int getsens2();
	public String affiche();
	public boolean satisfaite();
}
