package com.zhouzhili.zhilihomeproject.algorithm.utils;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.springframework.util.Assert;

/**
 * @ClassName MatrixUtils
 * @Description 矩阵工具类
 * @Author blessed
 * @Date 2021/11/28 : 16:25
 * @Email blessedwmm@gmail.com
 */
@Slf4j
public class MatrixUtils {

    public static String printMatrix(Mat matrix) {
        Assert.notNull(matrix, "matrix is null!");
        int m = matrix.rows();
        int n = matrix.cols();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                double[] value = matrix.get(i, j);
                for (double d : value) {
                    sb.append(d);
                    sb.append("\t");
                }
            }
            sb.append("\b\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        Mat m = Mat.zeros(3, 4, CvType.CV_8U);
        printMatrix(m);
    }
}
