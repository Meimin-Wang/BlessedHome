package com.zhouzhili.zhilihomeproject.opencv;

import com.zhouzhili.zhilihomeproject.algorithm.utils.MatrixUtils;
import org.junit.jupiter.api.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * @ClassName OpencvTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/28 : 16:03
 * @Email blessedwmm@gmail.com
 */
public class OpencvTest {

    @Test
    public void testOpenCv() {
        System.load("/Users/blessed/Downloads/opencv-4.x/build/lib/libopencv_java454.dylib");
        Mat m = Mat.ones(3, 4, CvType.CV_8U);
        String matrixStr = MatrixUtils.printMatrix(m);
        Mat m2 = new Mat();

        System.out.println(matrixStr);
        Mat.Atable<Integer> at = m.at(int.class, 1, 1);
        System.out.println(at.getV());
    }

}
