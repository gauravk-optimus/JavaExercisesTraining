
public class ReplaceString {
	public static void main(String[] args) {
		String str = "I use selenium webdriver. selenium is a tool for web applications automation.";
		String toBeReplaced = "selenium";
		String toReplacedWith = "Firefox";
		String[] astr = str.split(toBeReplaced);
		StringBuffer strb = new StringBuffer();
		for ( int i = 0; i <= astr.length - 1; i++ ) {
		strb = strb.append( astr[i] );
		if (i != astr.length - 1) {
		strb = strb.append(toReplacedWith);
		}
		}
		System.out.println(strb);
	}
}
