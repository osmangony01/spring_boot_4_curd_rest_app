package org.example.spring_boot_curd_rest_app.repository;

import org.example.spring_boot_curd_rest_app.model.Task;
import org.example.spring_boot_curd_rest_app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
