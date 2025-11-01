package com.ladislau.crud_portfolio.dto;

import com.ladislau.crud_portfolio.model.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectDTO {

    private Long id;
    private String title;
    private String description;
    private String link;
    private Long userId;

    public ProjectDTO(Long id, String title, String description, String link, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.userId = userId;
    }

    public static ProjectDTO fromEntity(Project project) {
        if (project == null) return null;

        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getLink(),
                project.getUser() != null ? project.getUser().getId() : null
        );
    }

    public static Project toEntity(ProjectDTO dto) {
        if (dto == null) return null;

        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setLink(dto.getLink());
        
        return project;
    }
}
