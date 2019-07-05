package uit.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import uit.modelview.GooglePojo;

public class GoogleUtil {
    public static String getToken(final String code) throws ClientProtocolException, IOException {
        // Google constants.
        String GOOGLE_LINK_GET_TOKEN = PropertiesUtil.getProperties().getProperty("GOOGLE_LINK_GET_TOKEN");
        String GOOGLE_CLIENT_ID = PropertiesUtil.getProperties().getProperty("GOOGLE_CLIENT_ID");
        String GOOGLE_CLIENT_SECRET = PropertiesUtil.getProperties().getProperty("GOOGLE_CLIENT_SECRET");
        String GOOGLE_REDIRECT_URI = PropertiesUtil.getProperties().getProperty("GOOGLE_REDIRECT_URI");
        String GOOGLE_GRANT_TYPE = PropertiesUtil.getProperties().getProperty("GOOGLE_GRANT_TYPE");
        
        // Request to google get info.
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        
        return accessToken;
    }
    public static GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = PropertiesUtil.getProperties().getProperty("GOOGLE_LINK_GET_USER_INFO") + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
        System.out.println(googlePojo);
        
        return googlePojo;
    }
}
