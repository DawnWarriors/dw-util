package dw.common.util.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonUtil
{
	/**
	 * 从JSON对象中获取JSON数组
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static JSONArray getJsonArray(JSONObject jsonObject, String key)
	{
		if(jsonObject==null)
		{
			throw new NullPointerException("jsonObject is null");
		}
		JSONArray result = null;
		try
		{
			result = jsonObject.getJSONArray(key);
		} catch (JSONException e)
		{
			result = new JSONArray();
		}
		return result;
	}
}
