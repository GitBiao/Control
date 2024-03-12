package cn.mingyuan.rpc.api;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String username;

    public User(String id,String username){
        this.id = id;
        this.username =username;
    }

    public User(){
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
