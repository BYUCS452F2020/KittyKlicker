package handler;

import com.sun.net.httpserver.HttpExchange;
import request.RegisterRequest;
import response.Response;
import service.RegisterService;

import java.io.*;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class RegisterHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /user/register handler");

        try
        {
            boolean success = false;
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Reader in = new InputStreamReader(exchange.getRequestBody());
                RegisterRequest request = gson.fromJson(in, RegisterRequest.class);

                Response response = RegisterService.register(request);

                exchange.sendResponseHeaders(HTTP_OK, 0); // -1 for no body

                Writer out = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(response, out);
                out.close();
                success = true;
            }

            if (!success)
            {
                exchange.sendResponseHeaders(HTTP_OK, 0); // -1 for no body

                Writer out = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(new Response("Unsuccessful"), out);
                out.close();
            }
        }
        catch (Exception e)
        {
            exchange.sendResponseHeaders(HTTP_OK, 0); // -1 for no body

            Writer out = new OutputStreamWriter(exchange.getResponseBody());
            gson.toJson(new Response(e.getMessage()), out);
            out.close();
        }
    }
}
