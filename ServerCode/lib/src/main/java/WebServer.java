import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import handler.ClearHandler;
import handler.KlickHandler;
import handler.LoginHandler;


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
            // uncomment this to set port number from command line args
            //int portNumber = Integer.parseInt(args[0]);
            int portNumber = 80;
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

        //server.createContext("/", new RootHandler());

        //server.createContext("/user/register", new RegisterHandler());
        server.createContext("/login-register", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/klick", new KlickHandler());

        server.start();

        System.out.println("Server started");
        System.out.println();

    }
}
