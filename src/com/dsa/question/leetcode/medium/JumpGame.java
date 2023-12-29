package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class JumpGame {

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();

        int[] arr = {2, 3, 1, 1, 4};
        int[] arr2 = {2, 0};
        int[] arr3 = {0};
        boolean canJump = jumpGame.canJump(arr);
        boolean canJump2 = jumpGame.canJump(arr2);
        boolean canJump3 = jumpGame.canJump(arr3);

        out.println(canJump);
        out.println(canJump2);
        out.println(canJump3);
    }

    public boolean canJump(int[] nums) {

        int numsL = nums.length;
        int counter = 0; //Index 0
        int i = nums[0]; //2

        while (counter != numsL - 1 && counter < nums.length) {

            if (counter + i == counter) return false;

            int tempI = nums[Math.min(counter + i, nums.length - 1)]; //1
            counter = Math.min(counter + i, nums.length - 1);
            i = tempI;
        }

        return counter < nums.length && nums[numsL - 1] == nums[counter];
    }
}
