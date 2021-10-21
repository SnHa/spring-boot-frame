package com.atsun.coreapi.orm.id;

import cn.hutool.core.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

@Slf4j
public class SnowflakeIdGenerator implements IdentifierGenerator, Configurable {

    protected Snowflake snowflake;

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {

        int workId = Integer.parseInt(System.getProperty("dcs.snowflake.work.id", "0"));
        int dataCenterId = Integer.parseInt(System.getProperty("dcs.snowflake.data.center.id", "0"));

        snowflake = new Snowflake(workId, dataCenterId);

        log.info(String.format("Init snowflake id generator end! Work id: %d, Data center id: %d", workId, dataCenterId));
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflake.nextIdStr();
    }

}
