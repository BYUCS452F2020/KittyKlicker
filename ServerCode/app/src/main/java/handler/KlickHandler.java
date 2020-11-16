package handler;

import com.sun.net.httpserver.HttpExchange;
import request.KlickRequest;
import response.Response;
import service.KlickService;

import java.io.*;

import static java.net.HttpURLConnection.HTTP_OK;

public class KlickHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /klick handler");

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Reader in = new InputStreamReader(exchange.getRequestBody());
                KlickRequest request = gson.fromJson(in, KlickRequest.class);

                Response response = KlickService.klick(request);

                exchange.sendResponseHeaders(HTTP_OK, 0);

                Writer out = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(response, out);
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
    }
}
