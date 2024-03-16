package cn.mingyuan.core;

public interface Registry {

    void stop();

    void start();

    void register(Class<?> serviceInterface,Class<?> impl);
}
