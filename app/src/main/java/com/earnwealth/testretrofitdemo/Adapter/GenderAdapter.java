package com.earnwealth.testretrofitdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.earnwealth.testretrofitdemo.R;
import com.earnwealth.testretrofitdemo.Pojo.UserResponse;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GenderAdapter extends RecyclerView.Adapter<GenderAdapter.ViewHolder> {
    Context context;
    List<UserResponse> genderTypeList;
    LayoutInflater lf;


    public GenderAdapter(Context context, List<UserResponse> genderTypeList) {
        this.context = context;
        this.genderTypeList = genderTypeList;
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public GenderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gender, parent, false);
        return new GenderAdapter.ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return genderTypeList.size();
    }

    @Override
    public void onBindViewHolder(GenderAdapter.ViewHolder holder, final int position) {
        UserResponse genderType = genderTypeList.get(position);
        holder.txtGenderTitle.setText(genderType.getGender());
        holder.txtGenderId.setText("" + genderType.getGenderId());

        holder.llGenderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genderTypeList.get(position).getGenderId().equals(1)) {
                    Toast.makeText(context, "Clicked on" + genderTypeList.get(position).getGender(), Toast.LENGTH_SHORT).show();
                } else if (genderTypeList.get(position).getGenderId().equals(2)) {
                    Toast.makeText(context, "Clicked on" + genderTypeList.get(position).getGender(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Wrong option selected buddy!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtGenderTitle, txtGenderId;
        LinearLayout llGenderType;

        public ViewHolder(View view) {
            super(view);
            txtGenderTitle = view.findViewById(R.id.txtGenderTitle);
            txtGenderId = view.findViewById(R.id.txtGenderId);
            llGenderType = view.findViewById(R.id.llGenderType);
        }
    }
}