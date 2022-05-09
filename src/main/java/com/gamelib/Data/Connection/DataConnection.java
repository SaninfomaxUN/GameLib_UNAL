package com.gamelib.Data.Connection;

import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;

public abstract class DataConnection {

    //Atributos de Acceso/Autorización
    protected final String client_id;
    protected final String client_secret;
    protected final String grant_type;
    protected String URL_API_TOKEN;
    protected TwitchAuthenticator twitchAuth;
    protected TwitchToken tokenAccess;
    protected String tokenStr;
    protected Long time_expire;
    protected String token_type;

    //Atributos de Consulta
    protected final String URL_API;
    protected String authorization;
    protected IGDBWrapper igdbWrapper;

    protected DataConnection(TwitchAuthenticator twitchAuth, String url_api, String client_id, String client_secret, String grant_type, String url_api_token) {
        this.twitchAuth = twitchAuth;
        this.URL_API = url_api;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.grant_type = grant_type;
        this.URL_API_TOKEN = url_api_token;
    }




}
