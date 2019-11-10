package com.andresantos.kafka;

import org.apache.kafka.common.config.ConfigDef;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.andresantos.kafka.TelnetConnectorConfig.*;

public class TelnetConnectorConfigTest {

  private ConfigDef configDef = TelnetConnectorConfig.conf();

  private Map<String,String> initialConfig(){
    Map<String,String> baseProps = new HashMap<>();
    baseProps.put(TOPIC_CONFIG,"message");
    baseProps.put(PAYLOAD_CONFIG,"Hello World");
    baseProps.put(TIMESTAMP_CONFIG,"2017-04-26T01:23:45Z");
    baseProps.put(SOURCE_CONFIG,"");
    return baseProps;
  }

  @Test
  public void doc() {
    System.out.println(TelnetConnectorConfig.conf().toRst());
  }

  @Test
  public void initialConfigIsValid(){
    assert(configDef.validate(initialConfig())
            .stream()
            .allMatch(configValue -> configValue.errorMessages().size() == 0));
  }

  @Test
  public void canReadConfigCorrectly(){
    TelnetConnectorConfig config = new TelnetConnectorConfig(initialConfig());
  }
}