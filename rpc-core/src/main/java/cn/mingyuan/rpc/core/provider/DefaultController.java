package cn.mingyuan.rpc.core.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @Autowired
    private RpcServiceRegistry registry;

    @RequestMapping("/request")
    public Object rpcRequest(@RequestBody RpcRequestParam param){
        Object service = registry.getService(param.getService());
        if(service != null){
            try {
                Method declaredMethod = service.getClass()
                        .getDeclaredMethod(param.getMethod(), getParameterType(param));
                return declaredMethod.invoke(service,param.getParam());
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e ) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private Class<?>[] getParameterType(RpcRequestParam param){
        Object[] param1 = param.getParam();
        if(param1!=null && param1.length>0){
            Class<?>[] classes = new Class[param1.length];
            for(int i = 0 ; i< param1.length ; i++){
                classes[i] = param1[i].getClass();
            }
            return classes;
        }
        return null;
    }
}
