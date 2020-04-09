/**
 * Some basic functions related to palindromes.
 * @author Martin Young
 */

public class Palindrome {
    /**
     * Given a String, returns a Deque.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }
    /**
     * Returns true if the word is a palindrome.
     */
    /*public boolean isPalindrome(String word) {
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
    }*/
    
    /**
     * Recursive isPalindrome()
     */
    private boolean isPalindrome(Deque d) {
        if (d.size() == 1 || d.size() == 0) {
            return true;
        } else {
            boolean condition = d.removeFirst() == d.removeLast();
            return condition && isPalindrome(d);
        }
    }
    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        return isPalindrome(d);
    }
    /*private boolean isPalindromeOffByOne(Deque d) {
        if (d.size() == 1 || d.size() == 0) {
                return true;
        } else {
            CharacterComparator cc = new OffByOne();
            boolean condition = cc.equalChars(d.removeFirst(), d.removeLast());
            return condition && isPalindromeOffByOne(d);
        }    
    }*/
    public boolean isPalindrome(String word, CharacterComparator cc) {
        //cc = new OffByN(5);
        int i;
        boolean res = false;
        for (i = 0; i < word.length() / 2; i += 1) {
            boolean condition = cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i));
            if (!condition) {
                break;
            }
        }
        if (i == word.length() / 2) {
            res = true;
        }
        return res;
    }
}
