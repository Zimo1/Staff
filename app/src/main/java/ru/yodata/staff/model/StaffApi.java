package ru.yodata.staff.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.yodata.staff.model.dto.Staff;

// Работа с сервером REST API
public interface StaffApi {
    // Команда получения данных о компании и сотрудниках с сервера
    @GET("v2/5ddcd3673400005800eae483")
    Observable<Staff> getStaffData();
}
