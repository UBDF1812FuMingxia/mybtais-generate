package com.utils;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName : BusinessException
 * @Description : 自定义异常
 * @Author : fmx
 * @Date: 2021-07-29 17:18
 */
public class BusinessException extends RuntimeException {

    public static class WithDataBusinessException extends BusinessException {

        private static final long serialVersionUID = 1L;

        public WithDataBusinessException (Object data,
                                          String message,
                                          Object... args) {
            super(message, args);
            this.data = data;
        }

        private Object data;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public BusinessException (String message) {super(message); }

    public BusinessException (Throwable e) {super(e);}

    public BusinessException (String message, Throwable e) {super(message,e);}

    public BusinessException (String message,Object... args) {super(prepareMessage(message, args));}

    @Override
    public void printStackTrace() {
        System.err.println("BusinessException:" + this.getMessage());
        Log4jHelper.getLogger().error("BusinessException:" + this.getMessage());
        //super.printStackTrace();
    }

    private static String prepareMessage(String message, Object... args) {
        if (Objects.isNull(args) || args.length == 0) {
            return message;
        }
        int i = 0;
        while (message.contains("{}") && i < args.length) {
            message = message.replaceFirst("\\{\\}", String.valueOf(args[i]));
            i++;
        }
        return message;
    }

    public static <T> T throwsIf(Supplier<T> supplier, Predicate<T> throwsIf,
                                 String message, Object... args) {
        T obj = supplier.get();
        if (throwsIf.test(obj)) {
            throw new BusinessException(message, args);
        }
        return obj;
    }

    public static <T> T throwsIfNull(Supplier<T> supplier, String message,
                                     Object... args) {
        return throwsIf(supplier, Objects::isNull, message, args);
    }
}
