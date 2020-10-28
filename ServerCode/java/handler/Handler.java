package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import javax.xml.validation.Validator;

/**
 * Created by jswense2 on 10/27/18.
 */

public abstract class Handler implements HttpHandler
{
    Gson gson = new Gson();
}
