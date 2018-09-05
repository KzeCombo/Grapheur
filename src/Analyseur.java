import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;


public class Analyseur {
	/*
	public static void main(String[] args) {
		try {
			Analyseur a = new Analyseur(args[0]);
			Fonction f = a.analyser();
			
			System.out.println("f(x) = " + f);
			
			for(int i = 1; i < args.length; i++) {
				double x = Double.parseDouble(args[i]);
				System.out.println("f(" + x + ") = " + f.getValue(x));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	private StreamTokenizer buffer;
	
	public Analyseur(String s){
			buffer = new StreamTokenizer(new StringReader(s));
			buffer.ordinaryChar('/');
			buffer.ordinaryChar('-');
		}
	public Fonction analyser() throws IOException,SyntaxErrorException{
		buffer.nextToken();
		Fonction result = analyserExpression();
		if(buffer.ttype != StreamTokenizer.TT_EOF)
			throw new SyntaxErrorException("Unexpected character at end of text.");
		
		return result;
	}
	
	public Fonction analyserExpression() throws IOException, SyntaxErrorException{
		Fonction result;
		
		boolean sign = true;
		if(buffer.ttype == '-') {
			sign = false;
			buffer.nextToken();
		}
		result = (sign) ? analyserTerme() : new Soustraction(new Constante(0), analyserTerme());
		
		while(buffer.ttype == '+' || buffer.ttype == '-') {
			int symbol = buffer.ttype;
			buffer.nextToken();
			Fonction term = analyserTerme();
			switch(symbol) {
				case '+':
					result = new Addition(result, term);
					break;
				case '-':
					result = new Soustraction(result, term);
					break;
				default:
					throw new SyntaxErrorException("Le buffer '" + buffer.ttype + "' dans l'expression");
			}			
			buffer.nextToken();
		}
		
		return result;
	}
	
	private Fonction analyserTerme() throws IOException, SyntaxErrorException {
		Fonction result = analyserFacteur();
		
		while(buffer.ttype == '*' || buffer.ttype == '/') {
			int symbol = buffer.ttype;
			buffer.nextToken();
			Fonction fact = analyserFacteur();
			switch(symbol)
			{
				case '*':
					result = new Multiplication(result, fact);
					break;
				case '/':
					result = new Division(result, fact);
					break;
				default:
					throw new SyntaxErrorException("Le buffer est inconnue '" + buffer.ttype);
			}
		}
		
		return result;
	}
	
	private Fonction analyserFacteur() throws IOException, SyntaxErrorException{
		Fonction result;
		switch(buffer.ttype) {
			case StreamTokenizer.TT_NUMBER:
				result = new Constante(buffer.nval);
				break;
			case StreamTokenizer.TT_WORD:
				if(buffer.sval.equalsIgnoreCase("x")) {
					result = new Variable();
				} else if (buffer.sval.equalsIgnoreCase("sin")) {
					buffer.nextToken();
					
					Sin sin = new Sin();
					sin.setArgument(analyserExpression());
					result = sin;
				} else if (buffer.sval.equalsIgnoreCase("cos")) {
					buffer.nextToken();
					
					Cos cos = new Cos();
					cos.setArgument(analyserExpression());
					result = cos;
				} else if (buffer.sval.equalsIgnoreCase("log")) {
					buffer.nextToken();
					
					Log log = new Log();
					log.setArgument(analyserExpression());
					result = log;
				} else if (buffer.sval.equalsIgnoreCase("exp")) {
					buffer.nextToken();
					
					Exp exp = new Exp();
					exp.setArgument(analyserExpression());
					result = exp;
				} else {
					throw new SyntaxErrorException("Variable inconnue '" + buffer.sval + "'");
				}
				break;
			case '(':
				buffer.nextToken();
				result = analyserExpression();
				buffer.nextToken();
				break;
			case ')':
				throw new SyntaxErrorException("Unmatched closing parenthesis.");
			default:
				throw new SyntaxErrorException("Buffer indefinie '" + buffer.ttype + "'dans facteur");
		}
		
		buffer.nextToken();
		return result;
	}
	
	private void debug() {
		System.out.println("ttype: " + buffer.ttype + "(" + (char)buffer.ttype + ")");
		System.out.println("nval: " + buffer.nval);
		System.out.println("sval: " + buffer.sval);
	}
	
	
	class SyntaxErrorException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SyntaxErrorException(String message) {
			super(message);
			debug();
		}
	}
}
