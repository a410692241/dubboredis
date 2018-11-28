package com.wei.service.bo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Awake
 * @time 2018/9/21 14:33
 */
public class BaseBO<T> {
    private Integer total;
    private List<T> rows;
    private Class aClass;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        Class<? extends BaseBO> aClass = this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        StringBuilder str = new StringBuilder();
        str.append("[");

        for (Field field : declaredFields) {
            /*授权*/
            field.setAccessible(true);
            try {
                str.append(field.getName() + "=" + field.get(this) + ";");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        str.append("]");
        return str.toString();
    }

    public String fieldCtrol() throws NoSuchFieldException, IllegalAccessException {

        aClass = this.getClass();

        /*只显示public修饰的部分*/
        Field[] fields = aClass.getFields();

        /*set属性*/
        Field nameField = aClass.getDeclaredField("name");
        //打破封装
        nameField.setAccessible(true);
        nameField.set(this, "huwei");
        nameField.get(this);

        return null;
    }

    public void methodCtrol() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        aClass = this.getClass();

        /*显示当前类的所有方法,不包含父类*/
        Field[] declaredFields = aClass.getDeclaredFields();

        /*显示所有public方法*/
        Method[] methods = aClass.getMethods();

        /*显示当前对象的所有公共方法,不包含父类*/
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Method method = aClass.getMethod("toString");

        /*执行该方法*/
        String result = (String) method.invoke(this, new Object() {
        });
        System.out.println(result);

        //当前类不存在toString方法
        /*aClass.getDeclaredMethod("toString");*/

    }



}
