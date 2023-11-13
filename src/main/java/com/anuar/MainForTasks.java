package com.anuar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.anuar.TaskDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Scanner;

public class MainForTasks {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ApplicationContext ctx= ContextProvider.provideContext();
        TaskDAO taskDAO=ctx.getBean("taskDAO",TaskDAO.class);

        System.out.println("1 for create task");
        System.out.println("2 for update task");
        System.out.println("3 for delete task");
        System.out.println("4 for list tasks");
        System.out.println("5 to EXIT");
        int a = sc.nextInt();



        switch (a){
            case 1:
                String name = sc.next();
                String desc =sc.next();
                String status = sc.nextLine();
                Timestamp time = Timestamp.valueOf(sc.nextLine());
                status = sc.next();
                Task task = new Task(name,desc,time,status);
                taskDAO.insert(task);
                break;
            case 2:
                System.out.println("id:");
              int id = sc.nextInt();
              Task task1 = taskDAO.getTask(id);
              task1.setName(sc.next());
              task1.setDescription(sc.next());
              task1.setStatus(sc.next());
              taskDAO.update(task1);
                break;
            case 3:
                System.out.println("id:");
                taskDAO.delete(sc.nextInt());
                break;
            case 4:
                System.out.println("Status filter:");
                System.out.println(taskDAO.getTasksByStatus(sc.next()));
                break;


        }

    }
}
