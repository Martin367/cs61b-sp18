public class Palindrome {
    /**
     * Given a String, return a Deque.
     */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }
    /**
     * Returns true if the word is a palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        int i;
        boolean res = false;
        for (i = 0; i < d.size() / 2; i += 1) {
            boolean condition = d.get(i) == d.get(d.size() - 1 - i);
            if (!condition) {
                break;
            }
        }
        if (i == d.size() / 2) {
            res = true;
        }
        return res;
    }
}
