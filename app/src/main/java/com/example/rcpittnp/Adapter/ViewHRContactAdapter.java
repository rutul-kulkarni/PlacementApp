package com.example.rcpittnp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Model.HrContact;
import com.example.rcpittnp.R;

import java.util.List;

public class ViewHRContactAdapter extends RecyclerView.Adapter<ViewHRContactAdapter.ViewHolder> {

    List<HrContact> hrContacts;
    Context context;
    LayoutInflater inflater;
    public ViewHRContactAdapter(Context context, List<HrContact> hrContacts) {
        this.context = context;
        this.hrContacts = hrContacts;
    }

    @NonNull
    @Override
    public ViewHRContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hr_contact_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHRContactAdapter.ViewHolder holder, int position) {
        String companyName, hrName, mobNum;
        HrContact hrContact = hrContacts.get(position);
        hrName = hrContact.getHrName();
        companyName = hrContact.getCompanyName();
        mobNum = hrContact.getMobileNumber();
        holder.hrNameTv.setText(hrName);
        holder.companyNameTv.setText(companyName);
        holder.mobNumTv.setText(mobNum);
    }

    @Override
    public int getItemCount() {
        return hrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hrNameTv , companyNameTv , mobNumTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hrNameTv = itemView.findViewById(R.id.hrNameEt);
            companyNameTv = itemView.findViewById(R.id.companyName);
            mobNumTv = itemView.findViewById(R.id.mobNumber);
        }
    }
}
