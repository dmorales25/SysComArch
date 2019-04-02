package com.lextersoft.service.dto;

import com.lextersoft.classes.TextQuery;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the Document entity.
 */
public class DocumentDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String name;

    private LocalDate uploadDate;

    @NotNull
    private String archiveName;

    @NotNull
    private String decription;

    private Long subjectId;

    private String subjectName;

    private Long studentId;

    private String studentName;

    private List<TextQuery> textQueries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<TextQuery> getTextQueries() {
        return textQueries;
    }

    public void setTextQueries(List<TextQuery> textQueries) {
        this.textQueries = textQueries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentDTO documentDTO = (DocumentDTO) o;
        if (documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", name='" + name + '\'' +
            ", uploadDate=" + uploadDate +
            ", archiveName='" + archiveName + '\'' +
            ", decription='" + decription + '\'' +
            ", subjectId=" + subjectId +
            ", subjectName='" + subjectName + '\'' +
            ", studentId=" + studentId +
            ", studentName='" + studentName + '\'' +
            ", textQueries=" + textQueries +
            '}';
    }
}
