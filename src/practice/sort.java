package practice;

public class sort {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        int index1 = 0;
        int index2 = 0;
        int i = 0;
        while (index1 < m && index2 < n){
            if (temp[index1] <= nums2[index2]){
                nums1[i++] = temp[index1++];
            }else {
                nums1[i++] = nums2[index2++];
            }
        }
        while (index1 < m){
            nums1[i++] = temp[index1++];
        }
        while (index2 < n){
            nums1[i++] = nums2[index2++];
        }
    }
    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int index = 0;
        while (left < right){
            int mid = right + ((left - right) >> 1);
            if (!isBadVersion(mid)){
                left = mid + 1;
            }else {
                index = mid;
                right = mid - 1;
            }
        }
        return index;
    }

    static boolean isBadVersion(int version){
        return version >= 4;
    }
    public static void main(String[] args) {
        int temp = (1-5)>>1;
        firstBadVersion(5);
    }
}
