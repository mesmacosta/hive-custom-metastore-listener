package com.medium.hive.metastore.listerner;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hive.metastore.events.AlterTableEvent;
import org.apache.hadoop.hive.metastore.MetaStoreEventListener;
import org.apache.hadoop.hive.metastore.events.CreateTableEvent;

import org.codehaus.jackson.map.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomListener extends MetaStoreEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);
    private static final  ObjectMapper objMapper = new ObjectMapper();

    public CustomListener(Configuration config) {
        super(config);
        logWithHeader(" created ");
    }

    @Override
    public void onCreateTable(CreateTableEvent event) {
        logWithHeader(event.getTable());
    }

    @Override
    public void onAlterTable(AlterTableEvent event) {
        logWithHeader(event.getNewTable());
    }

    private void logWithHeader(Object obj){
        LOGGER.info("[CustomListener][Thread: " + Thread.currentThread().getName()+"] | " + objToStr(obj));
    }

    private String objToStr(Object obj){
        try {
            return objMapper.writeValueAsString(obj);
        } catch (IOException e) {
            LOGGER.error("Error on conversion", e);
        }
        return null;
    }
}
