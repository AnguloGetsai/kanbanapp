package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.StudentTask;

import java.util.ArrayList;
import java.util.List;

public class TaskDetailDto {
    private Long taskID;
    private String name;
    private String description;
    private String statusKanban;
    private String color;

    private List<AttachmentDto> attachments;

    public TaskDetailDto(Long taskID, String name, String description,
                         String statusKanban, String color,
                         List<AttachmentDto> attachments) {
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.attachments = attachments;
    }

    public static TaskDetailDto fromDomain(StudentTask st){

        List<AttachmentDto> files = new ArrayList<>();

        if(st.getTask().getAttachments() != null){
            files = st.getTask().getAttachments()
                    .stream()
                    .map(AttachmentDto::fromDomain)
                    .toList();
        }

        return new TaskDetailDto(
                st.getTask().getTaskID(),
                st.getTask().getName(),
                st.getTask().getDescription(),
                st.getTask().getStatusKanban(),
                st.getTask().getColor(),
                files
        );
    }


    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusKanban() {
        return statusKanban;
    }

    public void setStatusKanban(String statusKanban) {
        this.statusKanban = statusKanban;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<AttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDto> attachments) {
        this.attachments = attachments;
    }
}
