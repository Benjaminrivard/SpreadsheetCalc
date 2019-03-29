package src;

public class TestRed extends Test {
	/**
	 * Entry point. Executes all the red tests. 
	 * 
	 * @param args
	 */	
	public static void main(String[] args){	
		boolean verbose = true;
		test(verbose, "test/red1.calc", "one constant function", "2");
		test(verbose, "test/red2.calc", "one constant function, one call", "2");
		test(verbose, "test/red3.calc", "two constant functions and calls", "5");
		test(verbose, "test/red4.calc", "two constant functions and nested calls", "3");
		test(verbose, "test/red5.calc", "function and call with a parameter", "11");
		test(verbose, "test/red6.calc", "call with a composed expression", "12");
		test(verbose, "test/red10.calc", "mutually recursive functions", "1");
		test(verbose, "test/red20.calc", "local variable hide parameter", "error");
		test(verbose, "test/red21.calc", "wrong number of parameters", "error");
		test(verbose, "test/red22.calc", "function called without being defined", "error");
		test(verbose, "test/red23.calc", "function redefinition", "error");
		test(verbose, "test/red30.calc", "fibonacci classic", "1");
		report();
	}
}
