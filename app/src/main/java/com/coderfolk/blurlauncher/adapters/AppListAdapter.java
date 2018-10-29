package com.coderfolk.blurlauncher.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderfolk.blurlauncher.R;
import com.coderfolk.blurlauncher.model.AppDetail;

import java.util.ArrayList;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppListViewHolder> {

    public interface Callback {
        void onAppClick(View view, int position);
    }

    private ArrayList<AppDetail> appDetails;
    private Callback callback;

    public AppListAdapter(ArrayList<AppDetail> appDetails) {
        this.appDetails = appDetails;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    @NonNull
    @Override
    public AppListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AppListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppListViewHolder appListViewHolder, int position) {
        TextView appName = appListViewHolder.appName;
        TextView appLabel = appListViewHolder.appLabel;
        ImageView appIcon = appListViewHolder.appIcon;

        appLabel.setText(appDetails.get(position).getLabel());
        appName.setText(appDetails.get(position).getName());
        appIcon.setImageDrawable(appDetails.get(position).getIcon());

    }


    @Override
    public int getItemCount() {
        return appDetails.size();
    }


    class AppListViewHolder extends RecyclerView.ViewHolder {
        TextView appLabel;
        TextView appName;
        ImageView appIcon;

        public AppListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.appLabel = (TextView) itemView.findViewById(R.id.item_app_label);
            this.appName = (TextView) itemView.findViewById(R.id.item_app_name);
            this.appIcon = (ImageView) itemView.findViewById(R.id.item_app_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onAppClick(view, getAdapterPosition());
                }
            });
        }

    }
}
