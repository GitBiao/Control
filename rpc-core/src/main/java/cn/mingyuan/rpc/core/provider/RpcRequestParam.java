package cn.mingyuan.rpc.core.provider;

import lombok.Data;

@Data
public class RpcRequestParam {

    private String service;
    private String method;
    private Object[] param;
}
