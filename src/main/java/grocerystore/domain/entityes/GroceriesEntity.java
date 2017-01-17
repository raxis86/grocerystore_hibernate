package grocerystore.domain.entityes;

import javax.persistence.*;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raxis on 17.01.2017.
 */
@Entity
@Table(name = "groceries", schema = "groceriesstore")
public class GroceriesEntity {
    private static final Logger logger = LoggerFactory.getLogger(GroceriesEntity.class);
    private String id;
    private String parentid;
    private byte iscategory;
    private String name;
    private Integer quantity;
    private BigDecimal price;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PARENTID", nullable = true, length = 36)
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "ISCATEGORY", nullable = false)
    public byte getIscategory() {
        return iscategory;
    }

    public void setIscategory(byte iscategory) {
        this.iscategory = iscategory;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "QUANTITY", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroceriesEntity that = (GroceriesEntity) o;

        if (iscategory != that.iscategory) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (parentid != null ? !parentid.equals(that.parentid) : that.parentid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentid != null ? parentid.hashCode() : 0);
        result = 31 * result + (int) iscategory;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
