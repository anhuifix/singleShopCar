/*
 * 
 */
package com.mask.singleshopcar.Utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * 
 * Utils - 数字
 * 
 * <p> 数字工具类
 * 
 * @author zhuangAH
 * @version 1.0
 */
public final class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
	
	/** 默认格式 */
	public static final DecimalFormat NO_FORMAT = new DecimalFormat("#0");
	/** 默认格式 */
	public static final DecimalFormat DEFAULT_FORMAT = new DecimalFormat("#0.00");
    /** 默认格式 */
    public static final DecimalFormat DEFAULT_NUMBER_FORMAT = new DecimalFormat("#0.##");
	/** 默认格式 */
	public static final DecimalFormat DEFAULT_FORMAT_2 = new DecimalFormat("#0.#");
	
//	/** 单价小数点位数 */
	public static DecimalFormat DEFAULT_PRICE_SCALE_FORMAT = new DecimalFormat("#0.##");
	
	/** 数量小数点位数 */
	public static DecimalFormat DEFAULT_QTY_SCALE_FORMAT = new DecimalFormat("#0.00");
	
	/** 金额小数点位数 */
	public static DecimalFormat DEFAULT_MONEY_SCALE_FORMAT = new DecimalFormat("#0.###");
	
	/** 数量小数点位数 */
	public static DecimalFormat DEFAULT_QTY_SCALE_THOUSANDS_FORMAT = new DecimalFormat("###,###,###,##0.###");
	
	/** 金额小数点位数 */
	public static DecimalFormat DEFAULT_MONEY_SCALE_THOUSANDS_FORMAT = new DecimalFormat("###,###,###,##0.##");
	
	/** 千分位格式 */
	public static final DecimalFormat THOUSANDS_FORMAT = new DecimalFormat("###,###,###,##0.##");
	
	/** 默认值 */
	public static final String DEFAULT_STRING_VALUE = DEFAULT_FORMAT.format(0);
	
	/** 默认值 */
	private static final BigDecimal DEFAULT_BIG_DECIMAL_VALUE = BigDecimal.ZERO;
	
	/** 默认值 */
	private static final Long DEFAULT_LONG_VALUE = 0L;

	/** 已完成销售订单的订单号中的日期格式 */
	public static final FastDateFormat ORDER_NO_DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	/** 草稿销售订单的订单号中的日期格式 */
	public static final FastDateFormat TEMPORARY_ORDER_NO_DATE_FORMAT = FastDateFormat.getInstance("yyMMdd");
	
	/** 舍入模式：四舍五入 */
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	
	/** 舍入精度 */
	private static final int ROUNDING_PRECISION = 6;
	
	/**
	 * 不允许实例化
	 */
	private NumberUtils() {
	}
	
	// 格式化 begin
	// --------------------------------------------------
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @return
	 */
	public static String format(final Object value) {
		return format(value, DEFAULT_STRING_VALUE, DEFAULT_FORMAT);
	}
	
	/**
	 * 单价小数点位数
	 * @param value
	 * @return
	 */
	public static String formatPriceScale(final Object value){
		return format(value, DEFAULT_STRING_VALUE, DEFAULT_PRICE_SCALE_FORMAT);
	}
	
	/**
	 * 数量小数点位数(带千分位)
	 * @param value
	 * @return
	 */
	public static String formatQtyScale(final Object value){
		return format(value, DEFAULT_STRING_VALUE, DEFAULT_QTY_SCALE_THOUSANDS_FORMAT);
	}
	
	/**
	 * 数量小数点位数
	 * @param value
	 * @return
	 */
	public static String formatQty(final Object value){
	    return format(value, DEFAULT_STRING_VALUE, DEFAULT_QTY_SCALE_FORMAT);
	}

	/**
	 * 折扣
	 * @param value
	 * @return
	 */
	public static String formatDiscount(final Object value){
		return format(value, DEFAULT_STRING_VALUE, DEFAULT_FORMAT_2);
	}
	
	/**
	 * 金额小数点位数(带千分位)
	 * @param value
	 * @return
	 */
	public static String formatMoneyScale(final Object value){
		return format(value, DEFAULT_STRING_VALUE, DEFAULT_MONEY_SCALE_THOUSANDS_FORMAT);
	}
	
	/**
	 * 金额小数点位数
	 * @param value
	 * @return
	 */
	public static String formatMoney(final Object value){
	    return format(value, DEFAULT_STRING_VALUE, DEFAULT_MONEY_SCALE_FORMAT);
	}
	
	/**
	 * 折扣小数点位数
	 * @param value
	 * @return
	 */
	public static String formatDiscountScale(final Object value){
		return format(value, NO_FORMAT, NO_FORMAT);
	}
	
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @return
	 */
	public static String format(final Object value, final String defaultValue) {
		return format(value, defaultValue, DEFAULT_FORMAT);
	}

	/**
	 * 将BigDecimal转为String,四舍五入
	 *
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @return
	 */
	public static String formatRounded(final BigDecimal value, final String defaultValue) {
		return format(value.setScale(2,BigDecimal.ROUND_HALF_UP), defaultValue, DEFAULT_FORMAT);
	}

	/**
	 * 将BigDecimal转为String，四舍五入
	 * @param value 值
	 * @return
	 */
	public static String formatRounded(final BigDecimal value) {
		return format(value.setScale(2,BigDecimal.ROUND_HALF_UP), new BigDecimal(0), DEFAULT_FORMAT);
	}

	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @param formatPattern 格式
	 * @return
	 */
	public static String format(final Object value, final String defaultValue, final String formatPattern) {
		return format(value, defaultValue, new DecimalFormat(formatPattern));
	}
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @param format 格式
	 * @return
	 */
	public static String format(final Object value, final String defaultValue, final DecimalFormat format) {
		Object formatValue = value;
		if (formatValue == null) {
			return defaultValue;
		}
		
		return format.format(formatValue);
	}
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param format 格式
	 * @param  ，值为null时用该值代替
	 * @return
	 */
	public static String format(final Object value, final DecimalFormat format) {
	    Object formatValue = value;
	    if (formatValue == null) {
	        return DEFAULT_STRING_VALUE;
	    }
	    
	    return format.format(formatValue);
	}
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @return
	 */
	public static String format(final Object value, final Object defaultValue) {
		return format(value, defaultValue, DEFAULT_FORMAT);
	}
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @param formatPattern 格式
	 * @return
	 */
	public static String format(final Object value, final Object defaultValue, final String formatPattern) {
		return format(value, defaultValue, new DecimalFormat(formatPattern));
	}
	
	/**
	 * 将BigDecimal转为String
	 * 
	 * @param value 值
	 * @param defaultValue 默认值，值为null时用该值代替
	 * @param format 格式
	 * @return
	 */
	public static String format(final Object value, final Object defaultValue, final DecimalFormat format) {
		Object formatValue = value == null ? defaultValue : value;
		if (formatValue == null) {
			throw null;
		}
		
		return format.format(value);
	}
	
	// --------------------------------------------------
	// 格式化 end
	
	// 类型转换 begin
	// --------------------------------------------------
	
	/**
	 * 将String转成BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(String value) {
		return toBigDecimal(value, DEFAULT_BIG_DECIMAL_VALUE);
	}

	/**
	 * 将String转成BigDecimal
	 *
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(double value) {
		return toBigDecimal(String.valueOf(value), DEFAULT_BIG_DECIMAL_VALUE);
	}

	/**
	 * 将String转成BigDecimal
	 *
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimalValue(final String value) {
		if(value == null || value.length() == 0){
			return BigDecimal.ZERO;
		}
		return new BigDecimal(value.replaceAll(",",""));
	}

	/**
	 * 将String转成BigDecimal
	 * 
	 * @param value
	 * @param defaultValue 默认值
	 * @return
	 */
	public static BigDecimal toBigDecimal(final String value, final BigDecimal defaultValue) {
		BigDecimal ret = defaultValue;
		try {
			ret = new BigDecimal(value);
		} catch (RuntimeException e) {
			// ignore
		}
		
		return ret;
	}
	
	/**
	 * 将String转成Long
	 * 
	 * @param value
	 * @return
	 */
	public static Long toLong(final Object value) {
		return toLong(value, DEFAULT_LONG_VALUE);
	}
	
	/**
	 * 将String转成Long
	 * 
	 * @param value
	 * @param defaultValue 默认值
	 * @return
	 */
	public static Long toLong(final Object value, Long defaultValue) {
		Long ret = defaultValue;
		if (value != null) {
			String valueStr = value instanceof String ? (String) value : value.toString();
			try {
				ret = Long.parseLong(valueStr);
			} catch (NumberFormatException e) {
				// ignore
			}
		}
		
		return ret;
	}
	
	// --------------------------------------------------
	// 类型转换 end
	
	// 运算 begin
	// --------------------------------------------------
	
	// Integer
	/**
	 * number1 + number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Integer add(Integer number1, Integer number2) {
		return number1 == null || number2 == null ? 0 : number1 + number2;
	}
	
	/**
	 * number1 - number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Integer subtract(Integer number1, Integer number2) {
		return number1 == null || number2 == null ? 0 : number1 - number2;
	}
	
	/**
	 * number1 * number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Integer multiply(Integer number1, Integer number2) {
		return number1 == null || number2 == null ? 0 : number1 * number2;
	}
	
	/**
	 * number1 / number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Integer divide(Integer number1, Integer number2) {
		return number1 == null || number2 == null || number2 == 0 ? 0 : number1 / number2;
	}
	
	/**
	 * -number
	 * 
	 * @param number
	 * @return
	 */
	public static Integer negate(Integer number) {
		return number == null ? 0 : -number;
	}
	
	// Long
	/**
	 * number1 + number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Long add(Long number1, Long number2) {
		return number1 == null || number2 == null ? 0L : number1 + number2;
	}
	
	/**
	 * number1 - number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Long subtract(Long number1, Long number2) {
		return number1 == null || number2 == null ? 0L : number1 - number2;
	}
	
	/**
	 * number1 * number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Long multiply(Long number1, Long number2) {
		return number1 == null || number2 == null ? 0L : number1 * number2;
	}
	
	/**
	 * number1 / number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Long divide(Long number1, Long number2) {
		return number1 == null || number2 == null || number2 == 0L ? 0L : number1 / number2;
	}
	
	/**
	 * -number
	 * 
	 * @param number
	 * @return
	 */
	public static Long negate(Long number) {
		return number == null ? 0L : -number;
	}
	
	// Float
	/**
	 * number1 + number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Float add(Float number1, Float number2) {
		return number1 == null || number2 == null ? 0F : number1 + number2;
	}
	
	/**
	 * number1 - number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Float subtract(Float number1, Float number2) {
		return number1 == null || number2 == null ? 0F : number1 - number2;
	}
	
	/**
	 * number1 * number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Float multiply(Float number1, Float number2) {
		return number1 == null || number2 == null ? 0F : number1 * number2;
	}

	/**
	 * 判断是否小于0
	 * @return
     */
	public static boolean isZero(String number){
		return new BigDecimal(number).compareTo(BigDecimal.ZERO) < 1;
	}

    /**
     * 判断是否小于0
     * @return
     */
    public static boolean isZero(BigDecimal number){
        return number.compareTo(BigDecimal.ZERO) < 1;
    }

	/**
	 * number1 / number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Float divide(Float number1, Float number2) {
		return number1 == null || number2 == null || number2 == 0F ? 0F : number1 / number2;
	}
	
	/**
	 * -number
	 * 
	 * @param number
	 * @return
	 */
	public static Float negate(Float number) {
		return number == null ? 0F : -number;
	}
	
	// Double
	/**
	 * number1 + number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Double add(Double number1, Double number2) {
		return number1 == null || number2 == null ? 0D : number1 + number2;
	}
	
	/**
	 * number1 - number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Double subtract(Double number1, Double number2) {
		return number1 == null || number2 == null ? 0D : number1 - number2;
	}
	
	/**
	 * number1 * number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Double multiply(Double number1, Double number2) {
		return number1 == null || number2 == null ? 0D : number1 * number2;
	}
	
	/**
	 * number1 / number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static Double divide(Double number1, Double number2) {
		return number1 == null || number2 == null || number2 == 0D ? 0D : number1 / number2;
	}
	
	/**
	 * -number
	 * 
	 * @param number
	 * @return
	 */
	public static Double negate(Double number) {
		return number == null ? 0D : -number;
	}
	
	// BigDecimal
	/**
	 * number1 + number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static BigDecimal add(BigDecimal number1, BigDecimal number2) {
		return number1 == null || number2 == null ? BigDecimal.ZERO : number1.add(number2);
	}
	
	/**
	 * number1 - number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
		return number1 == null || number2 == null ? BigDecimal.ZERO : number1.subtract(number2);
	}
	
	/**
	 * number1 * number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
		return number1 == null || number2 == null ? BigDecimal.ZERO : number1.multiply(number2);
	}
	
	/**
	 * number1 / number2
	 * 
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static BigDecimal divide(BigDecimal number1, BigDecimal number2) {
		return number1 == null || number2 == null || BigDecimal.ZERO.equals(number2) ? BigDecimal.ZERO : number1.divide(number2, ROUNDING_PRECISION, ROUNDING_MODE);
	}

	public static boolean equalsBigDecimal(BigDecimal number1, BigDecimal number2){
		return number1.equals(number2);

	}

	/**
	 * -number
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal negate(BigDecimal number) {
		return number == null ? BigDecimal.ZERO : number.negate();
	}
	
	// --------------------------------------------------
	// 运算 end
	
	public static void initNumberScale(int priceScale,int qtyScale,int moneyScale){

        if(qtyScale < 2){
            qtyScale = 2;
        }
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < qtyScale; index++){
			sb.append("#");
		}
        NumberUtils.DEFAULT_QTY_SCALE_FORMAT = new DecimalFormat("#0."+sb.toString());
        NumberUtils.DEFAULT_QTY_SCALE_THOUSANDS_FORMAT = new DecimalFormat("###,###,###,##0."+sb.toString());
	}

	/**
	 * 超过万就加后缀万
	 * @return 万
     */
	public static String moneryToTenThousand(BigDecimal bigDecimal){
		BigDecimal tenThouand = new BigDecimal(10000);
		int type = bigDecimal.compareTo(tenThouand);
		String showResult = null;
//		-1 小于 0 等于 1 大于
//		if (type == 1) {
//			BigDecimal restult = bigDecimal.divide(tenThouand);
//			showResult = format(restult,DEFAULT_FORMAT) + "万";
//		} else{
			showResult  = format(bigDecimal,DEFAULT_FORMAT);
//		}
		return showResult;
	}
	//保留两位小数的double
	public static Double SubNumber(Double doube_var){
		DecimalFormat df2 = new DecimalFormat("0.00");
		return  Double.valueOf(df2.format(doube_var));
	}

	//取消double的科学技术法
	public static BigDecimal showDoubleValue(Double showdouble){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		return toBigDecimal(nf.format(showdouble));
	}
}
