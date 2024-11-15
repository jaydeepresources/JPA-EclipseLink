package com.example.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CONSULTANT")
public class ConsultantEmployeeInheritance extends BaseEmployeeInheritance {

    private double hourlyRate;
    private int hoursWorked;
}
