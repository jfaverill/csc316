public class SwapDemo {
	public static String Swap(String toSwap) {
		// If toSwap is even-length string, then reduce length by 1
		// otherwise reduce length by 2
		String lastCharOddLength = "";
		int end = toSwap.length() - 1;
		if (toSwap.length() % 2 == 1) {
			end = toSwap.length() - 2;
			lastCharOddLength = "" + toSwap.charAt(toSwap.length() - 1);
		}

		if (end - 1 < 0) {
			return "";
		} else {
			// String swapped = "" + toSwap.charAt(end - 1) + toSwap.charAt(end);
			String swapped = Swap(toSwap.substring(0, end)) + "" + toSwap.charAt(end) + toSwap.charAt(end - 1); 
			//return swapped + lastCharOddLength;
			// System.out.print("\t" + lastCharOddLength);
			return swapped;
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
		System.out.println(Swap("Swapthis"));
		System.out.println(Swap("Swapethis"));
	}
}