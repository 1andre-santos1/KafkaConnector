package com.andresantos.kafka;

import com.andresantos.kafka.model.Message;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import static com.andresantos.kafka.TelnetConnectorSchemas.*;

public class TelnetConnectorTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(TelnetConnectorTask.class);
  public TelnetConnectorConfig config;

  TelnetListener telnetListener;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  /**
   * Initializes this task
   * @param map
   */
  @Override
  public void start(Map<String, String> map) {
    //assigns the configurations to the TelnetConnectorConfig configurations
    config = new TelnetConnectorConfig(map);

    //create an instance of the telnet listener, with the configurations
    telnetListener = new TelnetListener(config);

    //starts the listening process on the port
    telnetListener.Listen(5050);
  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    //List of SourceRecord objects that will be sent the kafka cluster.
    final ArrayList<SourceRecord> records = new ArrayList<>();

    //creates a new message given the current state of the TelnetListener
    Message message = new Message();
    message.setPayload(this.telnetListener.getMessage());
    message.setTimestamp(this.telnetListener.getTimestamp());
    message.setSource(this.telnetListener.getSource());

    //generate a source record with the message
    SourceRecord sourceRecord = generateSourceRecord(message);

    //add this message to the records list
    records.add(sourceRecord);

    return records;
  }

  /**
   * Generates a source record given the message
   * @param telnetMessage Message
   * @return SourceRecord for the message
   */
  private SourceRecord generateSourceRecord(Message telnetMessage) {
    return new SourceRecord(sourcePartition(),
            sourceOffset(),
            config.getTopic(),
            Schema.STRING_SCHEMA,
            telnetMessage);
  }

  /**
   * Stops the current listening process
   */
  @Override
  public void stop() {
    //closes the telnet listener connection
    try {
      telnetListener.CloseConnection();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates a map for the source partition containing the payload (content of the message)
   * @return Map
   */
  private Map<String, String> sourcePartition() {
    Map<String, String> map = new HashMap<>();
    map.put(PAYLOAD_FIELD, config.getPayload());
    return map;
  }

  /**
   * Generates a map for the source offset containing the source and timestamp of the message
   * @return Map
   */
  private Map<String, String> sourceOffset() {
    Map<String, String> map = new HashMap<>();
    map.put(TIMESTAMP_FIELD, config.getTimestamp());
    map.put(SOURCE_FIELD, config.getSource());
    return map;
  }
}