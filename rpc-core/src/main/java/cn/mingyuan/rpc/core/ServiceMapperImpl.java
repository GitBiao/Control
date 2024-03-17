package cn.mingyuan.rpc.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceMapperImpl implements ServiceMapper {
    private static Map<String,Class<?>> serviceRegistry = new HashMap<>();

    @Override
    public void register(Class<?> serviceInterface, Class<?> impl) {
        this.serviceRegistry.put(serviceInterface.getName(),impl);
    }

    @Override
    public Class<?> getRegisterClass(String serviceName) {
        return serviceRegistry.get(serviceName);
    }

}
