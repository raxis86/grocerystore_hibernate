package grocerystore.domain.entityes;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 17.01.2017.
 */
@Entity
@Table(name = "orderupdates", schema = "groceriesstore")
public class OrderupdatesEntity {
    private static final Logger logger = LoggerFactory.getLogger(OrderupdatesEntity.class);
    private String id;
    private String status;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderupdatesEntity that = (OrderupdatesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
