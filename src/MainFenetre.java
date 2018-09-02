import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainFenetre extends JFrame {

	/**
	 * classe qui  implemente partie graphique
	 */
	private static final long serialVersionUID = 1L;

	JLabel x,y,fx,xmin,xmax,deltax,ymin,ymax,deltay,exp;
	JTextField  tx,ty,tfx,txmin,txmax,tdeltax,tymin,tymax,tdeltay,texp;
	JButton button, buttonOk;
	Canvas mycanvas ;
	Fonction fonc;
    private double[] valeurParametres = { -1.0, +1.0, 0.2, -1.0, +1.0, 0.2 };

	public MainFenetre(){
	setTitle("Tracer des fonctions mathématique :");
	Container container = getContentPane();
	container.setLayout(new BorderLayout());
	
	// 1 - Container du NORTH
	JPanel north = new JPanel();
	north.setLayout(new BorderLayout());
		// 1-1 Grid layout
	JPanel rowNorth = new JPanel();
	rowNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
	
	x = new JLabel("   X =");
	rowNorth.add(x);
	tx = new JTextField(5);
	rowNorth.add(tx);
	
	y = new JLabel("   Y =");
	rowNorth.add(y);
	ty = new JTextField(5);
	rowNorth.add(ty);
	
	fx = new JLabel("   f(x) =");
	rowNorth.add(fx);
	tfx = new JTextField(5);
	rowNorth.add(tfx);
	
	north.add(rowNorth);
	
	// 2 - Container du WEST
	JPanel west = new JPanel();
	west.setLayout(new BorderLayout());
		// 2-1 Grid layout : le tableau
	JPanel row = new JPanel();
	row.setLayout(new GridLayout(6,6));

	xmin = new JLabel("X min");
	row.add(xmin);
	txmin = new JTextField(10);
	row.add(txmin);

	xmax = new JLabel("X max");
	row.add(xmax);
	txmax = new JTextField(10);
	row.add(txmax);

	deltax = new JLabel("delta X");
	row.add(deltax);
	tdeltax = new JTextField(10);
	row.add(tdeltax);

	ymin = new JLabel("Y min");
	row.add(ymin);
	tymin = new JTextField(10);
	row.add(tymin);

	ymax = new JLabel("Y max");
	row.add(ymax);
	tymax = new JTextField(10);
	row.add(tymax);

	deltay = new JLabel("delta Y");
	row.add(deltay);
	tdeltay = new JTextField(10);
	row.add(tdeltay);
		// 2-2- flow layout : le button
	JPanel but = new JPanel();
	but.setLayout(new FlowLayout());

	button = new JButton("Re-Tracer");
	but.add(button);

	west.add(row, BorderLayout.NORTH);
	west.add(but, BorderLayout.CENTER);

	// 3 - Container du CENTER
	mycanvas = new Canvas(this);
	//mycanvas.setPreferredSize(new Dimension(100, 100));

	// 4 - Container du SOUTH
		// 4-1 Grid layout : le tableau
	JPanel sud = new JPanel();
	JPanel rowsud = new JPanel();
	JPanel but2 = new JPanel();
	rowsud.setLayout(new GridLayout(1,0));
	
	but2.setLayout(new FlowLayout());
	buttonOk = new JButton("Tracer");
	but2.add(buttonOk);
	rowsud.add(but2);
	
	exp = new JLabel("Entrer votre fonction f(x) : ");
	rowsud.add(exp);
	texp = new JTextField(10);
	rowsud.add(texp);

	sud.add(rowsud);
	// fin- :
	container.add(north, BorderLayout.NORTH);
	container.add(west,BorderLayout.WEST);
	container.add(mycanvas,BorderLayout.CENTER);
	container.add(sud,BorderLayout.SOUTH);
	
	/*Fin de la logic */
	/*Début Evenements */
	button.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				preparerLaCourbe();

			}catch(Exception excep){
				String msg = "Exception est  "+excep;
				JOptionPane.showMessageDialog(null,msg,"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	});
	
	// listerner pour le deuxième button
	
	buttonOk.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				preparerLaCourbe();

			}catch(Exception excep){
				String msg = "Exception est  "+excep;
				JOptionPane.showMessageDialog(null,msg,"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	});

	/* fin d'évenments*/
	setLocationRelativeTo(this.getParent());
	setDefaultCloseOperation(3);
	}

	private void preparerLaCourbe() throws Exception{
		try{
			valeurParametres[0] = Double.parseDouble(txmin.getText());
			valeurParametres[1] = Double.parseDouble(txmax.getText());
			valeurParametres[3] = Double.parseDouble(tymin.getText());
			valeurParametres[4] = Double.parseDouble(tymax.getText());
			valeurParametres[2] = Double.parseDouble(tdeltax.getText());
			valeurParametres[5] = Double.parseDouble(tdeltay.getText());

	            Analyseur analyseur = new Analyseur(texp.getText());
	            fonc = analyseur.analyser();

		} catch (Exception ex) {
			throw ex;
		}
        mycanvas.repaint();
	}
	public double getXmin() {
        return valeurParametres[0];
    }

    public double getXmax() {
        return valeurParametres[1];
    }

    public double deltaX() {
        return valeurParametres[2];
    }

    public double getYmin() {
        return valeurParametres[3];
    }

    public double getYmax() {
        return valeurParametres[4];
    }

    public double deltaY() {
        return valeurParametres[5];
    }
    public Fonction getFonction() {
        return fonc;
    }
}
