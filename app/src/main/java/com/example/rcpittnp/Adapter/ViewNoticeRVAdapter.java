package com.example.rcpittnp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Model.Criteria;
import com.example.rcpittnp.Model.Notice;
import com.example.rcpittnp.R;

import java.util.List;

public class ViewNoticeRVAdapter extends RecyclerView.Adapter<ViewNoticeRVAdapter.ViewHolder> {
    List<Notice> notices;
    Context context;
    LayoutInflater inflater;

    public ViewNoticeRVAdapter(List<Notice> notices, Context context) {
        this.notices = notices;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewNoticeRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.noticeview, parent, false);
        ViewNoticeRVAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNoticeRVAdapter.ViewHolder holder, int position) {
        String companyName, date, pkg;
        Criteria criteria;
        companyName = notices.get(position).getCompanyName();
        date = notices.get(position).getDate();
        pkg = notices.get(position).getPkg();
        criteria = notices.get(position).getCriteria();
        holder.companyNameTv.setText(companyName);
        holder.pkgTv.setText(pkg);
        holder.dateTv.setText(date);
        if (criteria != null) {
            StringBuilder stringBuilder = new StringBuilder();
            if (criteria.getSscMarks() != null)
                stringBuilder.append("SSC > " + criteria.getSscMarks() + "  ");
            if (criteria.getHscMarks() != null)
                stringBuilder.append("HSC > " + criteria.getHscMarks() + "  ");
            if (criteria.getDiplomaMarks() != null)
                stringBuilder.append("Diploma > " + criteria.getDiplomaMarks() + "  ");
            if (criteria.getCgpa() != null)
                stringBuilder.append("CGPA > " + criteria.getCgpa() + "  ");
            if (criteria.isYearGap())
                stringBuilder.append("Min. Gap : " + criteria.getYearGapCount() + "  ");
            else
                stringBuilder.append("No Gap allowed" + "  ");
            if (criteria.isActiveBacklog())
                stringBuilder.append("Backlog allowed : " + criteria.getActiveBacklogCount() + "  ");
            else
                stringBuilder.append("No active backlog allowed" + "  ");

            holder.criteriaTv.setText(stringBuilder);
        } else {
            holder.criteriaTv.setText("No Criteria !");
        }
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyNameTv, criteriaTv, dateTv, pkgTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyNameTv = itemView.findViewById(R.id.companyName);
            criteriaTv = itemView.findViewById(R.id.criteria);
            dateTv = itemView.findViewById(R.id.date);
            pkgTv = itemView.findViewById(R.id.pkg);
        }
    }
}
