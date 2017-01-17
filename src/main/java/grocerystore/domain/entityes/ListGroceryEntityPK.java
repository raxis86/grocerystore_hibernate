package grocerystore.domain.entityes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by raxis on 17.01.2017.
 */
public class ListGroceryEntityPK implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ListGroceryEntityPK.class);
    private String id;
    private String groceryid;

    @Column(name = "ID", nullable = false, length = 36)
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "GROCERYID", nullable = false, length = 36)
    @Id
    public String getGroceryid() {
        return groceryid;
    }

    public void setGroceryid(String groceryid) {
        this.groceryid = groceryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListGroceryEntityPK that = (ListGroceryEntityPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groceryid != null ? !groceryid.equals(that.groceryid) : that.groceryid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groceryid != null ? groceryid.hashCode() : 0);
        return result;
    }
}
