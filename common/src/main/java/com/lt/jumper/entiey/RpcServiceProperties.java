package com.lt.jumper.entiey;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpcServiceProperties {
    private String version;
    private String group;
    private String serviceName;

    public String toRpcServiceName(){
        return this.serviceName + "/" + group + "/" + version;
    }
}
