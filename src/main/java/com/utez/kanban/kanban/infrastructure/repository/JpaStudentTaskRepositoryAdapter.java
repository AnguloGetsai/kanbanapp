package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.out.StudentTaskRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import com.utez.kanban.kanban.infrastructure.mapper.StudentMapper;
import com.utez.kanban.kanban.infrastructure.mapper.StudentTaskMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JpaStudentTaskRepositoryAdapter implements StudentTaskRepositoryPort {

    private final JpaStudentTaskRepository jpaStudentTaskRepository;
    public JpaStudentTaskRepositoryAdapter(JpaStudentTaskRepository jpaStudentTaskRepository){
        this.jpaStudentTaskRepository = jpaStudentTaskRepository;
    }


    @Override
    public void save(StudentTask studentTask) {
        jpaStudentTaskRepository.save(StudentTaskMapper.toStudentTaskEntity(studentTask));
    }

    @Override
    public void saveAll(List<StudentTask> studentTaskList) {

        List<StudentTaskEntity> studentTaskEntityList = studentTaskList
                .stream()
                .map(studentTask ->
                        StudentTaskMapper.toStudentTaskEntity(studentTask)
                )
                .toList();

        jpaStudentTaskRepository.saveAll(studentTaskEntityList);
    }

    @Override
    public List<StudentTask> getTasksByStudentAndAdviser(String email, Long adviserID) {
        List<StudentTask> list = new ArrayList<>();

        for(StudentTaskEntity entity : jpaStudentTaskRepository
                .findTasksByStudentAndAdviser(email, adviserID)){

            list.add(StudentTaskMapper.toStudentTask(entity));
        }

        return list;
    }

    @Override
    public Optional<StudentTask> getTaskDetail(String email, Long taskID) {
        return jpaStudentTaskRepository.findTaskDetailById(taskID, email)
                .map(StudentTaskMapper::toStudentTask);
    }

    @Override
    public List<StudentTask> findByTaskId(Long taskID) {
        return jpaStudentTaskRepository.findByTaskId(taskID)
                .stream()
                .map(StudentTaskMapper::toStudentTask)
                .toList();
    }

    @Override
    public void deleteByTaskId(Long taskID) {
        jpaStudentTaskRepository.deleteByTaskId(taskID);
    }

    @Override
    public Optional<StudentTask> findByStudentAndTask(Long studentID, Long taskID) {
        return jpaStudentTaskRepository
        .findByStudentIDAndTaskID(studentID, taskID)
                .map(StudentTaskMapper::toStudentTask);
    }

    @Override
    public void deleteStudentTask(StudentTask studentTask) {
        // Extraemos los IDs directamente de los objetos anidados en tu dominio
        Long studentID = studentTask.getStudent().getStudentID();
        Long taskID = studentTask.getTask().getTaskID();

        // Llamamos a nuestro nuevo método del repositorio
        jpaStudentTaskRepository.deleteByStudentAndTask(studentID, taskID);
    }
}
