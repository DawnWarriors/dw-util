package dw.common.util.num;

import java.math.BigDecimal;

public class IntUtil
{
	/**
	 * 将Object对象转换成int,默认值0
	 * @param obj
	 * @return
	 */
	public static int objToInt(Object obj)
	{
		return objToInt(obj, 0);
	}

	/**
	 * 将Object对象转换成int
	 * @param obj
	 * @param defaultVal
	 * @return
	 */
	public static int objToInt(Object obj, int defaultVal)
	{
		if (obj == null)
		{
			return defaultVal;
		}
		if (obj instanceof String)
		{
			if (obj.equals(""))
			{
				return defaultVal;
			}
			return Integer.parseInt((String) obj);
		}
		if (obj instanceof Long)
		{
			return ((Long) obj).intValue();
		}
		if (obj instanceof BigDecimal)
		{
			return ((BigDecimal) obj).intValue();
		}
		return (Integer) obj;
	}
}
