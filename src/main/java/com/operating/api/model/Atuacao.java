package com.operating.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Table(name = "ATUACAO")
@Entity
public class Atuacao {

    @Id
    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "states", nullable = false)
    @Convert(converter = ListToStringConverter.class)
    private List<String> states;

    public Atuacao() {
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region.toUpperCase(Locale.ROOT);
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public Atuacao(String region, List<String> state) {

        this.region = region;
        this.states = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atuacao)) return false;
        Atuacao atuacao = (Atuacao) o;
        return getRegion().equals(atuacao.getRegion()) && getStates().equals(atuacao.getStates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegion(), getStates());
    }

    @Override
    public String toString() {
        return "atuacao{" +
                " região='" + region + '\'' +
                ", estados='" + states + '\'' +
                '}';
    }
}
