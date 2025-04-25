package eu.gaiax.user.account.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Base64;

@Slf4j
public class JwtUtil {

    public static <T> T readTokenIntoClass(String bearerToken, Class<T> clazz) {
        String token = bearerToken.split("\\s+")[1];
        String tokenBody = token.split("\\.")[1];

        Base64.Decoder decoder = Base64.getDecoder();
        String jsonStr = new String(decoder.decode(tokenBody));
        log.info("jwt: " + jsonStr);
        final Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

     public static JSONObject readTokenIntoJSONObject(String bearerToken) {
        String token = bearerToken.split("\\s+")[1];
        String tokenBody = token.split("\\.")[1];

        Base64.Decoder decoder = Base64.getDecoder();
        String jsonStr = new String(decoder.decode(tokenBody));

        return new JSONObject(jsonStr);
    }

}
