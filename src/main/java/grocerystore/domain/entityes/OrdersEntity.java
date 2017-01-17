package grocerystore.domain.entityes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by raxis on 17.01.2017.
 */
@Entity
@Table(name = "orders", schema = "groceriesstore")
public class OrdersEntity {
    private static final Logger logger = LoggerFactory.getLogger(OrdersEntity.class);
    private String id;
    private String userid;
    private String statusid;
    private BigDecimal price;
    private Timestamp datetime;
    private String grocerylistid;
    private String address;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERID", nullable = false, length = 36)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "STATUSID", nullable = false, length = 36)
    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "DATETIME", nullable = true)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "GROCERYLISTID", nullable = false, length = 36)
    public String getGrocerylistid() {
        return grocerylistid;
    }

    public void setGrocerylistid(String grocerylistid) {
        this.grocerylistid = grocerylistid;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (statusid != null ? !statusid.equals(that.statusid) : that.statusid != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) return false;
        if (grocerylistid != null ? !grocerylistid.equals(that.grocerylistid) : that.grocerylistid != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (statusid != null ? statusid.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (grocerylistid != null ? grocerylistid.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
