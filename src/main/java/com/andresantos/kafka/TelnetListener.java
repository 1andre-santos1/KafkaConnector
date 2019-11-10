package com.andresantos.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZonedDateTime;

/**
 * This class is responsible for the listening process on a given port
 */
public class TelnetListener {
    private static final Logger log = LoggerFactory.getLogger(TelnetListener.class);

    TelnetConnectorConfig config;
    private static int port;
    private static ServerSocket serverSocket;
    private static BufferedReader input;
    private static String message;
    private static String timestamp;
    private static String source;

    /**
     * Instantiates a TelnetListener
     * @param config configuration
     */
    public TelnetListener(TelnetConnectorConfig config) {
        this.config = config;
    }

    /**
     * Starts the listening process on a given port
     * @param port Exposed port for the listening process
     */
    public void Listen(int port) {
        try {
            //the exposed port is assigned to the argument
            this.port = port;
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            //creates a new server socket object denoting which port number the communication will occur
            this.serverSocket = new ServerSocket(this.port);

            // server infinite loop
            while (true) {
                //the server awaits for a connection with the accept() method
                Socket socket = serverSocket.accept();

                //save the connection content to a variable (input)
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                try {
                    //the message attribute of this class now has the content of the message
                    this.message = input.readLine();
                    //gets the source of the message
                    this.source = serverSocket.getLocalSocketAddress().toString();
                    //gets the timestamp of the message
                    this.timestamp = ZonedDateTime.now().toString();

                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Closes the current ServerSocket connection
     */
    public void CloseConnection() throws IOException {
        this.serverSocket.close();
    }

    /**
     * Returns the message attribute of this class
     * @return message
     */
    public String getMessage(){return this.message;}

    /**
     * Returns the timestamp attribute of this class
     * @return timestamp
     */
    public String getTimestamp(){return  this.timestamp;}

    /**
     * Returns the source attribute of this class
     * @return source
     */
    public String getSource(){return this.source;}
}
