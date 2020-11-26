package ru.yodata.staff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import ru.yodata.staff.databinding.ActivityMainBinding;
import ru.yodata.staff.model.dto.Staff;
import ru.yodata.staff.viewmodel.StaffRecyclerViewAdapter;
import ru.yodata.staff.viewmodel.StaffViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String SERVER_URL = "http://www.mocky.io/";
    public static final String TAG = "Staff";
    public StaffViewModel staffViewModel;
    private ActivityMainBinding activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Активировать View Binding, чтобы не пользоваться findViewById()
        activity = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activity.getRoot());
        // Проверить соединение с Интернетом
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            // Соединение есть
            activity.dataLayout.setVisibility(View.VISIBLE);
            activity.progressBar.setVisibility(View.VISIBLE);
            // Подписаться на Live Data и получить данные с сервера
            staffViewModel = ViewModelProviders.of(this).get(StaffViewModel.class);
            LiveData<Staff> liveData = staffViewModel.getLiveData();
            liveData.observe(this, new Observer<Staff>() {
                @Override
                public void onChanged(Staff staff) {
                    // Заполнить данные о компании на экране
                    activity.companyNameTv.setText(staff.getCompany().getName());
                    activity.companyAgeTv.setText(staff.getCompany().getAge());
                    activity.companySkillsTv.setText(staff.getCompany().getCompetences().toString());
                    // Инициализировать Recycler View со списком сотрудников
                    StaffRecyclerViewAdapter recyclerViewAdapter =
                         new StaffRecyclerViewAdapter((ArrayList)staff.getCompany().getEmployees());
                    activity.staffRecyclerView.setLayoutManager(
                         new LinearLayoutManager(getApplicationContext()));
                    activity.staffRecyclerView.setAdapter(recyclerViewAdapter);
                    activity.progressBar.setVisibility(View.GONE);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            });
        }
        else { // соединение с Интернетом отсутствует, данные прочитать невозможно
            // Вывести на экран сообщение об отсутствии подключения и завершить работу
            activity.internetOffTv.setVisibility(View.VISIBLE);
            Log.d(TAG, "Отсутствует соединение с Интернетом");
        }
    }
}