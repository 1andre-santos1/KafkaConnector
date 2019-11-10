package com.andresantos.kafka;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;

import java.time.ZonedDateTime;
import java.util.Map;

public class TelnetConnectorConfig extends AbstractConfig {

  public static final String TOPIC_CONFIG = "topic";
  private static final String TOPIC_DOC = "Topic to write to";

  public static final String PAYLOAD_CONFIG = "message.content";
  private static final String PAYLOAD_DOC = "Content of the received message";

  public static final String TIMESTAMP_CONFIG = "message.timestamp";
  private static final String TIMESTAMP_DOC = "Timestamp of the received message";

  public static final String SOURCE_CONFIG = "message.source";
  private static final String SOURCE_DOC = "Telnet source of the received message";

  public TelnetConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  public TelnetConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
            .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC)
            .define(PAYLOAD_CONFIG, Type.STRING, Importance.HIGH, PAYLOAD_DOC)
            .define(TIMESTAMP_CONFIG, Type.STRING,ZonedDateTime.now().toInstant().toString(),Importance.HIGH,TIMESTAMP_DOC)
            .define(SOURCE_CONFIG,Type.STRING,Importance.HIGH,SOURCE_DOC);
  }

  public String getTopic(){
    return this.getString(TOPIC_CONFIG);
  }
  public String getPayload() {return this.getString(PAYLOAD_CONFIG);}
  public String getTimestamp(){return this.getString(TIMESTAMP_CONFIG);}
  public String getSource(){return this.getString(SOURCE_CONFIG);}
}
