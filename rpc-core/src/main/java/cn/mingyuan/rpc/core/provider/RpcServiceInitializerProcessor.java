package cn.mingyuan.rpc.core.provider;

import cn.mingyuan.rpc.core.annotation.RpcProvider;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class RpcServiceInitializerProcessor  implements BeanPostProcessor {

    private final RpcServiceRegistry registry;

    public RpcServiceInitializerProcessor() {
        this.registry = SingletonFactory.getInstance(RpcServiceRegistry.class);
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcProvider.class)) {
            // get RpcService annotation
            RpcProvider rpcProvider = bean.getClass().getAnnotation(RpcProvider.class);
            // build RpcServiceProperties
            if(rpcProvider.serviceName() == null || rpcProvider.serviceName().isEmpty()){
                registry.registerService(beanName,bean);
            }else {
                registry.registerService(rpcProvider.serviceName(),bean);
            }
        }
        return bean;
    }


}
