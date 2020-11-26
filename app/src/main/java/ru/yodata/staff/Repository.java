package ru.yodata.staff;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import java.util.Collections;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yodata.staff.dto.Staff;
import static ru.yodata.staff.MainActivity.SERVER_URL;
import static ru.yodata.staff.MainActivity.TAG;

// Репозиторий для получения данных о сотрудниках c сервера REST API
public class Repository {

    private MutableLiveData<Staff> liveData;
    private static StaffApi api;

    // Конструктор
    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(StaffApi.class);
    }

    // Метод для получения данных
    void getStaffData() {
        Log.d(TAG, "Начинается получение данных с сервера...");
        api.getStaffData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        responce -> {
                            // Отсортировать полученный список сотрудников по именам(условие задачи)
                            Collections.sort(responce.getCompany().getEmployees());
                            // Записать данные в Live Data
                            liveData.postValue(responce);
                            Log.d(TAG, "Данные с сервера получены и записаны в liveData");
                        },
                        error -> { // в случае ошибки - выдать сообщение о ней в лог
                            Log.d(TAG, "Ошибка REST: " + error.getLocalizedMessage());
                        });
    }

    // Метод создает liveData, если ее еще не было и отдает данные наружу
    public MutableLiveData<Staff> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
            getStaffData();
        }
        return liveData;
    }
}
