package com.icss.etc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Sex {

    enum Type {
        Man("男"),
        Woman("女");

        private String genderStr;

        //构造函数
        Type(String arg0) {
            this.genderStr = arg0;
        }


        @Override
        public String toString() {
            return genderStr;
        }
    }

    Type gender() default Type.Man;
}
