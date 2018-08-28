

class Sin extends FonctionUnaire {
		public double getValue(double x) {
			return Math.sin(argument.getValue(x));
		}
		public String toString() {
			return "sin(" + argument + ")";
		}
}


class Cos extends FonctionUnaire {
		public double getValue(double x) {
			return Math.cos(argument.getValue(x));
		}
		public String toString() {
			return "cos(" + argument + ")";
		}
}

class Log extends FonctionUnaire {
		public double getValue(double x) {
			return Math.log(argument.getValue(x));
		}
		public String toString() {
			return "log(" + argument + ")";
		}
}

class Exp extends FonctionUnaire {
		public double getValue(double x) {
			return Math.exp(argument.getValue(x));
		}
		public String toString() {
			return "exp(" + argument + ")";
		}
}