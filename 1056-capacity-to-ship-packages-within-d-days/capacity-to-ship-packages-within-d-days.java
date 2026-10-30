class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;

        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            int requiredDays = 1;
            int currentWeight = 0;

            for (int w : weights) {
                if (currentWeight + w > mid) {
                    requiredDays++;
                    currentWeight = 0;
                }

                currentWeight += w;
            }

            if (requiredDays <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}