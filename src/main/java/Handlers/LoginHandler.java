package Handlers;

import Requests.LoginRequest;

public class LoginHandler {
    //LoginRequest request = (LoginRequest) gson.fromJson(reqData, LoginRequest.class);
}

/*
LoginRequest request = (LoginRequest)gson.fromJson(reqData, LoginRequest.class);

LoginService service = new LoginService();
LoginResult result = service.login(request);

exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
gson.toJson(result, resBody);
resBody.close();
 */