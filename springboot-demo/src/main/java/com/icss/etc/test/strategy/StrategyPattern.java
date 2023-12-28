package com.icss.etc.test.strategy;

import org.junit.Test;

public class StrategyPattern {

    /**
     * 测试策略模式
     */
    @Test
    public void test() {

        Context context;

        String time3 = "6月";
        context = new Context(new ConcreteStrategyA());
        context.taoPlatformShow(time3);

        String time1 = "9月";
        context = new Context(new ConcreteStrategyB());
        context.taoPlatformShow(time1);

        String time2 = "11月";
        context = new Context(new ConcreteStrategyC());
        context.taoPlatformShow(time2);
    }
}
