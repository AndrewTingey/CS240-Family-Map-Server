package Server;

import java.io.*;
import java.net.*;

import Handlers.*;
import Model.Person;
import com.sun.net.httpserver.*;

/*
	This example demonstrates the basic structure of the Family Map Server
	(although it is for a fictitious "Ticket to Ride" game, not Family Map).
	The example is greatly simplfied to help you more easily understand the
	basic elements of the server.
	
	The Server class is the "main" class for the server (i.e., it contains the 
		"main" method for the server program).
	When the server runs, all command-line arguments are passed in to Server.main.
	For this server, the only command-line argument is the port number on which 
		the server should accept incoming client connections.
*/
public class Server {
    //Take CS 340 to know what this is
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // Java provides an HttpServer class
    private HttpServer server;

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        new Server().run(port);
    }

    private void run(int port) {
        System.out.println("Initializing HTTP Server");

        InetSocketAddress serverAddress = new InetSocketAddress(port);

        try {
            server = HttpServer.create(serverAddress, MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        registerHandlers();

        System.out.println("Starting server");

        server.start();

        // Log message indicating that the server has successfully started.
        System.out.println("Server started. Listening on port " + port);
    }

    private void registerHandlers() {
        // Log message indicating that the server is creating and installing
        // its HTTP handlers.
        // The HttpServer class listens for incoming HTTP requests.  When one
        // is received, it looks at the URL path inside the HTTP request, and
        // forwards the request to the handler for that URL path.
        System.out.println("Creating contexts");
        server.createContext("/", new FileHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/fill", new FillHandler()); //this is wrong

    }
}