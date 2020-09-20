package com.xia.jmm.refect;

public class Come {
   private Come come;

   private Integer age=12;
   public  String name="name";
   public  void show(String name,Integer age){
       System.out.println(name+"       "+age);
   }

    public Come getCome() {
        return come;
    }

    public void setCome(Come come) {
        this.come = come;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
