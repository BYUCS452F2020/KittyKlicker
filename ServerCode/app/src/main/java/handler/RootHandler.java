package main.java.handler;

import com.sun.net.httpserver.HttpExchange;
import main.java.response.Response;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.net.HttpURLConnection.*;

/**
 * Created by jswense2 on 10/27/18.
 */

public class RootHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        System.out.println("SERVER: root handler");

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                String requestPath = exchange.getRequestURI().getPath();
                if (requestPath.length() == 0 || requestPath.equals("/"))
                {
                    requestPath = "/indexx.html";
                }
                File result = new File(System.getProperty("user.dir") + "/web" + requestPath);
                if (result.exists() && result.canRead())
                {
                    exchange.sendResponseHeaders(HTTP_OK, 0);
                    Path path = Paths.get(result.getPath());
                    Files.copy(path, exchange.getResponseBody());
                    success = true;
                }
            }

            if (!success)
            {
                exchange.sendResponseHeaders(HTTP_NOT_FOUND, -1);
            }
        }
        catch (Exception e)
        {
            exchange.sendResponseHeaders(HTTP_BAD_REQUEST, 0);

            Writer out = new OutputStreamWriter(exchange.getResponseBody());

            gson.toJson(new Response(e.getMessage()), out);
            out.close();
        }
        finally
        {
            exchange.getResponseBody().close();
        }

    }
}
