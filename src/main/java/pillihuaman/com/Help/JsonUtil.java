package pillihuaman.com.Help;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {

    public static <T> T toObject(String data, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(data, type);
    }

    public static String toString(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}