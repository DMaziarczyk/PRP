package server;

import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class Calc {
	
	public static String calculate (String expression) {
		String result = "";
		JShell jshell = JShell.create();
		try (jshell) { // automatyczne zwalnianie zasobów we frazie try-catch, od JDK9
			List<SnippetEvent> events = jshell.eval(expression);
			for (SnippetEvent e : events) {
				if (e.causeSnippet() == null) {
					switch (e.status()) {
					case VALID:
						if (e.value() != null) {
//							System.out.printf("%s = %s\n", expression, e.value());
							result = e.value();
						}
						break;
					default:
						System.out.printf("Error\n");
						break;
					}
				}
			}
		}
		return result;		
	}
}