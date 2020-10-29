package main.java.handler;

import com.sun.net.httpserver.HttpExchange;
import main.java.request.LoginRequest;
import main.java.response.Response;
import main.java.service.LoginService;

import java.io.*;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class LoginHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("SERVER: /user/login handler");

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Reader in = new InputStreamReader(exchange.getRequestBody());
                LoginRequest request = gson.fromJson(in, LoginRequest.class);

                Response response = LoginService.login(request);

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
