import java.util.ArrayList;
import java.util.List;

public class string {
    /*
        题目：反转字符串
        编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
        不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length/2; i++) {
            char temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = temp;
        }
    }
    /*
        题目：整数反转
        给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
        如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int t = x % 10;     // 可以通过对10区取获得每一位
            int newRes = res * 10 + t;
            //如果数字溢出，直接返回0
            if ((newRes - t) / 10 != res)
                return 0;
            res = newRes;
            x = x / 10;
        }
        return res;
    }
    /*
        题目：字符串中的第一个唯一字符
     */
    public int firstUniqChar(String s) {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        for (char c : str) {
            count[c - 97]++;
        }
        for (int i = 0; i < str.length; i++) {
            if (count[str[i]-97]==1){
                return i;
            }
        }
        return -1;
    }

    /*
        题目：有效的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        if (str1.length != str2.length){
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < str1.length; i++) {
            count[str1[i]-'a']++;
            count[str2[i]-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0){
                return false;
            }
        }
        return true;
    }
    /*
        题目：验证回文串
        如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个回文串 。
        字母和数字都属于字母数字字符。
     */
    public boolean isPalindrome(String s) {
        if (s.length()==0){
            return true;
        }
        int left = 0, right = s.length()-1;
        while (left<right){
            while (left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /*
        题目：字符串转换整数 (atoi)
        请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     */
    public static int myAtoi(String s) {
        char[] str = s.toCharArray();
        int sign = 1;
        int result = 0;
        int begin = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i]==' '){
                continue;
            }else {
                begin = i;
            }
            if (str[begin] == '-'){
                sign = -1;
                begin++;
            }else if (str[begin] == '+'){
                begin++;
            }
            if (begin < str.length && !Character.isDigit(str[begin])){
                return 0;
            }
            while (begin < str.length && Character.isDigit(str[begin])){
                long new_result = (long) result * 10 + (str[begin] -'0');
                if ((int)new_result != new_result) {
                    return (sign==-1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = (int)new_result;
                begin++;
            }
            return sign * result;
        }
        return 0;
    }
    public static void main(String[] args) {
        myAtoi("-91283472332");
    }
}
