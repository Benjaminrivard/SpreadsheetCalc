package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import calc.Calc;

/** 
 * @author Jacques Noye
 * @version 1.2
 * @since 	2016-01-27
 */
public abstract class Test {
	static int count = 0;
	static int success = 0;
	
	public static void report(){
		System.out.println(success + " successful tests out of " + count);
	}
	// assumes the path environment variable has been updated so that bash (and gcc) is in the path	
	static final String SHELL = "bash";
	
	/**
	 * Executes a test.
	 * 
	 * @param verbose	Verbose mode. Assumes compiler also has a verbose mode (option "-v" as
	 * 					second argument). 
	 * @param fileName  Name of file to compile (relative to project root).
	 * @param test		Description of the test.
	 * @param expectation	Expected result (as a string).
	 */
	public static void test(boolean verbose, String fileName, String test, String expectation){
		count++;
		String[] args0;
		if (verbose) {
			args0 = new String[2];
			args0[1] = "-v";
		} else 
		args0 = new String[1];
		args0[0] = fileName;
		if (verbose) args0[1] = "-v";
		System.out.println("==== " + fileName + ": " + test + ", attendu : " + expectation);
		try {
			long time0 = 0;
			long time1 = 0;
			String root = fileName.replaceFirst("\\.calc\\z", "");
			String cFileName = root + ".c";
			File cFile = new File(cFileName);
			if (cFile.exists()) 
			    time0 = cFile.lastModified();
			try {
			    Calc.main(args0);
			} catch(Exception e){
			    if (expectation.equals("error")) success++;
			    System.err.println("==== Exception in compiler");
			    e.printStackTrace();
			    return;
			}			
			if (cFile.exists()) // it should
			    time1 = cFile.lastModified();
			if (time1 > time0) { // some C code has been produced by the compiler 
			    // compile file
			    String outFileName = compile(cFileName);
			    File outFile = new File(outFileName);
			    if (outFile.exists()) {
				long time2 = outFile.lastModified();
				if (time2 >= time1) {
				    execute(outFileName);
				    String result = display(root + ".txt");
				    if (result.equals(expectation)) {
					System.out.println("SUCCESS");
					success++;
				    } else System.err.println("FAILURE with " + result);
				} else 
				    System.err.println("C code does not compile");
			    } else 
				System.err.println("C code does not compile");
			} else { // no C code produced
			    System.err.println("No C code produced for " + fileName);
			    if (expectation.equals("error")) success++;
		     }
		} catch(Exception e){
		    System.err.println("==== Unexpected exception");
		    e.printStackTrace();
		}
	}
			
	/**
	 * Compiles input file.
	 * 
	 * @param cFileName Name of input .c file. 
	 * @return Name of output executable (.out) file.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	static String compile(String cFileName) throws IOException, InterruptedException {
//		String[] cmd = new String[3];
//		cmd[0] = "/bin/sh";
//		cmd[1] = "-c";
//		cmd[2] = "/usr/bin/gcc " + CFilename;
		String outputFileName = cFileName.replaceFirst("\\.c\\z", ".out");
		String[] cmd = {SHELL, "-c", "gcc -o " + outputFileName + " " + cFileName};
		Runtime.getRuntime().exec(cmd).waitFor();
		return outputFileName;
	}

	/** 
	 * Executes input file and logs result in .txt file.
	 * 
	 * @param fileName		Input executable (.out) file.	
	 * @throws IOException
	 * @throws InterruptedException
	 */
	static void execute(String fileName) throws IOException, InterruptedException {
		String txtFileName = fileName.replaceFirst("\\.out\\z", ".txt");
		String[] cmd = {SHELL, "-c", "./" + fileName + ">" + txtFileName};
		Runtime.getRuntime().exec(cmd).waitFor();
	}

	/**
	 * Reads result from .txt file. 
	 * 
	 * Assumes one-line result.
	 * 
	 * @param txtFileName Input .txt file.
	 * @return Contents of .txt as a string (first line only).			
	 * @throws FileNotFoundException
	 */
	static String display(String txtFileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(txtFileName));
		String result = scan.nextLine(); // assumes the result is on one line
		scan.close();
		new File("result").delete();
        return result;
	}
    
	/**
	 * For testing purposes.
	 * 
	 * @param shellCmd
	 * @throws IOException
	 * @throws InterruptedException
	 */
	static void test(String shellCmd) throws IOException, InterruptedException {
		String[] cmd = {SHELL, "-c", shellCmd};
		Runtime.getRuntime().exec(cmd).waitFor();
	}
}
