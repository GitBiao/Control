package cn.mingyuan.rpc.core.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcServiceInitializer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public RpcServiceRegistry rpcServiceRegistry() {
        return new RpcServiceRegistry();
    }

    @Bean
    public void initRpcServices(RpcServiceRegistry registry) {

    }

    private Object getBeanForService(Class<?> serviceInterface) {
        // From Spring Context get Bean
        return applicationContext.getBean(serviceInterface);
    }


}
