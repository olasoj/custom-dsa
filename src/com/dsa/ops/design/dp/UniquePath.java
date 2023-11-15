package com.dsa.ops.design.dp;

import com.dsa.util.PerformanceUtil;

import static java.lang.System.out;

public class UniquePath {

    public static void main(String[] args) {
        //Combination
        int answer = PerformanceUtil.measureOperationDuration(() -> cUniquePath(1, 1));
        int n;
        int m = n = 2;
        int[][] prices = {{1, 2}, {4, 1}};
        int answer2 = PerformanceUtil.measureOperationDuration(() -> oUniquePath(m, n, prices));
        out.println(answer);
        out.println(answer2);
    }

    private static int oUniquePath(int m, int n, int[][] prices) {

        int[][] computedValue = new int[m][n];
        int[] fromM = new int[m];
        int[] fromN = new int[n];

        computedValue[0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    computedValue[i][j] = prices[i][j] + Math.max(computedValue[i][j - 1], computedValue[i - 1][j]);
                    //Path
                    if (computedValue[i][j - 1] < computedValue[i - 1][j]) {
                        fromM[i] = i;
                        fromN[j] = j - 1;
                    } else {
                        fromM[i] = i - 1;
                        fromN[j] = j;
                    }
                } else if (i > 0) {
                    computedValue[i][j] = prices[i][j] + computedValue[i - 1][j];
                    fromM[i] = i - 1;
                    fromN[j] = j;
                } else if (j > 0) {
                    computedValue[i][j] = prices[i][j] + computedValue[i][j - 1];
                    fromM[i] = i;
                    fromN[j] = j - 1;
                }
            }
        }

        int pathM = m - 1;
        int pathN = n - 1;
        out.println("Path are " + pathM + "," + pathN);
        do {
            pathM = fromM[pathM];
            pathN = fromN[pathN];
            out.println("Path are " + pathM + "," + pathN);
        } while (pathM > 0 && pathN > 0);

        return computedValue[m - 1][n - 1];
    }

    private static int cUniquePath(int m, int n) {

        int[][] computedValue = new int[m + 1][n + 1];

        computedValue[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0)
                    computedValue[i][j] = computedValue[i][j - 1] + computedValue[i - 1][j];
                else if (i > 0)
                    computedValue[i][j] = computedValue[i - 1][j];
                else if (j > 0)
                    computedValue[i][j] = computedValue[i][j - 1];
            }
        }

        return computedValue[m - 1][n - 1];
    }
}
