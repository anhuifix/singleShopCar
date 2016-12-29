/*
 * 
 */
package com.mask.singleshopcar.Utils;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Utils - 类型
 * 
 * @author zhuangAH
 * @version 1.0
 */
public final class TypeUtils {
	
	/**
	 * 不允许实例化
	 */
	private TypeUtils() {
	}
	

	/**
	 * 将int转为Boolean
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean toBoolean(int value) {
		return value != 0;
	}
	
	/**
	 * 将int转为Date
	 * 
	 * @param value
	 * @return
	 */
	public static Date toDate(long value) {
		return value <= 0L ? null : new Date(value);
	}
	
	/**
	 * 将double转为BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(double value) {

		return new BigDecimal(String.valueOf(value));
	}

    /**
     * 将double转为BigDecimal
     *
     * @param value
     * @return
     */
    public static BigDecimal toBigDecimal(String value) {
        return new BigDecimal(value);
    }


	
	/**
	 * 将String转为枚举
	 * 
	 * @param value
	 * @param enumClass 枚举的Class
	 * @return
	 */
	public static <T> T toEnum(String value, Class<T> enumClass) {
		if (enumClass == null || !Enum.class.isAssignableFrom(enumClass)) {
			throw new IllegalArgumentException("The argument \'enumClass\' must be Class of a enum.");
		}
		
		T ret = null;
		if (StringUtils.isNotBlank(value)) {
			// 遍历枚举中的常量
			for (T enumConstant : enumClass.getEnumConstants()) {
				if (value.equals(enumConstant.toString())) {
					ret = enumConstant;
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * 将Date转为Long
	 * 
	 * @param value
	 * @return
	 */
	public static Long toLong(Date value) {
		return value == null ? null : value.getTime();
	}
	
	/**
	 * 将BigDecimal转为double
	 * 
	 * @param value
	 */
	public static double toDouble(BigDecimal value) {
		return value == null ? 0d : value.doubleValue();
	}
	
	/**
	 * 将枚举转为String
	 * 
	 * @param value
	 */
	public static String toString(Enum<?> value) {
		return value == null ? null : value.toString();
	}
	
	/**
	 * 将Boolean转为Integer
	 * 
	 * @param value
	 */
	public static Integer toInteger(Boolean value) {
		return BooleanUtils.isTrue(value) ? 1 : 0;
	}
	
	/**
	 * 将Object转为Long
	 * 
	 * @param value
	 * @return
	 */
	public static Long toLong(Object value) {
		Long ret = null;
		if (value != null) {
			if (value instanceof Long) {
				ret = (Long) value;
			} else if (value instanceof Number) {
				ret = ((Number) value).longValue();
			} else if (value instanceof Boolean) {
				ret = ((Boolean) value) ? 1L : 0L;
			} else if (value instanceof Date) {
				ret = ((Date) value).getTime();
			} else {
				try {
					ret = Long.parseLong(value.toString());
				} catch (NumberFormatException e) {
					// ignore
				}
			}
		}
		return ret;
	}
	
	/**
	 * 将Object转为Double
	 * 
	 * @param value
	 * @return
	 */
	public static Double toDouble(Object value) {
		Double ret = null;
		if (value != null) {
			if (value instanceof Double) {
				ret = (Double) value;
			} else if (value instanceof Number) {
				ret = ((Number) value).doubleValue();
			} else if (value instanceof Boolean) {
				ret = ((Boolean) value) ? 1D : 0D;
			} else {
				try {
					ret = Double.parseDouble(value.toString());
				} catch (NumberFormatException e) {
					// ignore
				}
			}
		}
		return ret;
	}
	
	/**
	 * 将Object转为String
	 * 
	 * @param value
	 * @return
	 */
	public static String toString(Object value) {
		return value == null ? null : value.toString();
	}
	
}
