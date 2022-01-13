package cn.edu.bupt.demo;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * @author zy
 * @date 2022/1/12 下午9:17
 */
public class TestRunner {
    public static void main(String[] args){

        Result result = JUnitCore.runClasses(TeamControllerTest.class);
        System.out.println("测试结果"+result.wasSuccessful());

    }
}
