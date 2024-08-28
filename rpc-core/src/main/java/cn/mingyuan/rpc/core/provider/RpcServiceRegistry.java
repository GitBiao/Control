package cn.mingyuan.rpc.core.provider;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class RpcServiceRegistry {

    private final Map<String,Object> serviceMap = new HashMap<>();

    public void registerService(String name,Object serviceBean){
        serviceMap.put(name,serviceBean);
    }

    public Object getService(String name){
         return serviceMap.get(name);
    }

}
