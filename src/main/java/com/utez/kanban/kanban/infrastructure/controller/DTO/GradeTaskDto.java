package com.utez.kanban.kanban.infrastructure.controller.DTO;

import jakarta.validation.constraints.NotNull;

public class GradeTaskDto {
    @NotNull(message = "Grade cannot be null")
    private Double grade;

    private String feedback;


    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
