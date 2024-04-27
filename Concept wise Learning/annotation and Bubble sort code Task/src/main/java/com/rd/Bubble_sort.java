package com.rd;

public class Bubble_sort {
    public static int[] sort(int[] nums) {
        if(null==nums) {
            return new int[]{};
        }
        int size=nums.length;
        for(int i=0;i<size-1;i++) {
            for(int j=0;j<size-i-1;j++) {
                if(nums[j]>nums[j+1]) {
                    int temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                }
            }
        }
        return nums;
    }

}
