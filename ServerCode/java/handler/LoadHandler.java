package handler;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import request.LoadRequest;
import response.Response;
import service.ClearService;
import service.LoadService;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class LoadHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /load handler");

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Reader in = new InputStreamReader(exchange.getRequestBody());
                LoadRequest request = gson.fromJson(in, LoadRequest.class);

                Response response = LoadService.load(request);

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
