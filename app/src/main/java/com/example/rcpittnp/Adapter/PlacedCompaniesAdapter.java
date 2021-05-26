package com.example.rcpittnp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.R;

import java.util.List;

public class PlacedCompaniesAdapter extends RecyclerView.Adapter<PlacedCompaniesAdapter.ViewHolder> {
    List<String> placedCompanies;
    Context context;
    LayoutInflater inflater;

    public PlacedCompaniesAdapter(List<String> placedCompanies, Context context) {
        this.placedCompanies = placedCompanies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.placed_company_view ,parent , false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String companyName = placedCompanies.get(position);
        holder.companyNameTv.setText(companyName);
    }

    @Override
    public int getItemCount() {
        if (placedCompanies != null)
            return placedCompanies.size();
        return 0;
    }

    public void updateData(List<String> placedCompanies)
    {
        this.placedCompanies = placedCompanies;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyNameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyNameTv = itemView.findViewById(R.id.companyName);
        }
    }
}
