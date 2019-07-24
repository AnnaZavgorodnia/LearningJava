package com.salon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salon.entity.Position;
import com.salon.entity.Service;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class MasterDTO implements Serializable {

    private String fullName;
    private String username;
    private Position position;
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Service> services = new HashSet<>();
}
