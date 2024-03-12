package cn.mingyuan.rpc.core;

public interface Registry {

    void stop();

    void start();

    void register(Class<?> serviceInterface,Class<?> impl);
}
