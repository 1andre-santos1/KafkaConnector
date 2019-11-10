package com.andresantos.kafka.model;

/**
 * Blueprint model of the message
 */
public class Message {
    //content of the message
    private String payload;
    private String timestamp;
    //source address of the message
    private String source;

    public Message(){

    }

    /**
     * Generates a message given its payload,timestamp and source address
     * @param payload content of the message
     * @param timestamp timestamp of the message
     * @param source source address of the message
     */
    public Message(String payload,String timestamp, String source){
        this.payload = payload;
        this.timestamp = timestamp;
        this.source = source;
    }

    public String getPayload(){return this.payload;}
    public String getTimestamp(){return this.timestamp;}
    public String getSource(){return this.source;}

    public void setPayload(String payload){this.payload = payload;}
    public void setTimestamp(String timestamp){this.timestamp = timestamp;}
    public void setSource(String source){this.source = source;}

    public Message withPayload(String payload){
        this.payload = payload;
        return this;
    }
    public Message withTimestamp(String timestamp){
        this.timestamp = timestamp;
        return this;
    }
    public Message withSource(String source){
        this.source = source;
        return this;
    }
}
