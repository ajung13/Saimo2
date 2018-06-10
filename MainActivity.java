package ajung13.github.saimo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog_main";
    EditText msgInput;
    TextView msgOutput;

    String regId;
    RequestQueue queue;

    private AddMemoDialog memoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgInput = (EditText)findViewById(R.id.mainEditText);
        msgOutput = (TextView)findViewById(R.id.mainText1);

        queue = Volley.newRequestQueue(getApplicationContext());
        getRegistrationId();
    }

    @Override
    protected void onNewIntent(Intent intent){
        Log.e(TAG, "onNewIntent called");
        if(intent != null){
            processIntent(intent);
        }
        super.onNewIntent(intent);
    }

    public void btnClicked(View v){
        memoDialog = new AddMemoDialog(this);
        memoDialog.show();
    }

    public void getRegistrationId(){
        Log.e(TAG, "getRegistrationId called");

        regId = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "regID : " + regId);
    }

    public void send(String input){
        JSONObject requestData = new JSONObject();
        try{
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);

            JSONArray idArray = new JSONArray();
            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        }catch(JSONException jsone){
            Log.e(TAG, "json error: " + jsone.getMessage());
        }catch(Exception e){
            Log.e(TAG, e.toString());
        }

        sendData(requestData, new SendResponseListener() {
            @Override
            public void onRequestStarted() {
                Log.e(TAG, "onrequestStarted called");
            }
            @Override
            public void onRequestCompleted() {
                Log.e(TAG, "onrequestCompleted called");
            }
            @Override
            public void onRequestWithError(VolleyError error) {
                Log.e(TAG, "onrequestWithError called");
            }
        });
    }

    private void processIntent(Intent intent){
        String from = intent.getStringExtra("from");
        if(from == null){
            Log.e(TAG, "from is null");
            return;
        }
        String contents = intent.getStringExtra("contents");
        Log.e(TAG, "DATA : " + from + ", " + contents);
        msgOutput.setText("[" + from + "] " + contents);
    }


    // interfaces
    public interface SendResponseListener{
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "key=AAAAtn2jpwY:APA91bEQ-5Igwbnxom3kJ9aoxd-QihB3ULbcWLdPChj6aGnIxLe7rzESoxXlaM8IvW81hnfkL5_elFrFnN75QqlboCbyHJwcsNznsIO_K8O9TBC3XcbQtcG8sW4lloEC-qniJhqKCwiI");
                return headers;
            }
            @Override
            public String getBodyContentType(){
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        queue.add(request);
    }
}
