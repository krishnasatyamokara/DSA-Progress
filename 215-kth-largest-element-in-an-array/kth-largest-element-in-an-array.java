class Solution {
    private void heapifyDown(int[] nums, int n, int i){
        int largest = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left<n && nums[left] > nums[largest]) largest = left;
        if(right<n && nums[right] > nums[largest]) largest = right;
        if(largest != i){
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            heapifyDown(nums,n,largest);
        }
        
    }
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // Building max heap
        for(int i=n/2-1;i>=0;i--){
            heapifyDown(nums,n,i);
        }

        // getting kth largest element
        for(int i=0;i<k-1;i++){
            int temp = nums[0];
            nums[0] = nums[n - 1];
            nums[n - 1] = temp;
            n--;
            heapifyDown(nums, n, 0);
        }
        return nums[0];
    }
}