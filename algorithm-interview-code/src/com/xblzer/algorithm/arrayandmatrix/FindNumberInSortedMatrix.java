package com.xblzer.algorithm.arrayandmatrix;

/**
 * 从行列都排好序的矩阵中找到指定数
 * @author 行百里者
 * @date 2022-07-15 16:26
 */
public class FindNumberInSortedMatrix {
    public boolean contains(int[][] matrix, int k) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == k) {
                return true;
            } else if (matrix[row][col] > k) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
