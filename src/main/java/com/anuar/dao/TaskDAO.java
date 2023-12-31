package com.anuar.dao;

import com.anuar.beans.Task;

import java.util.List;

public interface TaskDAO {

    public int insert(Task task);
    public void delete(int id);
    public void update(Task task);
    public Task getTask (int id);
    public List<Task> getTasksByStatus(String status);


}
