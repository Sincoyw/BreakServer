package com.sincoyw.breakserver.dao;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_privilege")
public class Privilege {

    public Privilege(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilege_id")
    private long privilegeID;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public long getPrivilegeID() {
        return privilegeID;
    }

    public void setPrivilegeID(long privilegeID) {
        this.privilegeID = privilegeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
