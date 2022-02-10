package br.com.alura.control.financeiro.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tb_profile")
public class Profile implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id",nullable = false)
    private Long profileId;

    @Column(name = "name_profile",nullable = false,columnDefinition = "CHARACTER VARYING(255)")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
    
}

