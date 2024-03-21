package com.sales.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private  String lastName;
    private  String mobile;
    private  String email;

    public Client(String name, String email,String lastName , String mobile) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.mobile = mobile;
    }

    public Client() {

    }
}
