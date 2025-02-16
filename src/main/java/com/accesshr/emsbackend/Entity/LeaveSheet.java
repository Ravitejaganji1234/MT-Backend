package com.accesshr.emsbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LeaveSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int SICK;
    private int VACATION;
    private int CASUAL;
    private int MARRIAGE;
    private int PATERNITY;
    private int MATERNITY;
    private int OTHERS;
}
