package handler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import response.Response;
import service.ClearService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class ClearHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /clear handler");

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Response response = ClearService.clear();

                exchange.sendResponseHeaders(HTTP_OK, 0); // -1 for no body

                Writer out = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(response, out);
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
