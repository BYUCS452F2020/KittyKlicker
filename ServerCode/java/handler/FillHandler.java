package handler;

import android.annotation.TargetApi;

import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import model.User;
import request.FillRequest;
import response.Response;
import service.ClearService;
import service.FillService;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by jswense2 on 10/27/18.
 */

public class FillHandler extends Handler {

    private String username;
    private int generations;

    private static final int DEFAULT_GENERATIONS = 4;

    @Override
    @TargetApi(26)
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        System.out.println("SERVER: /fill handler");

        try
        {
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                username = null;
                generations = 0;
                String path = exchange.getRequestURI().getPath();
                String[] parameters = path.split("/");
                // (0)/(1)fill/(2)[username]
                if (parameters.length == 3)
                {
                    username = parameters[2];
                    generations = DEFAULT_GENERATIONS;
                }
                // (0)/(1)fill/(2)[username]/(3){generations}
                else if (parameters.length >= 4)
                {
                    username = parameters[2];
                    try
                    {
                        generations = Integer.parseInt(parameters[3]);
                    }
                    catch (Exception e)
                    {
                        throw new Exception("Generations must be an integer.");
                    }
                }
                else
                {
                    throw new Exception("Invalid Parameters");
                }

                Response response = FillService.fillResponse(username, generations);

                exchange.sendResponseHeaders(HTTP_OK, 0);

                Writer out = new OutputStreamWriter(exchange.getResponseBody());

                gson.toJson(response, out);
                out.close();

                success = true;
            }

            if (!success)
            {
                exchange.sendResponseHeaders(HTTP_NOT_FOUND, -1);
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
