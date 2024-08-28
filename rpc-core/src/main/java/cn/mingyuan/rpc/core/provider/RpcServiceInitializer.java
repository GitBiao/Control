package cn.mingyuan.rpc.core.provider;

import cn.mingyuan.rpc.core.annotation.RpcProvider;
import java.util.Map;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Data
@Component
public class RpcServiceInitializer  implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public RpcServiceInitializer() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(
                RpcProvider.class);
        for(Object bean : beansWithAnnotation.values()){
            processRpcProvider(bean);
        }
    }

    public void processRpcProvider(Object bean) {
        // cache rpc service impl bean
        RpcServiceRegistry registry = applicationContext.getBean(RpcServiceRegistry.class);
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        for(Class<?> service : interfaces){
            registry.registerService(service.getName(),bean);
        }
    }


}
