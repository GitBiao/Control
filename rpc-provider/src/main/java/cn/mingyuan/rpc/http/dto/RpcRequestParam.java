package cn.mingyuan.rpc.http.dto;

import lombok.Data;

@Data
public class RpcRequestParam {

    private String service;
    private String method;
    private Object[] param;
}
