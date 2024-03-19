package cn.mingyuan.rpc.core.spring;

import cn.mingyuan.rpc.core.ProxyUtil;
import cn.mingyuan.rpc.core.ServiceMapper;
import cn.mingyuan.rpc.core.ServiceMapperImpl;
import cn.mingyuan.rpc.core.annotation.RPCService;
import cn.mingyuan.rpc.core.annotation.RpcReference;
import cn.mingyuan.rpc.core.factory.SingletonFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;

@Component
public class SpringBeanPostProcessor  implements BeanPostProcessor {

    private final ServiceMapper registry;

    public SpringBeanPostProcessor() {
        this.registry = SingletonFactory.getInstance(ServiceMapperImpl.class);
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RPCService.class)) {
            // get RpcService annotation
            RPCService rpcService = bean.getClass().getAnnotation(RPCService.class);
            // build RpcServiceProperties
            for(Class<?> c : bean.getClass().getInterfaces()){

//                for (Method method : c.getMethods()) {
//                    new ProviderMeta(method.getName(), bean, c.getCanonicalName());
//                }
//
//                Map<String, ProviderMeta> map = new HashMap<>();
//                {
//                    String methodSign;
//                    // methodName!3_java.lang.String_java.lang.Integer
//
//                    Object bean;
//                    String serviceName;
//                    Object[] args;
//                    Class<?> parameterTyps;
//                }
//
//                key : interface;

                registry.register(c,bean.getClass());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
            if (rpcReference != null) {
                Object clientProxy = ProxyUtil.getRemoteProxyInstance(declaredField.getType(),new InetSocketAddress("localhost", 8888));;
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return bean;
    }

}
