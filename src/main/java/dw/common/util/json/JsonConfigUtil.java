package dw.common.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonConfigUtil
{
	private static JsonConfig	jsonConfig	= null;

	public static JsonConfig getJsonConfig()
	{
		if (jsonConfig == null)
		{
			jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor()
			{
				@Override
				public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2)
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					return arg1 == null ? "" : df.format(arg1);
				}

				@Override
				public Object processArrayValue(Object arg0, JsonConfig arg1)
				{
					return null;
				}
			});
		}
		return jsonConfig;
	}
}
