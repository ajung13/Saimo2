package ajung13.github.saimo;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "myLog_msg";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.e(TAG, "onMsgReceived called");
    }
}
