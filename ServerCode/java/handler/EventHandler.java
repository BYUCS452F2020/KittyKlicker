package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import request.EventRequest;
import response.Response;
import service.AuthService;
import service.EventService;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class EventHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /event handler");

        boolean success = false;

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                Headers reqHeaders = exchange.getRequestHeaders();
                String authToken = reqHeaders.getFirst("Authorization");

                String path = exchange.getRequestURI().getPath();
                String[] parameters = path.split("/");
                EventRequest request = null;
                // (0)/(1)event
                if (parameters.length == 2)
                {
                    request = new EventRequest(authToken);
                }
                // (0)/(1)event/(2)eventID
                else if (parameters.length == 3)
                {
                    request = new EventRequest(parameters[2], authToken);
                }
                else
                {
                    throw new Exception("Invalid Parameters");
                }

                Response response = EventService.getEvent(request);

                exchange.sendResponseHeaders(HTTP_OK, 0);

                Writer out = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(response, out);
                out.close();

                success = true;
            }

            if (!success)
            {
                exchange.sendResponseHeaders(HTTP_OK, 0);

                Writer out = new OutputStreamWriter(exchange.getResponseBody());

                gson.toJson(new Response("Incorrect Request Method"), out);
                out.close();
            }
        }
        catch (Exception e)
        {
            exchange.sendResponseHeaders(HTTP_OK, 0);

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
