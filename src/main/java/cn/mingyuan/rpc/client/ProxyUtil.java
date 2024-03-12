package cn.mingyuan.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ProxyUtil {

    public static <T> T getRemoteProxyInstance(Class<T> serviceInterface, InetSocketAddress address){
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),new Class<?>[]{serviceInterface},((proxy,method,args) ->{
            try (Socket socket = new Socket()){
                socket.connect(address);
                try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())){
                    output.writeUTF(serviceInterface.getName());
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);
                    output.flush();
                    try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
                        return input.readObject();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }));
    }

}
