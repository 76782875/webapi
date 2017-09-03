package com.tubugs.springboot.frame.validator;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xuzhang on 2017/9/3.
 */
public class Validator {

    /**
     * 手机号校验
     *
     * @param phone
     */
    public static void checkPhone(String phone) {
        checkNotNull(phone, "手机号");
        if (!isMobile(phone))
            throw new ParamError(String.format("手机号格式错误"));
    }

    /**
     * 非空校验
     *
     * @param param
     * @param desc
     */
    public static void checkNotNull(String param, String desc) {
        if (StringUtils.isEmpty(param))
            throw new ParamError(String.format("%s 不能为空", desc));
    }

    /**
     * 字符串长度校验
     *
     * @param min
     * @param max
     * @param param
     * @param desc
     */
    public static void checkLength(Integer min, Integer max, String param, String desc) {
        if (min == null)
            min = 0;
        if (max == null)
            max = Integer.MAX_VALUE;
        checkNotNull(param, "desc");
        if (param.length() > max) {
            throw new ParamError(String.format("%s 长度不能大于 %s", desc, max));
        } else if (param.length() < min) {
            throw new ParamError(String.format("%s 长度不能小于 %s", desc, min));
        }
    }

    /**
     * 数字大小校验
     *
     * @param min
     * @param max
     * @param param
     * @param desc
     */
    public static void checkSize(Integer min, Integer max, Integer param, String desc) {
        if (min == null)
            min = 0;
        if (max == null)
            max = Integer.MAX_VALUE;
        if (param == null)
            throw new ParamError(String.format("%s 不能为空", desc));
        if (param > max) {
            throw new ParamError(String.format("%s 不能大于 %s", desc, max));
        } else if (param < min) {
            throw new ParamError(String.format("%s 不能小于 %s", desc, min));
        }
    }

    private static boolean isMobile(final String str) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
