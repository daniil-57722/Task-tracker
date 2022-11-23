package com.example.courseWork.repository;

import com.example.courseWork.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository  extends CrudRepository<Task, Long> {
}
