package com.andresantos.kafka;

import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Schema;

public class TelnetConnectorSchemas {
    // Message fields
    public static String PAYLOAD_FIELD = "message.content";
    public static String TIMESTAMP_FIELD = "message.timestamp";
    public static String SOURCE_FIELD = "message.source";

    public static String SCHEMA_KEY = "Message Key";
    public static String SCHEMA_VALUE_MESSAGE = "Message";

    public static Schema MESSAGE_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_MESSAGE)
            .version(1)
            .field(PAYLOAD_FIELD, Schema.STRING_SCHEMA)
            .field(TIMESTAMP_FIELD,Schema.STRING_SCHEMA)
            .field(SOURCE_FIELD,Schema.STRING_SCHEMA)
            .build();
}
