
public interface Fonction {
	double getValue(double x);
}


abstract class FonctionBinaire implements Fonction {
	
	protected Fonction filsGauche, filsDroite;
	public FonctionBinaire(Fonction g, Fonction d) {
			filsGauche = g;
			filsDroite = d;
	}
}

abstract class FonctionUnaire implements Fonction {
	protected Fonction argument;
	
	void FonctionUnaire (Fonction a){
		argument = a;
	}
	void setArgument(Fonction a) {
        argument = a;
    }
}

