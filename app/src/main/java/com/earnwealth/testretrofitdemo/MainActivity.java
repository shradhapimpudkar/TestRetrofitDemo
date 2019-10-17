package com.earnwealth.testretrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.earnwealth.testretrofitdemo.Adapter.GenderAdapter;
import com.earnwealth.testretrofitdemo.Pojo.UserResponse;
import com.earnwealth.testretrofitdemo.WebService.ApiService;
import com.earnwealth.testretrofitdemo.WebService.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    private ArrayList<UserResponse> insTypeList = new ArrayList<>();
    private GenderAdapter genderTypeAdapter;
    private RecyclerView rvGenderType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        GenderWS();

    }

    private void GenderWS() {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Loading data please wait...");
        pd.show();
        pd.setCanceledOnTouchOutside(false);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<UserResponse>> call = apiService.gender_type();
        Log.i("@shradha", "GenderTypeUrl: " + call.request().url().toString());

        call.enqueue(new Callback<List<UserResponse>>() {

            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.i("@shradha", "GenderTypeResp:" + response.toString());

                        //Set Gender Type Adapter on Listview view to display url data on it
                        insTypeList = (ArrayList<UserResponse>) response.body();
                        genderTypeAdapter = new GenderAdapter(context, insTypeList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rvGenderType.setLayoutManager(mLayoutManager);
                        rvGenderType.setAdapter(genderTypeAdapter);
                        genderTypeAdapter.notifyDataSetChanged();

                        pd.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("@B2C", e.getMessage());
                        Toast.makeText(context, e.getMessage(), LENGTH_LONG).show();
                        Toast.makeText(context, "Something went wrong", LENGTH_LONG).show();
                        pd.dismiss();
                    }
                    System.out.println("onResponseonResponse  " + response.body().size());
                } else {
                    System.out.println("onResponseonResponse  " + response.code() + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.i("@B2C", "Error: " + t.toString());
                Toast.makeText(context, t.getMessage(), LENGTH_LONG).show();
                Toast.makeText(context, "Something went wrong", LENGTH_LONG).show();
                pd.dismiss();
            }
        });
    }


    private void initUi() {
        rvGenderType = findViewById(R.id.rvGenderType);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtils.checkNetworkConnection(context)) {
            GenderWS();
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

}
