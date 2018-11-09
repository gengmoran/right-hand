package com.misterfat.framework.mybatis.query;

public interface QueryCondition {

	final String SHORLINE_DATE_FORMAT = "yyyy-MM-dd";

	final String SHORLINE_TIME_FORMAT = "HH:mm:ss";

	final String SHORLINE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	final String TIMEZONE_GMT8 = "GMT+8";

	final String NULL_VALUE = "NULL_VALUE";

	final String YEAR_MONTH = "yyyy-MM";

	String getOrderBy();

}
