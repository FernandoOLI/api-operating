package com.operating.api.model;

import java.util.Date;
import java.util.List;

public class VendedorReponseUnit {

    private String name;
    private Date dataInclusao;
    private List<String> states;

    public VendedorReponseUnit(String name, Date dataInclusao, List<String> states) {
        this.name = name;
        this.dataInclusao = dataInclusao;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }




}
