package roleexample.com.role.core.user.jpa;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
    @Table(name = "principle_groups")
    public class Group extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        @Column(unique = true, nullable = false)
        private String code;
        private String name;



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

    @Override
    public Long getId() {
        return id;
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Group userGroup = (Group) o;
            boolean equals = Objects.equals(getId(), userGroup.id);
            return equals;
        }



        @ManyToMany(mappedBy = "userGroups")
        private Set<UserEntity> users;

        public Set<UserEntity> getUsers() {
            return users;
        }

        public void setUsers(Set<UserEntity> users) {
            this.users = users;
        }


}
