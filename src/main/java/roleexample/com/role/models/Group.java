package roleexample.com.role.models;


import roleexample.com.role.core.user.jpa.data.UserEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
    @Table(name = "principle_groups")
    public class Group {

        //removed getter and setter to save space


        @Column(unique = true, nullable = false)
        private String code;
        private String name;

        @ManyToMany(mappedBy = "userGroups")
        private Set<UserEntity> users;

        public Group(String code, String name){
            this.code = code;
            this.name = name;
        }

        public Group(){}

        //GETTER AND SETTER


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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
