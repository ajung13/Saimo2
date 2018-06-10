package ajung13.github.saimo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class newMemoWidget extends AppWidgetProvider {
    public static final String ACTION_CLICK = "CLICK";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_memo_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            RemoteViews views = buildViews(context);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private RemoteViews buildViews(Context context){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_memo_widget);
        views.setOnClickPendingIntent(R.id.addMemoButton, dialogIntent(context));
        return views;
    }
    private PendingIntent dialogIntent(Context context){
        Intent in = new Intent("Click1");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.e("myLog_widget", "onReceive called: " + action);

        if (action != null){
/*            if(action.equals(ACTION_CLICK)) {
                int id = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
                updateAppWidget(context, AppWidgetManager.getInstance(context), id);   // 버튼이 클릭되면 새로고침 수행

                Log.e("myLog_ExampleWidget", "onReceive: CLICK Button");
            }*/
            if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)){
                Bundle extras = intent.getExtras();
                if(extras != null){
                    int[] appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);
                    if(appWidgetIds != null && appWidgetIds.length > 0)
                        this.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
                }
            }
            else if(action.equals("Click1"))
                Toast.makeText(context, "드디어!!!!!!!!", Toast.LENGTH_SHORT).show();
        }
    }
}

