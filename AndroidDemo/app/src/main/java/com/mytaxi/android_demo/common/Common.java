package com.mytaxi.android_demo.common;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.android_demo.stub.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.mytaxi.android_demo.misc.Constants.LOG_TAG;

public class Common {

    public static Map<String, String> getUserCredential() throws IOException {

        String data = null;
        User user = null;

        //JSONObject obj = new JSONObject(json);
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> userDataMap = new HashMap<>();
        URL url = new URL("https://randomuser.me/api/?seed=a1f30d446f820665");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String responseCode = conn.getResponseMessage();

        if (conn.getResponseMessage().equalsIgnoreCase("OK")){
            System.out.println(responseCode);
            Scanner sc = new Scanner(url.openStream());

            while(sc.hasNext())
                data+=sc.nextLine();

            Log.i(LOG_TAG, "\nJSON data in string format: " + data);
            sc.close();

            /* convert JSON string to Map*/
            user = (User) mapper.readValue(data.substring(4), User.class);
            userDataMap.put("username", user.getResults()[0].getLogin().getUsername());
            userDataMap.put("password", user.getResults()[0].getLogin().getPassword());
        } else
            Log.i(LOG_TAG,"FAILED");
        return userDataMap;
    }
}
