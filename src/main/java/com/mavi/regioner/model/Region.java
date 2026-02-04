package com.mavi.regioner.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @Column(length = 4)
    @JsonAlias("kode")
    private String code;

    @JsonAlias("navn")
    private String name;

    private String href;

    @OneToMany(mappedBy = "region", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Municipality> municipalityList = new HashSet<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Set<Municipality> getMunicipalityList() {
        return municipalityList;
    }

    public void setMunicipalityList(Set<Municipality> municipalityList) {
        this.municipalityList = municipalityList;
    }
}
