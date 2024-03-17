package cn.mingyuan.rpc.core;

public interface ServiceMapper {

    void register(Class<?> serviceInterface,Class<?> impl);

    Class<?> getRegisterClass(String serviceName);
}
