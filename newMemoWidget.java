package ajung13.github.saimo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

public class newMemoWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
        {
            Bundle extras = intent.getExtras();
            //Bundle 은 Key-Value 쌍으로 이루어진 일종의 해쉬맵 자료구조
            //한 Activity에서 Intent 에 putExtras로 Bundle 데이터를 넘겨주고,
            //다른 Activity에서 getExtras로 데이터를 참조하는 방식입니다.
            if(extras!=null)
            {
                int [] appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);
                if(appWidgetIds!=null && appWidgetIds.length>0)
                    this.onUpdate(context,AppWidgetManager.getInstance(context),appWidgetIds);
            }
        }//업데이트인 경우
        else if(action.equals("Click1"))
        {
            AddMemoDialog memoDialog = new AddMemoDialog(context);
            memoDialog.show();
        }
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = buildViews(context);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    //Click1 이라는 Action을 onReceive로 보낸다.
    private PendingIntent buildToastIntent(Context context)
    {
        Intent in = new Intent("Click1");
        PendingIntent pi = PendingIntent.getBroadcast(context,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }

    //위젯에 멀티 버튼 추가하기
    private RemoteViews buildViews(Context context)
    {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_memo_widget);
//        views.setOnClickPendingIntent(R.id.simple_btn,buildURIIntent(context));
        views.setOnClickPendingIntent(R.id.simpleWidget,buildToastIntent(context));

        return views;
    }
}