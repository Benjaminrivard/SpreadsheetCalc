package src;

import calc.Calc;

public class TestSyntax {
	static int count = 0;
	static int success = 0;
	
	public static void report(){
		System.out.println(success + " successful tests out of " + count);
	}

	public static void test(String filename, boolean wellFormed){
		String[] args0 = new String[1];
		args0[0] = filename;
		System.out.println("====: " + filename);
		try {		
			count++;
			Calc.main(args0);
			if (wellFormed) success++;
			else System.err.println("FAILURE");
		} catch(Exception e){
			if (!wellFormed) success++;
			else System.err.println("FAILURE");
			e.printStackTrace();
		}		
	}
	public static void main(String[] args){
		test("test/green1.calc", true);
		test("test/green2.calc", true);
		test("test/green3.calc", true);
		test("test/green4.calc", true);
		test("test/green5.calc", true);
		test("test/green6.calc", true);
		test("test/green7.calc", true);
		test("test/green8.calc", true);
		test("test/green9.calc", true);
		test("test/green10.calc", true);
		test("test/green11.calc", true);
		test("test/green12.calc", true);
		test("test/green13.calc", true);
		test("test/green14.calc", true);
		test("test/green15.calc", true);
		test("test/green16.calc", true);
		test("test/green17.calc", true);
		test("test/green18.calc", true);
		test("test/green19.calc", true);
		test("test/green21.calc", false);
		test("test/green22.calc", false);
		test("test/green23.calc", false);
		test("test/green24.calc", false);
		test("test/green25.calc", true);
		test("test/green26.calc", true);
		test("test/green27.calc", false);
		
		test("test/blue1.calc", true);
		test("test/blue2.calc", true);
		test("test/blue3.calc", true);
		test("test/blue4.calc", true);
		test("test/blue5.calc", true);
		test("test/blue6.calc", true);
		test("test/blue7.calc", true);
		test("test/blue8.calc", false);
		
		test("test/red1.calc", true);
		test("test/red2.calc", true);
		test("test/red3.calc", true);
		test("test/red4.calc", true);
		test("test/red5.calc", true);
		test("test/red6.calc", true);
		test("test/red10.calc", true);
		report();
	}
}

