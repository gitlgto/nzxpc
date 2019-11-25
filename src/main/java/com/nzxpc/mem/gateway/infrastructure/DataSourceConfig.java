package com.nzxpc.mem.gateway.infrastructure;

import com.nzxpc.handler.util.db.DataSourceConfigBase;
import com.nzxpc.handler.web.entity.Button;
import com.nzxpc.mem.entity.trade.Worker;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig extends DataSourceConfigBase {
    @Override
    protected Class[] setEntityPackages() {
        return new Class[]{Button.class, Worker.class};
    }
}
