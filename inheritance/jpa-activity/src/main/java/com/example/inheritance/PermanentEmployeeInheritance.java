package com.example.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("PERMANENT")
public class PermanentEmployeeInheritance extends BaseEmployeeInheritance {

    private double salary;
    private String benefits;
}
