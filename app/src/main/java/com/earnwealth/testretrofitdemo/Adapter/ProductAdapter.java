package com.earnwealth.testretrofitdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.earnwealth.testretrofitdemo.Pojo.ProductResponse;
import com.earnwealth.testretrofitdemo.R;
import com.earnwealth.testretrofitdemo.Pojo.UserResponse;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<ProductResponse> productTypeList;
    private LayoutInflater lf;


    public ProductAdapter(Context context, List<ProductResponse> productTypeList) {
        this.context = context;
        this.productTypeList = productTypeList;
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return productTypeList.size();
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, final int position) {
        ProductResponse genderType = productTypeList.get(position);
        holder.txtProdTitle.setText(genderType.getProductTitle());
        holder.txtProdCode.setText("" + genderType.getProductCode());
        holder.txtPurchaseDate.setText("" + genderType.getPurchaseDate());
        holder.txtProdStatus.setText("" + genderType.getProductStatus());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProdTitle, txtProdCode, txtPurchaseDate, txtProdStatus;

        public ViewHolder(View view) {
            super(view);
            txtProdTitle = view.findViewById(R.id.txtProdTitle);
            txtProdCode = view.findViewById(R.id.txtProdCode);
            txtPurchaseDate = view.findViewById(R.id.txtPurchaseDate);
            txtProdStatus = view.findViewById(R.id.txtProdStatus);
        }
    }
}