package cn.mingyuan.rpc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RpcProvider {
    String serviceName() default "";
}
