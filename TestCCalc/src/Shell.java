package src;

import java.io.IOException;

public class Shell {
	// static final String SHELL = "powershell";
	static final String SHELL = "bash";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO: avec powershell, waitFor() ne semble pas rendre lce contrôle
		// il ne faut donc exécuter qu'un test à la fois et arrêter l'application à la main
		// à regarder de plus près
		test("pwd > test.txt");
		test("gcc test/test.c");
	}
	
	static void test(String shellCmd) throws IOException, InterruptedException {
	  String[] cmd = {SHELL, "-c", shellCmd};
	  Runtime.getRuntime().exec(cmd).waitFor();
	}
}
