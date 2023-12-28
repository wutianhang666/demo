package com.icss.etc.test.strategy;

/**
 * step3:定义环境角色（Context）：把促销活动推送给用户，这里可理解为淘宝平台
 */
public class Context {
    //持有抽象策略的引用
    private Strategy strategy;

    //生成构造方法，让平台根据传入的参数（type）选择促销活动
    public Context(Strategy strategyType) {
        this.strategy = strategyType;
    }

    //向用户展示促销活动
    public void taoPlatformShow(String time) {
        System.out.println(time + "的促销策略是:");
        strategy.show();
    }
}

