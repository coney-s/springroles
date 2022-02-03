package roleexample.com.role.models;


import javax.persistence.*;
import java.util.Set;

@Entity
    @Table(name = "principle_groups")
    public class Group extends AbstractEntity {

        //removed getter and setter to save space


        @Column(unique = true, nullable = false)
        private String code;
        private String name;

        @ManyToMany(mappedBy = "userGroups")
        private Set<User> users;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
