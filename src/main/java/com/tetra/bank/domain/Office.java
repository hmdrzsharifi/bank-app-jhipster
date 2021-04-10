package com.tetra.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * The Office entity.\n@author HamidReza.
 */
@Entity
@Table(name = "m_office")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(name = "opening_date", nullable = false)
    private Instant openingDate;

    @Size(max = 100)
    @Column(name = "external_id", length = 100)
    private String externalId;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Office> children = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Office parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Office name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getOpeningDate() {
        return openingDate;
    }

    public Office openingDate(Instant openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public void setOpeningDate(Instant openingDate) {
        this.openingDate = openingDate;
    }

    public String getExternalId() {
        return externalId;
    }

    public Office externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Set<Office> getChildren() {
        return children;
    }

    public Office children(Set<Office> offices) {
        this.children = offices;
        return this;
    }

    public Office addChildren(Office office) {
        this.children.add(office);
        office.setParent(this);
        return this;
    }

    public Office removeChildren(Office office) {
        this.children.remove(office);
        office.setParent(null);
        return this;
    }

    public void setChildren(Set<Office> offices) {
        this.children = offices;
    }

    public Office getParent() {
        return parent;
    }

    public Office parent(Office office) {
        this.parent = office;
        return this;
    }

    public void setParent(Office office) {
        this.parent = office;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Office)) {
            return false;
        }
        return id != null && id.equals(((Office) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Office{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", openingDate='" + getOpeningDate() + "'" +
            ", externalId='" + getExternalId() + "'" +
            "}";
    }
}
