package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Attachment;

import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.model.Task;


import javax.xml.stream.events.StartDocument;
import java.time.LocalDate;
import java.util.List;

public class TaskSimpleDto {



    private Long id;
    private String name;
    private String statusKanban;
    private String color;


    private List<Long> students;
    private String priority;
    private LocalDate startDate;
    private LocalDate dateOfEnd;
    private String notes;
    private List<AttachmentDto> files;


    public static Task toTask(UpdateTaskDto dto) {
        if (dto == null) return null;

        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatusKanban(dto.getStatusKanban());
        task.setColor(dto.getColor());
        task.setPriority(dto.getPriority());
        task.setLimitDate(dto.getLimitDate());

        return task;
    }


    public static TaskSimpleDto fromDomain(Task task, List<StudentTask> studentTasks){

        List<Long> students = studentTasks.stream()
                .map(st -> st.getStudent().getStudentID())
                .toList();

        List<AttachmentDto> files = task.getAttachments()
                .stream()
                .map(AttachmentDto::fromDomain)
                .toList();

        return new TaskSimpleDto(
                task.getTaskID(),
                task.getName(),
                task.getStatusKanban(),
                task.getColor(),
                students,
                task.getPriority(),
                task.getCreationDate(),
                task.getLimitDate(),
                task.getDescription(),
                files
        );

    }


    public TaskSimpleDto(Long id, String name, String statusKanban,
                         String color, List<Long> students,
                         String priority,LocalDate startDate,
                         LocalDate dateOfEnd, String notes,
                         List<AttachmentDto> files) {
        this.id = id;
        this.name = name;
        this.statusKanban = statusKanban;
        this.color = color;
        this.students = students;
        this.priority = priority;
this.startDate = startDate;
        this.dateOfEnd = dateOfEnd;
        this.notes = notes;
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusKanban() {
        return statusKanban;
    }

    public void setStatusKanban(String statusKanban) {
        this.statusKanban = statusKanban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Long> getStudents() {
        return students;
    }

    public void setStudents(List<Long> students) {
        this.students = students;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDate dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public List<AttachmentDto> getFiles() {
        return files;
    }

    public void setFiles(List<AttachmentDto> files) {
        this.files = files;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
