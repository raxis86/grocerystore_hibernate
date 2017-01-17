package grocerystore.domain.entityes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 17.01.2017.
 */
@Entity
@Table(name = "roles", schema = "groceriesstore")
public class RolesEntity {
    private static final Logger logger = LoggerFactory.getLogger(RolesEntity.class);
    private String id;
    private String rolename;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROLENAME", nullable = true, length = 45)
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rolename != null ? !rolename.equals(that.rolename) : that.rolename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
    }
}
