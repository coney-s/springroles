package roleexample.com.role.core.security.jpa;

import org.hibernate.annotations.CreationTimestamp;
import roleexample.com.role.core.user.jpa.data.UserEntity;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "secureTokens")
public class SecureToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timestamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private UserEntity user;

    @Transient
    private boolean isExpired;

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public Long getId(){
        return id;
    }

    public LocalDateTime getExpireAt(){
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt){
        this.expireAt = expireAt;
    }

    public Timestamp getTimestamp(){
        return timestamp;
    }

    public boolean isExpired(){
        return getExpireAt().isBefore(LocalDateTime.now()); //this is a generic implementation, you can always make timezone specific.
    }

    public UserEntity getUser(){
        return user;
    }

    public void setUser(UserEntity user){
        this.user = user;
    }
}
