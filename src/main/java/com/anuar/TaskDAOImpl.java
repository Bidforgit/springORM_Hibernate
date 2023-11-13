package com.anuar;

import java.sql.Timestamp;
import java.util.List;

import com.anuar.Task;
import com.anuar.TaskDAO;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;


public class TaskDAOImpl implements TaskDAO {


    private HibernateTemplate hibernatTemplate;

    public void sethTemplate(HibernateTemplate hibernatTemplate) {
        this.hibernatTemplate = hibernatTemplate;
    }

    @Override
    @Transactional
    public int insert(Task task) {
        // Create a new Task object with the provided values
//        Task task = new Task();
//        task.setName(name);
//        task.setDescription(desc);
//        task.setDate(date);
//        task.setStatus(status);
//
//        session.save(task);
//        session.flush();
        return (int) hibernatTemplate.save(task);
    }

    @Override
    @Transactional
    public void delete(int id) {
//        session.createQuery("DELETE FROM Task WHERE id = :id")
//                .setParameter("id",id)
//                .executeUpdate();

        Task s = hibernatTemplate.get(Task.class, id);
        hibernatTemplate.delete(s);
    }

    //        session.createQuery("UPDATE Task SET name = :newName, status = :newStatus  WHERE id = :taskId")
//                .setParameter("newName",name)
//                .setParameter("newStatus",status)
//                .executeUpdate();
    @Override
    @Transactional
    public void update(Task task) {
        hibernatTemplate.update(task);
    }

    @Override
    @Transactional
    public Task getTask(int id) {
        return hibernatTemplate.get(Task.class, id);
    }

    @Override
    @Transactional
    public List<Task> getTasksByStatus(String status) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Task.class);
        criteria.add(Restrictions.eq("status", status));
        return (List<Task>) hibernatTemplate.findByCriteria(criteria);
    }
}
