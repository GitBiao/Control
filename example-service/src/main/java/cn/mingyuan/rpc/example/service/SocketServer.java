package cn.mingyuan.rpc.example.service;

import cn.mingyuan.rpc.core.ServiceMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SocketServer {

    @Resource
    private ServiceMapper registry;

    private ServerSocket serverSocket ;

    private ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void start() {
        try {
            this.serverSocket = new ServerSocket(8888);

            System.out.println("Registry Center Start...");

            while (true){
                this.executor.execute(new ServiceTask(this.serverSocket.accept(),registry));
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(this.serverSocket != null) this.serverSocket.close();;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static class ServiceTask implements Runnable{

        private Socket socket;
        private ServiceMapper registry;

        public ServiceTask(Socket socket, ServiceMapper registry){
            this.socket = socket;
            this.registry =registry;
        }

        @Override
        public void run() {
            try (ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream())){
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] paramTypes = (Class<?>[]) input.readObject();
                Object[] arguements = (Object[]) input.readObject();
                Class<?> serviceClass = registry.getRegisterClass(serviceName);
                if(serviceClass == null){
                    throw new ClassNotFoundException(serviceName + "not found.");
                }
                Method method = serviceClass.getMethod(methodName,paramTypes);
                Object result = method.invoke(serviceClass.newInstance(),arguements);

                try (ObjectOutputStream output = new ObjectOutputStream(this.socket.getOutputStream())){
                    output.writeObject(result);
                    output.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
