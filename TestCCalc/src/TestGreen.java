package src;

public class TestGreen extends Test {
	/**
	 * Entry point. Executes all the tests. 
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		boolean verbose = true;
		test(verbose, "test/green1.calc", "number", "1234567890");
		test(verbose, "test/green2.calc", "addition", "2");
		test(verbose, "test/green3.calc", "arithmetic expression with priority", "7");
		test(verbose, "test/green4.calc", "unary minus", "-4");
		test(verbose, "test/green5.calc", "boolean", "0");
		test(verbose, "test/green6.calc", "boolean expression", "1");
		test(verbose, "test/green7.calc", "boolean expression with priority", "1");
		test(verbose, "test/green8.calc", "boolean expression with explicit priority", "0");
		test(verbose, "test/green9.calc", "unary not", "1");		
		test(verbose, "test/green10.calc", "conditional", "12");
		test(verbose, "test/green11.calc", "conditional", "21");
		test(verbose, "test/green12.calc", "comparison", "1");
		test(verbose, "test/green13.calc", "nested conditionals", "2");
		test(verbose, "test/green14.calc", "unequality", "1");
		test(verbose, "test/green15.calc", "equality", "0");
		test(verbose, "test/green16.calc", "equality", "error");
		test(verbose, "test/green17.calc", "nested divisisons", "1");
		test(verbose, "test/green18.calc", "minus mix", "2");
		test(verbose, "test/green19.calc", "parenthesized number", "2");
		test(verbose, "test/green20.calc", "erroneous expression", "error");
		test(verbose, "test/green21.calc", "garbage", "error");
		test(verbose, "test/green22.calc", "garbage", "error");
		test(verbose, "test/green23.calc", "erroneous conditional", "error");
		test(verbose, "test/green24.calc", "erroneous conditional/garbage", "error");
		test(verbose, "test/green25.calc", "division by zero", "error");
		test(verbose, "test/green26.calc", "division by zero", "error");
		test(verbose, "test/green27.calc", "erroneous number", "error");
		report();
	}
}
