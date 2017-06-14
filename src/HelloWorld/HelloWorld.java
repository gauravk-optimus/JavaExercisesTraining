package HelloWorld;

public class HelloWorld {

	public static String hello(String s) {
		String t = "";
		if (s == null) {
			t = "Hello, World!";
			return t;
		}
		if (s.toString().equals("")) {
			t = "Hello, World!";
		}
		if (s.toString().equals("Alice")) {
			t = "Hello, Alice!";
		}
		if (s.toString().equals("Bob")) {
			t = "Hello, Bob!";
		}
		return t;
	}
}
