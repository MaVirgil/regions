package com.mavi.regioner.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
public class Municipality {

    @Id
    @Column(length = 4)
    @JsonAlias("kode")
    private String code;

    @JsonAlias("navn")
    private String name;

    private String href;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "code")
    private Region region;

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
