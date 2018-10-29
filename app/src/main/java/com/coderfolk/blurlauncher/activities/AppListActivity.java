package com.coderfolk.blurlauncher.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coderfolk.blurlauncher.R;
import com.coderfolk.blurlauncher.adapters.AppListAdapter;
import com.coderfolk.blurlauncher.model.AppDetail;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity implements AppListAdapter.Callback {
    private ArrayList<AppDetail> appList;
    private PackageManager packageManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        recyclerView = findViewById(R.id.recyclerViewAppList);
        packageManager = getPackageManager();
        appList = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.setLabel(ri.loadLabel(packageManager));
            app.setName(ri.activityInfo.packageName);
            app.setIcon(ri.activityInfo.loadIcon(packageManager));
            appList.add(app);
        }

        AppListAdapter adapter = new AppListAdapter(appList);
        adapter.setCallback(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAppClick(View view, int position) {
        Intent intent = packageManager.getLaunchIntentForPackage(appList.get(position).getName().toString());
        AppListActivity.this.startActivity(intent);
    }
}
