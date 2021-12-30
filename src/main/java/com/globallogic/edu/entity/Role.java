package com.globallogic.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)

@Entity
@Table(name = "role")
public class Role {

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

}