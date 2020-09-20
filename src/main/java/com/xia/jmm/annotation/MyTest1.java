package com.xia.jmm.annotation;

import com.sun.deploy.security.BadCertificateDialog;
import com.xia.jmm.controller.ComeController;
import com.xia.jmm.refect.Come;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class MyTest1 {
    public static void main(String[] args)  {
        ComeController comeController=new ComeController();
         Class<? extends ComeController> clazz=comeController.getClass();
        Come come=new Come();
        Stream.of(clazz.getDeclaredFields()).forEach(field -> { //lomba表达式
            String name=field.getName();
            ResourceCome resourceCome=field.getAnnotation(ResourceCome.class);
            if(resourceCome!=null){
                field.setAccessible(true);
                Class<?> type=field.getType();
                try {
                    Object obj=type.newInstance();
                    field.set(comeController,obj);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(comeController.getCome());
    }
    public  static void add(){
        List<Integer> list=new LinkedList<>();
        String string="sdasdsadas";
        char[] chars=string.toCharArray();
        Integer.valueOf(chars[1]);//获取ASCLL码
        Stream.of(chars).forEach(chars1 -> {
            list.add(Integer.valueOf('s'));
        });
    }
}
