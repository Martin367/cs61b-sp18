public class OffByN implements CharacterComparator {
    int n;
    OffByN(int N) {
        n = N;
    }

    public boolean equalChars(char x, char y) {
        int diff = x - y;
        boolean res = false;
        boolean condition = diff == n || diff == -n;
        if (condition) {
            res = true;
        }
        return res;
    }
}
