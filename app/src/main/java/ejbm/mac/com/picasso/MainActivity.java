package ejbm.mac.com.picasso;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    ImageView imageView ;
    TextView tv1, tv2, tv3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        tv1 = (TextView ) findViewById( R.id.tv1 );
        tv2 = (TextView ) findViewById( R.id.tv2 );
        tv3 = (TextView ) findViewById( R.id.tv3 );

    }

    public void loadIMG (View v ) {
        String url = "" + tv1.getText() ;

        if ( url == null || url.equals("") )
            url = "http://oyster.ignimgs.com/mediawiki/apis.ign.com/futurama/thumb/f/fb/CostumeContest.jpg/228px-CostumeContest.jpg" ;

        Picasso.with(this)
                .load( /* "https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg"*/
                        url
                )
                .into(imageView);
    }


    public void serialize (View v ) {
        Serialization s = new Serialization ( ) ;
        s.execute(null, null, null) ;
    }

    public void deserialize (View v ) {

        Deserialization s = new Deserialization ( new TestPojo ( ) ) ;
        s.execute( tv3.getText() + "" , null, null ) ;
    }


    private class Serialization  extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... notUsed) {
            TestPojo tp = new TestPojo();
            Gson gson = new Gson();
            String result = "" ;


            try {
                result = /*URLEncoder.encode(*/ gson.toJson(tp) /*, "UTF-8")*/ ;
                //String url = String.format("%s%s", u , json);
                //result += getStream(url);
            } catch (Exception ex) {
                Log.v("TAG", "Error: " + ex.getMessage());
            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            tv2.setText(result);
        }

    }

    private class Deserialization  extends AsyncTask<String, Void, String> {
        TestPojo tp = null ;

        public Deserialization( TestPojo t ) {
            this.tp = t ;
        }

        @Override
        protected String doInBackground(String... notUsed) {

            Gson gson = new Gson();
            String result = "" ;

            try {
                result = notUsed [0] + " ==> " + gson.fromJson( notUsed [0] , TestPojo.class ).toString() ;
                //String url = String.format("%s%s", u , json);
                //result += getStream(url);
            } catch (Exception ex) {
                Log.v("TAG", "Error: " + ex.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            tv3.setText(result);
        }

    }
}
