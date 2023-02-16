package com.pca00168.eat;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
public interface  WebAPICallback {
    void success(JSONObject item) throws JSONException;
    void fail(IOException e);
}
