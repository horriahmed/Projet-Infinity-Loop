package fr.dauphine.javaavance.phineloops; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.impl.BoolVarImpl;

public class Piece {
	//les Attributs
	private int numero_piece ;
	private int orientation;
	private ArrayList<Integer> connexions;//0=Nord;1=Est;2=Sud;3=Ouest
	private int nombre_connection;
	private int nb_connexions;//nbr connexions de la piÃ¨ce
	private int type;//nbr max d'orientations - 1 (indice max d'orientation)
	private BoolVar [] tab;
	
	
	//les Methodes 
	public Piece() {
		this.numero_piece=0;
		this.orientation=0;
		this.nombre_connection=0;
		this.type=0;
		this.connexions=new ArrayList<>();
		this.nb_connexions=0;
	
		
		
		
	}
	
	public Piece(int num, int orientation ) {//0=Nord;1=Est;2=Sud;3=Ouest
		
		this.numero_piece=num;
		this.orientation=orientation;
		this.nombre_connection=0;
		connexions=new ArrayList<>();
		switch(this.numero_piece) {
			case 0:
				this.type=0;
				break;
			case 1:
				this.type=3;
				connexions.add(this.orientation);
				break;
			case 2:
				this.type=1;
				switch(this.orientation) {
					case 0:
						connexions.add(0);
						connexions.add(2);
						break;
					case 1:
						connexions.add(1);
						connexions.add(3);
						break;
				}
				break;
			case 3:
				this.type=3;
				switch(this.orientation) {
					case 0:
						connexions.add(0);
						connexions.add(1);
						connexions.add(3);
						break;
					case 1:
						connexions.add(0);
						connexions.add(2);
						connexions.add(1);
						break;
					case 2:
						connexions.add(1);
						connexions.add(2);
						connexions.add(3);
						break;
					case 3:
						connexions.add(0);
						connexions.add(2);
						connexions.add(3);
						break;
				}
				break;
			case 4:
				this.type=0;
				connexions.add(0);
				connexions.add(1);
				connexions.add(2);
				connexions.add(3);
				break;
			default : //0=Nord;1=Est;2=Sud;3=Ouest
				this.type=3;
				switch(this.orientation) {
					case 0:
						connexions.add(0);
						connexions.add(1);
						break;
					case 1:
						connexions.add(1);
						connexions.add(2);
						break;
					case 2:
						connexions.add(2);
						connexions.add(3);
						break;
					case 3:
						connexions.add(0);
						connexions.add(3);
						break;
				}
				
				break;
		}
		this.nb_connexions=connexions.size();
		
	}
	
	public void connexion(int sens) {
		if(!connexions.contains(sens)) 
		{
			nombre_connection++;
			nb_connexions++;
			connexions.add(sens);
			System.out.println("tab connexions: "+this.affiche());
		}
	}
	
	public void update_numpiece() {
		if(nb_connexions!=2) {
			this.numero_piece=nb_connexions;
		}
		else {
			if(((connexions.contains(0)) && (connexions.contains(2)))||((connexions.contains(1)) && (connexions.contains(3)))) this.numero_piece=2;
			else this.numero_piece=5;
		}

		if(this.numero_piece==1) this.type=3;
		if(this.numero_piece==2) this.type=1;
		if(this.numero_piece==3) this.type=3;
		if(this.numero_piece==5) this.type=3;
		
		//System.out.println("num piece: "+this.numero_piece);
		//System.out.println("num type: "+this.type);
	}
	
	public void update_connexions() {//mise Ã  jour tab connexions en fonction orientation choisie !!!!!!!!
		//int n =connexions.size();
		connexions.clear();
		switch(this.numero_piece) {
		case 0:
			this.type=0;
			break;
		case 1:
			this.type=3;
			connexions.add(this.orientation);
			break;
		case 2:
			this.type=1;
			switch(this.orientation) {
				case 0:
					connexions.add(0);
					connexions.add(2);
					break;
				case 1:
					connexions.add(1);
					connexions.add(3);
					break;
			}
			break;
		case 3:
			this.type=3;
			switch(this.orientation) {
				case 0:
					connexions.add(0);
					connexions.add(1);
					connexions.add(3);
					break;
				case 1:
					connexions.add(0);
					connexions.add(2);
					connexions.add(1);
					break;
				case 2:
					connexions.add(1);
					connexions.add(2);
					connexions.add(3);
					break;
				case 3:
					connexions.add(0);
					connexions.add(2);
					connexions.add(3);
					break;
			}
			break;
		case 4:
			this.type=0;
			connexions.add(0);
			connexions.add(1);
			connexions.add(2);
			connexions.add(3);
			break;
		default : //0=Nord;1=Est;2=Sud;3=Ouest
			this.type=3;
			switch(this.orientation) {
				case 0:
					connexions.add(0);
					connexions.add(1);
					break;
				case 1:
					connexions.add(1);
					connexions.add(2);
					break;
				case 2:
					connexions.add(2);
					connexions.add(3);
					break;
				case 3:
					connexions.add(0);
					connexions.add(3);
					break;
			}
			
			break;
	}
	this.nb_connexions=connexions.size();
	//this.nb_connexions=n;
		
	}
	
