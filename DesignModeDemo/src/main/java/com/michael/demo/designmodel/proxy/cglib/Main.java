package com.michael.demo.designmodel.proxy.cglib;

/**
 * 测试调用类 - 静态代理
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();
        //代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法
        proxy.save();
    }
}
