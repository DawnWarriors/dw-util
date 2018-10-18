package dw.common.util.model;

import dw.common.util.list.ListUtil;
import dw.common.util.str.StrUtil;
import dw.common.util.type.TypeUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ModelUtil {

    /**
     * 设置model属性值
     *
     * @param obj
     * @param dataMap
     * @param <T>
     */
    public static <T> void getModelFromMap(T obj, Map<String, Object> dataMap) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            List<Field> fieldsList = new ArrayList<>(Arrays.asList(fields));
            Field[] superFields = obj.getClass().getSuperclass().getDeclaredFields();
            List<Field> superFieldList = new ArrayList<>(Arrays.asList(superFields));
            List<Field> allFields = new ArrayList<>();
            ListUtil.mergeList(allFields, fieldsList);
            ListUtil.mergeList(allFields, superFieldList);
            for (Field field : allFields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                String fldName = field.getName();
                if (fldName.startsWith("_")) {
                    continue;
                }
                Object objValue = dataMap.get(fldName);
                String value = StrUtil.objToString(objValue, null);
                setValue(obj, fldName, field, value);
            }
        } catch (IllegalAccessException | IntrospectionException | IllegalArgumentException | InvocationTargetException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将value进行类型转换，然后用反射的途径赋值给model
     * @param obj
     * @param fldName
     * @param field
     * @param value
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ParseException
     */
    public static void setValue(Object obj, String fldName, Field field, String value) throws IntrospectionException, InvocationTargetException, IllegalAccessException, ParseException {
        if (value != null) {
            PropertyDescriptor pd = new PropertyDescriptor(fldName, obj.getClass());
            Method method = pd.getWriteMethod();
            Object valueObj = TypeUtil.typeConversion(value, field.getType().getName());
            //field.set(obj, valueObj);
            method.invoke(obj, valueObj);
        }
    }
}
