package com.michael.demo.designmodel.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * 动态代理类
 *
 * @author Michael
 */
public class ProxyFactory {

    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     */
    public Object getProxyInstance() {

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    before();
                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    after();
                    return returnValue;
                }
        );
    }

    private void before() {
        System.out.println("开始事务...");
    }

    private void after() {
        System.out.println("提交事务...");
    }
}
