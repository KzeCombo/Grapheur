import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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

	JLabel xmin,xmax,deltax,ymin,ymax,deltay,exp;
	JTextField  txmin,txmax,tdeltax,tymin,tymax,tdeltay,texp;
	JButton button;
	Canvas mycanvas ;
	Fonction fonc;
    private double[] valeurParametres = { -1.0, +1.0, 0.2, -1.0, +1.0, 0.2 };

	public MainFenetre(){
	setTitle("SMI6 : Tracer des fonctions mathématique");
	setSize(600, 400);
	/* La logique du graphic est ici : composÃ© de trois parties : */
	Container container = getContentPane();
	container.setLayout(new BorderLayout());

	// 1 - Container du West
	JPanel west = new JPanel();
	west.setLayout(new BorderLayout());
		// 1-1 Grid layout : le tableau
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
		// 1-2- flow layout : le button
	JPanel but = new JPanel();
	but.setLayout(new FlowLayout());

	button = new JButton("Tracer");
	but.add(button);

	west.add(row, BorderLayout.NORTH);
	west.add(but, BorderLayout.CENTER);

	// 2 - Container du CENTER
	mycanvas = new Canvas(this);
	//mycanvas.setPreferredSize(new Dimension(100, 100));

	// 3 - Container du SOUTH
		// 3-1 Grid layout : le tableau
	JPanel sud = new JPanel();
	JPanel rowsud = new JPanel();
	rowsud.setLayout(new GridLayout(2,0));

	exp = new JLabel("Entrer votre fonction f(x) à tracer par ici : ");
	rowsud.add(exp);
	texp = new JTextField(10);
	rowsud.add(texp);

	sud.add(rowsud);
	// fin- :
	container.add(west,BorderLayout.WEST);
	container.add(mycanvas,BorderLayout.CENTER);
	container.add(sud,BorderLayout.SOUTH);
	/*Fin de la logic */
	/*DÃ©but Evenements */
	button.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
				preparerLaCourbe();

			}catch(Exception excep){
				String msg = "Exception est  "+excep;
				JOptionPane.showMessageDialog(null,msg,"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	});

	/* fin d'Ã©venments*/
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
