package fr.dauphine.javaavance.phineloops; 

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Gui {
	private JFrame frame = new JFrame("Infinity Loop");
	private Grille grid = new Grille();
	private int dim;
	private JButton solver;
	private JButton generate;

	
	
	private JComponent component = new JComponent() {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			BufferedImage img = null;
			int gH = grid.getHauteur();
			int gW = grid.getLargeur();

			for(int j=0; j<gW; j++) {
				for(int i=0; i<gH; i++) {
					try {
					    img = ImageIO.read(new File(grid.getGrid()[i][j].toString()));
					    g.drawImage(img, dim*j, dim*i, dim, dim, this);
					} catch (IOException e) {
					}

				}

			}
		}
		
	};
	
	public JComponent getComponent() {
		return component;
	}
	 //TODO


	Gui(final String inputFile){
		

		
		this.grid.Read(inputFile);
		dim = 1000/ Math.max(grid.getHauteur(), grid.getLargeur());
		frame.setContentPane(component);

		frame.setSize(1400,1000);
		frame.setLocation(0, 0);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);
		solver = new JButton("Solve");
		solver.setLayout(null);
		frame.add(solver);
		solver.setBounds(1050, 100, 300, 100);
		solver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Solver s = new Solver();
				s.solve(inputFile, "outputFile.txt");
				frame.dispose();
				new Gui("outputFile.txt");
				
			}
			
		});
		generate = new JButton("Generate");
		generate.setLayout(null);
		frame.add(generate);
		generate.setBounds(1050, 300, 300, 100);
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Generateur g = new Generateur();
				g.generate(6, 6, inputFile);
				frame.dispose();
				new Gui(inputFile);
				
			}
			
		});
		frame.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();
				int caseX = y/(dim);
				int caseY = x/(dim);
				System.out.println("x= "+ x + "y = "+ x );
				System.out.println("Cx= "+ caseX + "Cy = "+ caseY );

				Piece p =grid.getGrid()[caseX][caseY];
				p.turn();
				grid.setGrid(caseX,caseY,p);
				frame.repaint();
//				
			}
			});
		

		
	}
	
	


	




}