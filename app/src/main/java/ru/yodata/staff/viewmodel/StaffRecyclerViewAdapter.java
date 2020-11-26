package ru.yodata.staff.viewmodel;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ru.yodata.staff.databinding.EmployeeCardBinding;
import ru.yodata.staff.model.dto.EmployeesItem;

// Адаптер для Recycler View со стандартными методами для подобных адаптеров
public class StaffRecyclerViewAdapter extends
                RecyclerView.Adapter<StaffRecyclerViewAdapter.StaffViewHolder>{

    public ArrayList<EmployeesItem> employeesItems;

    // Конструктор
    public StaffRecyclerViewAdapter(ArrayList<EmployeesItem> employeesItems) {
        this.employeesItems = employeesItems;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaffViewHolder(EmployeeCardBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        EmployeesItem dev = employeesItems.get(position);
        holder.devNameView.setText(dev.getName());
        holder.devPhoneView.setText(dev.getPhoneNumber());
        holder.devSkillsView.setText(dev.getSkills().toString());
    }

    @Override
    public int getItemCount() {
        return employeesItems.size();
    }

    // Вьюхолдер
    public class StaffViewHolder extends RecyclerView.ViewHolder {

        private EmployeeCardBinding card;
        private TextView devNameView;
        private TextView devPhoneView;
        private TextView devSkillsView;

        public StaffViewHolder(EmployeeCardBinding binding) {
            // Активировать View Binding, чтобы не пользоваться findViewById()
            super(binding.getRoot());
            card = binding;
            // Получить views, которые необходимо будет заполнять
            // данными сотрудников в onBindViewHolder
            devNameView = card.devNameTv;
            devPhoneView = card.devPhoneTv;
            devSkillsView = card.devSkillsTv;
        }
    }
}
