package com.jwt.LoginBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Emergency {
    @Id
    private int eid;
    private int id;
    private Date date ;
    private String status;
    private String description;
    private String location;
}
