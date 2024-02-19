package leetcode;

public class KMP {
    public void getNext(int[] next, String s){
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)){
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)){
                j++;
            }
            next[i] = j;
        }
    }

    int strStr(String haystack, String needle) {
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++){
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)){
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)){
                j++;
            }
            if (j == needle.length() - 1){
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String s = "aabaaf";
        KMP test = new KMP();
        int[] next = new int[s.length()];
        test.getNext(next, s);
    }
}
