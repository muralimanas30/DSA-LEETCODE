// public class Solution {
//     public int threeSumMulti(int[] arr, int target) {
//         Arrays.sort(arr);

//         HashMap<Integer, Integer> hm = new HashMap<>();
//         for (int x : arr) {
//             hm.put(x, hm.getOrDefault(x, 0) + 1);
//         }

//         int count = 0;
//         int n = arr.length;
//         int left = 0;
//         int right = n - 1;

//         while (left < right) {
//             int leftVal = arr[left];
//             int rightVal = arr[right];
//             int diff = target - (rightVal + leftVal);

//             int indx = Arrays.binarySearch(arr, left + 1, right, diff);

//             if (indx >= 0) {
//                 int currCount = 1;
//                 currCount *= hm.get(rightVal);
//                 currCount *= hm.get(leftVal);
//                 currCount *= hm.get(diff);
//                 count += currCount;

//                 while (left < right && rightVal == arr[right]) right--;
//                 while (left < right && leftVal == arr[left]) left++;
//             } else {
//                 indx = -indx - 1;
//                 int replacer = arr[indx];
//                 if (leftVal + rightVal + replacer > target) right--;
//                 else left++;
//             }
//         }
//         return count;
//     }
// }
class Solution {
    public int threeSumMulti(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        long ans = 0;

        for (int i = 0; i < n - 2; i++) {
            int sum = target - arr[i];
            int l = i + 1, r = n - 1;

            while (l < r) {
                if (arr[l] + arr[r] < sum) l++;
                else if (arr[l] + arr[r] > sum) r--;
                else {
                    int count1 = 1, count2 = 1;

                    if (arr[l] == arr[r]) {
                        int len = r - l + 1;
                        ans += (len * (len - 1) / 2);
                        break;
                    }

                    while (l < r && arr[l] == arr[l + 1]) {
                        count1++;
                        l++;
                    }

                    while (r > l && arr[r] == arr[r - 1]) {
                        count2++;
                        r--;
                    }

                    ans += (count1 * count2);
                    l++; r--;
                }
            }
        }

        return (int) (ans % 1000000007);
    }
}
