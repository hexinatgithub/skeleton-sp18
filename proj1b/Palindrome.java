public class Palindrome {
    private static class CharacterEqual implements CharacterComparator {
        @Override
        public boolean equalChars(char x, char y) {
            return x == y;
        }
    }

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> characterDeque = new LinkedListDeque<>();
        for (Character c : word.toCharArray()) {
            characterDeque.addLast(c);
        }
        return characterDeque;
    }

    private static boolean isPalindrome(Deque<Character> cd, CharacterComparator cc) {
        int size = cd.size();
        if (size == 0 || size == 1) {
            return true;
        }

        Character first, last;
        first = cd.removeFirst();
        last = cd.removeLast();

        return cc.equalChars(first, last) && isPalindrome(cd, cc);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> cd = wordToDeque(word);
        CharacterEqual ce = new CharacterEqual();
        return isPalindrome(cd, ce);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> cd = wordToDeque(word);
        return isPalindrome(cd, cc);
    }
}
