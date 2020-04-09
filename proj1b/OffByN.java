public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int N) {
        n = N;
    }

    @Override
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
