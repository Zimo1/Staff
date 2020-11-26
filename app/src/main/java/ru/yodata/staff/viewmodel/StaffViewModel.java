package ru.yodata.staff.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ru.yodata.staff.Repository;
import ru.yodata.staff.dto.Staff;

// Вьюмодель для получения данных из репозитория
public class StaffViewModel extends ViewModel {

    private Repository repository;

    public StaffViewModel() {
        super();
        repository = new Repository();
    }

    public LiveData<Staff> getLiveData() {
        return repository.getLiveData();
    }
}
