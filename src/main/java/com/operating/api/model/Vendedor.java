package com.operating.api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "VENDEDORES")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "include_date", nullable = false)
    private Date dataInclusao;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "region", nullable = false, insertable = false, updatable = false)
    private String region;

    @ManyToOne()
    @JoinColumn(name = "region", referencedColumnName = "region")
    private Atuacao atuacao;

    public Atuacao getAtuacao() {
        return atuacao;
    }

    public void setAtuacao(Atuacao atuacao) {
        this.atuacao = atuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state.toUpperCase(Locale.ROOT);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region.toUpperCase(Locale.ROOT);
    }


    public Vendedor(int id, Date dataInclusao, String name, String phone, Integer age, String city, String state, String region) {
        this.id = id;
        this.dataInclusao = dataInclusao;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.state = state;
        this.region = region;
    }

    public Vendedor() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendedor)) return false;
        Vendedor vendedor = (Vendedor) o;
        return getId() == vendedor.getId() && Objects.equals(getDataInclusao(), vendedor.getDataInclusao()) && Objects.equals(getName(), vendedor.getName()) && Objects.equals(getPhone(), vendedor.getPhone()) && Objects.equals(getAge(), vendedor.getAge()) && Objects.equals(getCity(), vendedor.getCity()) && Objects.equals(getState(), vendedor.getState()) && Objects.equals(getRegion(), vendedor.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataInclusao(), getName(), getPhone(), getAge(), getCity(), getState(), getRegion());
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                " nome='" + name + '\'' +
                ", telefone ='" + phone + '\'' +
                ", idade =" + age +
                ", cidade='" + city + '\'' +
                ", estado='" + state + '\'' +
                ", região='" + region + '\'' +
                '}';
    }
}