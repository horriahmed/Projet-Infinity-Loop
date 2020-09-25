package fr.dauphine.javaavance.phineloops;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;



public class Test {
	public static void main(String[] args) {
		
		Generateur ge = new Generateur();
		ge.generate(4, 4, "input.txt");
		
		Solver s=new Solver();
		s.solve("input.txt","outputFile.txt");	
		
          //Generateur g=new Generateur();
		  //g.generate(4,4,"fichier4.txt");
//		Grille gr = new Grille();
//		gr.Read("output.txt");
//		Gui G = new Gui(gr);
		new Gui("input.txt");
	}
		
}
