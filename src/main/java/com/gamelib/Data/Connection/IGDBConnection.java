package com.gamelib.Data.Connection;


import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.TwitchAuthenticator;
public class IGDBConnection extends DataConnection{

    public static void startConnection(){

    }

    public IGDBConnection() {
        super(
                TwitchAuthenticator.INSTANCE,
                "https://api.igdb.com/v4",
                "4pjszf3hlm27otvqskafizqw6huvpx",
                "oudc8nmufz51kcoxjo1mc4khp8wsts",
                "client_credentials",
                "https://id.twitch.tv/oauth2/token");

    }



    public boolean authenticate() {
        tokenAccess = twitchAuth.requestTwitchToken(client_id, client_secret);
        if (tokenAccess == null) {
            return false;
        }
        setDataAuthentication();
        setCredentials();

        return true;
    }

    public void setDataAuthentication() {

        tokenStr = twitchAuth.getTwitchToken().getAccess_token();
        time_expire = twitchAuth.getTwitchToken().getExpires_in();
        token_type = twitchAuth.getTwitchToken().getToken_type();
        authorization = token_type + " " + tokenStr;
    }


    public void setCredentials() {
        igdbWrapper = IGDBWrapper.INSTANCE;
        igdbWrapper.setCredentials(client_id,tokenStr);
    }


    public boolean getAgainAuthentication() {
        tokenAccess = twitchAuth.getTwitchToken();
        return false;
    }



}
