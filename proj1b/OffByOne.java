public class OffByOne implements CharacterComparator {
    
    @Override
    public boolean equalChars(char a, char b) {
        boolean res = false;
        int diff = a - b;
        if (diff == 1 || diff == -1) {
            res = true;
        }
        return res;
    }
}
