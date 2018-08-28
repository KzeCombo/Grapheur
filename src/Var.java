class Variable implements Fonction {
	@Override
	public double getValue(double x) {
		return x;
	}
	public String toString() {
		return "x";
	}
}

class Constante implements Fonction {
	private double val;

	public Constante(double value) {
		val = value;
	}

	@Override
	public double getValue(double x) {
		return val;
	}

	public String toString() {
		return Double.toString(val);
	}
}
