package com.fuxi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Properties;

public class FanSheLei {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
//        String s = Thread.currentThread().getContextClassLoader().getResource("dayin class.properties").getPath();
//        System.out.println(s);
/*        URL p = FanSheLei.class.getResource("src\\com\\fuxi\\dayin class.properties");
        System.out.println(p == null);*/
        InputStream is = new FileInputStream("src\\com\\fuxi\\dayin class.properties");
        Properties ppt = new Properties();
        ppt.load(is);
        String s = ppt.getProperty("111");
        Class cls = Class.forName(s);
        System.out.println(Modifier.toString(cls.getModifiers())+
                " class "+
                cls.getName()+
                " extends "+
                cls.getSuperclass().getName());
        jiekou(cls);
        ShuXing(cls);
        gouzao(cls);
        fangfa(cls);
        System.out.printf("}");
    }

    public static void gouzao(Class cls){
        Constructor[] ctts = cls.getDeclaredConstructors();
        for (Constructor ctt : ctts) {
            Class cls1 = ctt.getClass();
            System.out.printf("\t"+Modifier.toString(ctt.getModifiers())+
                    " "+ctt.getName()+"(");
            Class[] cls2 = ctt.getParameterTypes();
            for (int i = 0; i < cls2.length; i++) {
                System.out.printf(cls2[i].getSimpleName());
                if(i==cls2.length-1) break;
                System.out.printf(",");
            }
            System.out.println("){}");
        }
        System.out.printf("\n");
    }

    public static void fangfa(Class cls){
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            Class cls1 = method.getClass();
            System.out.print("\t"+Modifier.toString(cls.getModifiers())+
                    " "+method.getReturnType().getSimpleName()+
                    " "+method.getName()+"(");
            Class[] cls2 = method.getParameterTypes();
            for (int i = 0; i < cls2.length; i++) {
                System.out.printf(cls2[i].getSimpleName());
                if(i==cls2.length-1) break;
                System.out.printf(",");
            }
            System.out.println("){}");
        }
    }

    public static void ShuXing(Class cls) throws ClassNotFoundException {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Class cls1 = field.getType();
            System.out.println("\t"+Modifier.toString(field.getModifiers())+" "+
                    cls1.getSimpleName()+" "+
                    field.getName()+";");
        }
        System.out.printf("\n");
    }

    public static void jiekou(Class cls){
        Class[] fu = cls.getInterfaces();
        System.out.print("\t\t\tinterface ");
        for (int i = 0; i < fu.length; i++) {
            if(i!=0 && i %3 == 0) System.out.printf("\n\t\t\t");
            System.out.printf(fu[i].getName());
            if(i==fu.length-1) break;

            System.out.printf(",");
        }
        System.out.println("{");
    }
}

