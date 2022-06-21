package com.general.template.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;

    private String name;

    @Size(min = 2, max = 100, message = "Username character must be between 2 to 100!")
    private String username;

    @Size(min = 6)
    private String password;

    private String phone;

    @NotNull
    private Long roleId;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, @Size(min = 2, max = 100, message = "Username character must be between 2 to 100!") String username, @Size(min = 6) String password, String phone, @NotNull Long roleId) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.roleId = roleId;
    }
}