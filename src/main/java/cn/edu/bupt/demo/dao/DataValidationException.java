package cn.edu.bupt.demo.dao;

/**
 * @author zy
 * @date 2018/10/23 下午3:47
 */
public class DataValidationException extends RuntimeException{

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
