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
}
