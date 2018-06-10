package ajung13.github.saimo;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceService extends FirebaseInstanceIdService {
    private static final String TAG = "myLog_instance";

    @Override
    public void onTokenRefresh(){
        Log.e(TAG, "onTokenRefresh called");

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token : " + refreshedToken);
    }
}
