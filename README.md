# hive-metastore-listener
Example on how to implement a hive metastore listener

## Build jar with dependencies
```bash
mvn assembly:assembly
```

## Register your hook in Hive (Permanently, stays even if you restart)
```bash
# Open the file
vi /opt/hive/conf/hive-env.sh

# At the end of the file, add the line and save:
export HIVE_AUX_JARS_PATH=/hive-metastore-listener-1.0-SNAPSHOT-jar-with-dependencies.jar

# Open the file
vi /opt/hive/conf/hive-site.xml

# Add the tag before the closing configuration tag </configuration> and save:
<property><name>hive.metastore.event.listeners</name><value>com.medium.hive.metastore.listerner.CustomListener</value></property>

# Restart your Hive Metastore
```
