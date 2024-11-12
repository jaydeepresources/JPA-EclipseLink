package com.example;

import java.util.List;

public interface ActivityDAO {

    Activity insert(Activity activity);
    public Activity update(Activity activity);
    public void deleteById(Long id);
    public Activity findById(Long id);
    public List<Activity> findAll();

}