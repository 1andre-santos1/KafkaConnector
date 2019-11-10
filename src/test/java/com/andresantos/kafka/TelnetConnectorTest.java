package com.andresantos.kafka;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static com.andresantos.kafka.TelnetConnectorConfig.*;

public class TelnetConnectorTest {

  private Map<String, String> initialConfig() {
    Map<String, String> baseProps = new HashMap<>();
    baseProps.put(TOPIC_CONFIG, "message");
    baseProps.put(PAYLOAD_CONFIG, "Hello World");
    baseProps.put(TIMESTAMP_CONFIG,"2017-04-26T01:23:45Z");
    baseProps.put(SOURCE_CONFIG,"");
    return (baseProps);
  }

  @Test
  public void taskConfigsShouldReturnOneTaskConfig() {
    TelnetConnector telnetConnector = new TelnetConnector();
    telnetConnector.start(initialConfig());
    assertEquals(telnetConnector.taskConfigs(1).size(),1);
    assertEquals(telnetConnector.taskConfigs(10).size(),1);
  }
}
