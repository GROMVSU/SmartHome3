package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;

@MappedSuperclass
abstract public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_native")
    @GenericGenerator(name = "id_native", strategy = "native")//enhanced-sequence
    @Column(name="id")
    private Integer identity;

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity entity = (BaseEntity) o;
        return identity.equals(entity.identity);
    }

    @Override
    public int hashCode() {
        return identity != null ? identity.hashCode() : 0;
    }
}
