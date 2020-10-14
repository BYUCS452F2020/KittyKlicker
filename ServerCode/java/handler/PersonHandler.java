package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import request.PersonRequest;
import response.PersonResponse;
import response.Response;
import service.AuthService;
import service.PersonService;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class PersonHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /person handler");

        boolean success = false;

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                Headers reqHeaders = exchange.getRequestHeaders();
                String authToken = reqHeaders.getFirst("Authorization");

                String path = exchange.getRequestURI().getPath();
                String[] parameters = path.split("/");
                PersonRequest request = null;
                // (0)/(1)person
                if (parameters.length == 2)
                {
                    request = new PersonRequest(authToken);
                }
                // (0)/(1)person/(2)personID
                else if (parameters.length == 3)
                {
                    request = new PersonRequest(authToken, parameters[2]);
                }
                else
                {
                    throw new Exception("Invalid Parameters");
                }

                Response response = PersonService.getPerson(request);

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
