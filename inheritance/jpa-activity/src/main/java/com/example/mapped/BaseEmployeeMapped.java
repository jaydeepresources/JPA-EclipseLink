package com.example.mapped;

import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEmployeeMapped {

    private String name;
    private String department;
}
