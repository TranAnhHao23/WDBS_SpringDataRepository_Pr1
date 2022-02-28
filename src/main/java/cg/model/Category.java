package cg.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long c_id;
    private String c_name;

    public Category() {
    }

    public Category(String c_name) {
        this.c_name = c_name;
    }

    public Category(Long c_id, String c_name) {
        this.c_id = c_id;
        this.c_name = c_name;
    }

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
