import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue("a is palindrome", palindrome.isPalindrome("a"));
        assertTrue("empty word is palindrome", palindrome.isPalindrome(""));
        assertTrue("racecar is palindrome", palindrome.isPalindrome("racecar"));
        assertTrue("noon is palindrome", palindrome.isPalindrome("noon"));
        assertFalse("horse is not a palindrome", palindrome.isPalindrome("horse"));
        assertFalse("rancor is not a palindrome", palindrome.isPalindrome("rancor"));
        assertFalse("aaaaab is not a palindrome", palindrome.isPalindrome("aaaaab"));
        assertFalse("aA is not a palindrome", palindrome.isPalindrome("aA"));
    }

    @Test
    public void testIsPalindromeOffBy1() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("acb", cc));
        assertTrue(palindrome.isPalindrome("rq", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("ae", cc));
        assertFalse(palindrome.isPalindrome("za", cc));
        assertFalse(palindrome.isPalindrome("aa", cc));
    }

    @Test
    public void testIsPalindromeOffByN() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", cc));
        assertTrue(palindrome.isPalindrome("aef", cc));
        assertFalse(palindrome.isPalindrome("fh", cc));
    }
}
