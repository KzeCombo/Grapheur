


class Addition extends FonctionBinaire {

	public Addition(Fonction g, Fonction d) {
      super(g, d);
   }

	public String toString() {
		return filsGauche + " + " + filsDroite;
	}

	@Override
	public double getValue(double x) {
		// TODO Auto-generated method stub
	      return filsGauche.getValue(x) + filsDroite.getValue(x);
	}
}

class Soustraction extends FonctionBinaire{

    public  Soustraction(Fonction g, Fonction d) {
        super(g, d);
    }

    public String toString() {
    	return "(" + filsGauche + " - " + filsDroite + ")";
    }

	@Override
	public double getValue(double x) {
		// TODO Auto-generated method stub
        return filsGauche.getValue(x) - filsDroite.getValue(x);
	}
}

class Division extends FonctionBinaire{

	public Division(Fonction g , Fonction d) {
		// TODO Auto-generated constructor stub
		super(g, d);
	}
	@Override
	public double getValue(double x) {
		// TODO Auto-generated method stub
		if(filsDroite.getValue(x) != 0)
			return filsGauche.getValue(x) / filsDroite.getValue(x);
		return 0;
	}
	public String toString(){
			return "(" + filsGauche + " / " + filsDroite + ")";
	}
}

class Multiplication extends FonctionBinaire{

	public Multiplication(Fonction g,Fonction d) {
		// TODO Auto-generated constructor stub
		super(g, d);
	}

	@Override
	public double getValue(double x) {
		// TODO Auto-generated method stub
		return filsGauche.getValue(x) * filsDroite.getValue(x);
	}
	public String toString(){
		return "(" + filsGauche + " * " + filsDroite + ")";
}

}
