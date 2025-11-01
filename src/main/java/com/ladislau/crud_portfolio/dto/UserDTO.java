package com.ladislau.crud_portfolio.dto;

import com.ladislau.crud_portfolio.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private List<ProjectDTO> projects = new ArrayList<>();

    public UserDTO(Long id, String name, String email, List<ProjectDTO> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.projects = projects != null ? projects : new ArrayList<>();
    }

    public static UserDTO fromEntity(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getProjects() != null
                        ? user.getProjects().stream()
                        .map(ProjectDTO::fromEntity)
                        .collect(Collectors.toList())
                        : null
        );
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return user;
    }
}
