package grocerystore.domain.entityes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * Created by raxis on 17.01.2017.
 */
@Entity
@Table(name = "grocerylist", schema = "groceriesstore")
@IdClass(ListGroceryEntityPK.class)
public class ListGroceryEntity {
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryEntity.class);
    private String id;
    private String groceryid;
    private int quantity;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "GROCERYID", nullable = false, length = 36)
    public String getGroceryid() {
        return groceryid;
    }

    public void setGroceryid(String groceryid) {
        this.groceryid = groceryid;
    }

    @Basic
    @Column(name = "QUANTITY", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListGroceryEntity that = (ListGroceryEntity) o;

        if (quantity != that.quantity) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groceryid != null ? !groceryid.equals(that.groceryid) : that.groceryid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groceryid != null ? groceryid.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
