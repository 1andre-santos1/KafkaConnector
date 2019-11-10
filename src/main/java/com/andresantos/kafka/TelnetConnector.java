package com.andresantos.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Source connector for the Telnet Listening process
 */
public class TelnetConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(TelnetConnector.class);
  private TelnetConnectorConfig config;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  /**
   * Initializes this connector given a map of the configurations
   * @param map
   */
  @Override
  public void start(Map<String, String> map) {
    //set up the connector configuration
    config = new TelnetConnectorConfig(map);
  }

  /**
   * Returns this connector task class
   * @return
   */
  @Override
  public Class<? extends Task> taskClass() {
    return TelnetConnectorTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
    // Define the individual task configurations that will be executed.
    ArrayList<Map<String, String>> configs = new ArrayList<>(1);
    configs.add(config.originalsStrings());
    return configs;
  }

  @Override
  public void stop() {
    //Do things that are necessary to stop your connector.
  }

  @Override
  public ConfigDef config() {
    return TelnetConnectorConfig.conf();
  }
}
