package ejbm.mac.com.picasso;


import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.net.URLEncoder;

public class JsonValidate  extends AsyncTask<String, Void, String> {

    public String object_or_array;
    public boolean empty;
    public long parse_time_nanoseconds;

    @SerializedName("validate")
    public boolean isValid;
    public int size;


    @Override
    protected String doInBackground(String... notUsed) {
        TestPojo tp = new TestPojo();
        Gson gson = new Gson();
        String result = "" ;


        try {
            result = URLEncoder.encode ( gson.toJson ( tp ) , "UTF-8");
            //String url = String.format("%s%s", u , json);
            //result += getStream(url);
        } catch (Exception ex) {
            Log.v("TAG", "Error: " + ex.getMessage());
        }


        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        //tv.setText(result);
    }

}