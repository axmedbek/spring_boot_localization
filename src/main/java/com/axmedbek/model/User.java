package com.axmedbek.model;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private int age;
    private String position;
    @Transient
    private MultipartFile file;
    private String image;
    @Type(type = "text")
    private String description;
    private String password;
    private String username;
    @Transient
    private String passwordConfirm;
    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(){}

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(String name, String surname, int age, String position, String image, String description) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.image = image;
        this.description = description;
    }

    public User(String name, String surname, int age, String position, MultipartFile file, String image, String description, String password, String username, String passwordConfirm, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.file = file;
        this.image = image;
        this.description = description;
        this.password = password;
        this.username = username;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public User(String name, String surname, int age, String position, MultipartFile file, String description) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.file = file;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
