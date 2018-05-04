public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> characterDeque = new LinkedListDeque<>();
        for (Character c : word.toCharArray()) {
            characterDeque.addLast(c);
        }
        return characterDeque;
    }

    private static boolean isPalindrome(Deque<Character> cd) {
        int size = cd.size();
        if (size == 0 || size == 1) {
            return true;
        }

        Character fist, last;
        fist = cd.removeFirst();
        last = cd.removeLast();

        return fist.compareTo(last) == 0 && isPalindrome(cd);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> cd = wordToDeque(word);
        return isPalindrome(cd);
    }
}
