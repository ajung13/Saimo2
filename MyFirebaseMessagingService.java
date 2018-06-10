package ajung13.github.saimo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "myLog_msg";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.e(TAG, "onMsgReceived called");

        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        String contents = data.get("contents");
        Log.e(TAG, "from : " + from + ", contents : " + contents);

        sendToActivity(getApplicationContext(), from, contents);
    }

    private void sendToActivity(Context context,String from, String contents){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);

        context.startActivity(intent);
    }
}
