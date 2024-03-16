package cn.mingyuan.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistryCenter implements Registry{

    private ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static Map<String,Class> serviceRegistry = new HashMap<>();

    private int port;

    private ServerSocket serverSocket;

    public RegistryCenter(int port){
        this.port = port;
    }

    @Override
    public void stop() {
        this.executor.shutdown();
        if(this.serverSocket != null){
            try {
                this.serverSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start() {
        try {
            this.serverSocket = new ServerSocket(this.port);

            System.out.println("Registry Center Start...");

            while (true){
                this.executor.execute(new ServiceTask(this.serverSocket.accept()));
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

    @Override
    public void register(Class<?> serviceInterface, Class<?> impl) {
        this.serviceRegistry.put(serviceInterface.getName(),impl);
    }

    static class ServiceTask implements Runnable{

        private Socket socket;

        public ServiceTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream())){
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] paramTypes = (Class<?>[]) input.readObject();
                Object[] arguements = (Object[]) input.readObject();
                Class<?> serviceClass = serviceRegistry.get(serviceName);
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
