package dw.common.util.list;

import dw.common.util.map.MapUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListUtil
{
	/**
	 * 返回两个集合的运算结果
	 *
	 * @param list1  列表1
	 * @param list2  列表2
	 * @param option 1:(list1-list2)在list1中不在list2中
	 *               0:(list1-list2)+(list2-list1)在list1和list2合集减去公共部分
	 *               -1:(list2-list1)在list2中不在list1中
	 * @param <T>    泛型对象
	 * @return 集合运算结果
	 */
	public static <T> List<T> getDiffSet(List<T> list1, List<T> list2, int option)
	{
		List<T> result = new ArrayList<>();
		//在list1中，不再list2中
		if (option == 1)
		{
			result = list1.stream().filter((t) -> !list2.contains(t)).collect(Collectors.toList());
		} else if (option == -1)
		{
			result = list2.stream().filter((t) -> !list1.contains(t)).collect(Collectors.toList());
		} else
		{
			List<T> r1 = getDiffSet(list1, list2, 1);
			List<T> r2 = getDiffSet(list1, list2, -1);
			result.addAll(r1);
			result.addAll(r2);
		}
		return result;
	}

	/**
	 * 将from中得元素全部添加到to中
	 *
	 * @param to 添加至
	 * @param from 添加来源
	 * @param <T>    泛型对象
	 * @return 合并后的List
	 *
	 */
	public static <T> List<T> mergeList(List<T> to, List<T> from)
	{
		if (from == null)
		{
			return to;
		}
		if (to == null)
		{
			to = new ArrayList<>();
		}
		to.addAll(from);
		return to;
	}

	/**
	 * 使用指定字符串连接strList，生成最终连接后的字符串
	 *
	 * @param strList 字符串列表
	 * @param linkStr 连接字符串
	 * @return 连接后的字符串
	 */
	public static String linkStrList(List<String> strList, String linkStr)
	{
		if (strList == null || strList.size() == 0)
		{
			return "";
		}
		StringBuffer linkedStr = new StringBuffer();
		for (int i = 0 ; i < strList.size() ; i++)
		{
			if (i > 0)
			{
				linkedStr.append(linkStr);
			}
			linkedStr.append(strList.get(i));
		}
		return linkedStr.toString();
	}

	/**
	 * 将JSONArray对象转换成MapList对象
	 *
	 * @param jsonArray JSONArray对象
	 * @return MapList对象
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> JSONArray2MapList(JSONArray jsonArray)
	{
		if (jsonArray == null || jsonArray.size() == 0)
		{
			return null;
		}
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for (Object obj : jsonArray)
		{
			if (obj instanceof JSONNull)
			{
				mapList.add(null);
			} else
			{
				mapList.add((Map<String,Object>) obj);
			}
		}
		return mapList;
	}

	/**
	 * 批量对Map进行base64解码操作
	 * 具体说明参考：MapUtil.base64StrDecode
	 *
	 * @param list Base64编码的list对象
	 * @return 解码后的List
	 * @throws UnsupportedEncodingException 不支持的编码格式
	 */
	@SuppressWarnings("unchecked")
	public static final List<Object> listBase64StrDecode(List<Object> list) throws UnsupportedEncodingException
	{
		if (list == null)
		{
			return null;
		}
		List<Object> newList = new ArrayList<>();
		for (Object obj : list)
		{
			if (obj instanceof String)
			{
				obj = new String(Base64.decodeBase64((String) obj), "utf-8");
			} else if (obj instanceof Map)
			{
				obj = MapUtil.base64StrDecode((Map<String,Object>) obj);
			} else if (obj instanceof List)
			{
				obj = ListUtil.listBase64StrDecode((List<Object>) obj);
			} else if (obj instanceof JSONNull)
			{
				obj = null;
			}
			newList.add(obj);
		}
		return newList;
	}
}