	public ArrayList<Integer> getConnexion(){
		return connexions;
	}
	
	public String affiche() {
		String s="";
		for(int i: connexions) {
			s+=i+" ";
		}
		return s; 
	}
	
	public String affiche2() {
		String s="";
		s+=this.numero_piece+" "+this.orientation;
		return s;
	}
	
	public void Rotation() {
		this.orientation=(this.orientation+1)%type;
	}
	
	public void AjouterConnection() {
		this.nombre_connection++;
	}
	
	public void SupprimerConnection() {
		this.nombre_connection--;
	}
	
	public int getNombre_connnection() {
		return nombre_connection;
	}
	
	public int getNumero_piece() {
		return numero_piece;
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	
	
	public int getType_orientation() {
		return type;
	}
	
	
	
	public 	String toString() {
		switch(this.numero_piece) {
		
		case 1: 
			switch(this.orientation) {
			case 0: return "img/10.png"; 
			case 1: return "img/11.png";
			case 2: return "img/12.png";
			case 3: return "img/13.png";
				}
	case 2: 
			if(this.orientation==0)
				return "img/20.png";
			else 
				return "img/21.png";
			
	case 3:
		     switch (this.orientation) {
		        case 0: return "img/30.png";
				case 1: return "img/31.png";
				case 2: return "img/32.png";
				case 3: return "img/33.png";
		     }
	case 4: 
			return "img/4.png";
			
	case 5: 
			switch(this.orientation) {
				case 0: return "img/50.png";
				case 1: return "img/51.png";
				case 2: return "img/52.png";
				case 3: return "img/53.png"; 
			}
				
	}
	return "img/0.png"; // dans le cas ou numer_piece ==0
	
	}
		
		
//		case 1: 
//				
//				switch(this.orientation) {
//				case 0: 
//					System.out.println("piece 1 orientation 0");
//					return "\u2579"; 
//				case 1:
//					System.out.println("piece 1 orientation 1");
//					return "\u257A";
//				case 2:
//					System.out.println("piece 1 orientation 2");
//					return "\u257B";
//				case 3: 
//					System.out.println("piece 1 orientation 3");
//					return "\u2578";
//				}
//		case 2: 
//			System.out.println("piece 2");
//				if(this.orientation==0)
//					return "\u2503";
//				else 
//					return "\u2501";
//				
//		case 3:
//			     switch (this.orientation) {
//			        case 0:
//			        	System.out.println("piece 3 orientation 0");
//			        	return "\u253B";
//					case 1: 
//						System.out.println("piece 3 orientation 1");
//						return "\u2523";
//					case 2: 
//						System.out.println("piece 3 orientation 2");
//						return "\u2533";
//					case 3: 
//						System.out.println("piece 3 orientation 3");
//						return "\u252B";
//			    }
//		case 4: 
//			System.out.println("piece 4");
//				return "\u254B";
//				
//		case 5:
//				System.out.println("heyyyy piece 5");
//				switch(this.orientation) {
//					case 0: 
//						System.out.println("piece 5 orientation 0");
//						return "\u2517";
//					case 1: 
//						System.out.println("piece 5 orientation 1");
//						return "\u250F";
//					case 2: 
//						System.out.println("piece 5 orientation 2");
//						return "\u2513";
//					case 3: 
//						System.out.println("piece 5 orientation 3");
//						return "\u251B"; 
//				}
//					
//		}
//		return " "; // dans le cas ou numer_piece ==0
//		
		
	
	// pour generer des orientation aleatoir 
	
	public void RandomOrientation() {
		Random R = new Random();
		switch (this.numero_piece) {
			case 0: orientation = 0; break;
			case 1: this.orientation = R.nextInt(4); break;// entre 0 et 3
			case 2: this.orientation= R.nextInt(2); break; // 0 ou 1
			case 3: this.orientation = R.nextInt(4); break;	// entre 0 et 3
			case 4: this.orientation = 0;  break;	
			case 5: this.orientation = R.nextInt(4); break;	// entre 0 et 3
	    }
		//this.update_connexions();
    }
	
	public void RandomNumPiece() {
		Random R = new Random();
		this.numero_piece=R.nextInt(6); // entre 0 et 5
	}

	public void randomOrientation() {
		// TODO Auto-generated method stub
		int r=(int)(Math.random()*(type+1));
		this.orientation=r;
		//this.update_connexions();
	}
	
	public boolean connecte(int sens) {
		//System.out.println("sens voulu pour la piece "+this.affiche2()+" : "+sens+" : "+connexions.contains(sens));
		return connexions.contains(sens);
	};
	
	public ArrayList<Integer> orientationsPossibles(){
		ArrayList<Integer> list=new ArrayList<>();
		if((numero_piece==0)||(numero_piece==4)) list.add(0);
		if((numero_piece==1)||(numero_piece==3)||(numero_piece==5)) {
			list.add(0);
			list.add(1);
			list.add(2);
			list.add(3);
		}
		if(numero_piece==2) {
			list.add(0);
			list.add(1);
		}
		return list;
		
	}
	
	public void getVar(Model m) {
		tab=new BoolVar[4];// liste de boolean
		for(int i=0;i<tab.length;i++) {
			tab[i]=new BoolVarImpl("b",m);
		}
		switch (this.numero_piece) {
			case 0:
				m.sum(tab,"=",0).post();
				break;
			case 1:
				m.sum(tab,"=",1).post();
				break;
			case 2:
				m.sum(tab,"=",2);
				m.arithm(tab[0],"!=", tab[1]);
				m.arithm(tab[1],"!=", tab[2]);
				break;
			case 3:
				m.sum(tab,"=",3).post();
				break;
			case 4:
				m.sum(tab,"=",4).post();
				break;
				
			case 5:
				System.out.println(m);
				System.out.println(tab);
				m.sum(tab,"=",2).post();
				m.or(m.arithm(tab[0],"=", tab[1]),m.arithm(tab[1],"=", tab[2])).post();
				break;
		
		}

	}
	
	public BoolVar[] getTab() {
		return tab;
	}
	
	public void update() {
		if(this.numero_piece==0) this.orientation=0;
		if(this.numero_piece==1) {
			if(tab[0].isInstantiated() && tab[0].getValue()==1) this.orientation=0;
			if(tab[1].isInstantiated() && tab[1].getValue()==1) this.orientation=1;
			if(tab[2].isInstantiated() && tab[2].getValue()==1) this.orientation=2;
			if(tab[3].isInstantiated() && tab[3].getValue()==1) this.orientation=3;
		}
		if(this.numero_piece==2) {
			if(tab[0].isInstantiated() && tab[0].getValue()==1 && tab[2].isInstantiated() && tab[2].getValue()==1) this.orientation=0;
			if(tab[1].isInstantiated() && tab[1].getValue()==1 && tab[3].isInstantiated() && tab[3].getValue()==1) this.orientation=1;
		}
		if(this.numero_piece==3) {
			if(tab[2].isInstantiated() && tab[2].getValue()==0) this.orientation=0;
			if(tab[3].isInstantiated() && tab[3].getValue()==0) this.orientation=1;
			if(tab[0].isInstantiated() && tab[0].getValue()==0) this.orientation=2;
			if(tab[1].isInstantiated() && tab[1].getValue()==0) this.orientation=3;
		}
		if(this.numero_piece==4) this.orientation=0;
		if(this.numero_piece==5) {
			if(tab[0].isInstantiated() && tab[0].getValue()==1 && tab[2].isInstantiated() && tab[1].getValue()==1) this.orientation=0;
			if(tab[1].isInstantiated() && tab[1].getValue()==1 && tab[2].isInstantiated() && tab[2].getValue()==1) this.orientation=1;
			if(tab[2].isInstantiated() && tab[2].getValue()==1 && tab[3].isInstantiated() && tab[3].getValue()==1) this.orientation=2;
			if(tab[0].isInstantiated() && tab[0].getValue()==1 && tab[3].isInstantiated() && tab[3].getValue()==1) this.orientation=3;
		}
		
	}
	
	public String affichetab() {
		String s=" [ ";
		for(int i=0;i<tab.length;i++) {
			s+=tab[i]+",";
		}
		s+="]";
		return s;
	}
	
	private int turnOrientation() {
		switch(this.numero_piece) {

		case 1: 
			return (this.orientation+1)%4;
		case 2: 
			return (this.orientation+1)%2;

		case 3:
			return (this.orientation+1)%4;
		case 4: 
			return this.orientation;

		case 5: 
			return (this.orientation+1)%4;

		}
		return 0;	
}

public Piece turn(){
	this.orientation = turnOrientation();
	return new Piece(this.numero_piece, this.orientation);
}
			
	
}// Piece