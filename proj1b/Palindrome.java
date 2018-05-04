public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> characterDeque = new LinkedListDeque<>();
        for (Character c : word.toCharArray()) {
            characterDeque.addLast(c);
        }
        return characterDeque;
    }
}
