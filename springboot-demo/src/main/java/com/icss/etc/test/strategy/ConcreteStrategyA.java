package com.icss.etc.test.strategy;


//step2:定义具体策略角色（Concrete Strategy）：不同类型的具体促销策略
//618大促活动 A
public class ConcreteStrategyA implements Strategy {
    @Override
    public void show() {
        System.out.println("618大促");
    }
}
