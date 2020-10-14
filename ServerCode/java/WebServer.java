
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

import com.google.gson.Gson;
import java.io.*;

import handler.ClearHandler;
import handler.EventHandler;
import handler.FillHandler;
import handler.LoadHandler;
import handler.LoginHandler;
import handler.PersonHandler;
import handler.RegisterHandler;
import handler.RootHandler;


/**
 * Created by jswense2 on 10/16/18.
 */

public class WebServer {

    Gson gson = new Gson();

    public static void main(String[] args)
    {
        try
        {
            WebServer server = new WebServer();
            int portNumber = Integer.parseInt(args[0]);
            server.run(portNumber);
        }
        catch (Exception e)
        {
            System.out.println("error: " + e.getMessage());
        }
    }

    private HttpServer server;

    void run(int port)
    {
        System.out.println("server listening on port: " + port);
        System.out.println();

        try
        {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        System.out.println();

        server.createContext("/", new RootHandler());

        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());

        server.start();

        System.out.println("Server started");
        System.out.println();

    }
}
