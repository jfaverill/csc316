public class SwapDemo {
	public static String Swap(String toSwap) {
		if (toSwap.length() / 2 >= 2) {
			return "" + toSwap.charAt(1) + toSwap.charAt(0) + Swap(toSwap.substring(2));
		} else if (((float) toSwap.length()) / 2 == 1.5) {
			return "" + toSwap.charAt(1) + toSwap.charAt(0) + toSwap.charAt(2);
		} else if (((float) toSwap.length()) / 2 == 1) {
			return "" + toSwap.charAt(1) + toSwap.charAt(0);
		} else {
			return "" + toSwap.charAt(0);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Swapping a\tExpected: a\tActual: " + Swap("a"));
		System.out.println("Swapping ab\tExpected: ba\tActual: " + Swap("ab"));
		System.out.println("Swapping abc\tExpected: bac\tActual: " + Swap("abc"));
		System.out.println("Swapping abcd\tExpected: badc\tActual: " + Swap("abcd"));
		System.out.println("Swapping abcde\tExpected: badce\tActual: " + Swap("abcde"));
		System.out.println("Swapping abcdef\tExpected: badcfe\tActual: " + Swap("abcdef"));
		System.out.println("Swapping abcdefg\tExpected: badcfeg\tActual: " + Swap("abcdefg"));
		System.out.println("Swapping abcdefgh\tExpected: badcfehg\tActual: " + Swap("abcdefgh"));
		System.out.println("Swapping abcdefghi\tExpected: badcfehgi\tActual: " + Swap("abcdefghi"));
		System.out.println("Swapping abcdefghij\tExpected: badcfehgji\tActual: " + Swap("abcdefghij"));
		System.out.println("Swapping abcdefghijk\tExpected: badcfehgjik\tActual: " + Swap("abcdefghijk"));
		System.out.println("Swapping abcdefghijkl\tExpected: badcfehgjilk\tActual: " + Swap("abcdefghijkl"));
		System.out.println(Swap("Swapthis"));
		System.out.println(Swap("Swapethis"));
		System.out.println(Swap("Swapaeiou"));
		System.out.println(Swap("Iwantthisentirestringtobereversed.Canitbedone??"));
		System.out.println(Swap("Iwantthisentirestringtobereversed.Canitbedone?-?"));
	}
}