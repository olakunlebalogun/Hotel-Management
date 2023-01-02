package fcmb.com.good.model.entity.products;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "productCategory")
public class ProductCategory extends BaseEntity {

    private String name;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "products_id", insertable = false, updatable = false)
//    private Products products;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory", cascade = CascadeType.MERGE)
    private Set<Product> prod = new HashSet<>();

    public ProductCategory(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProd() {
        return prod;
    }

    public void setProd(Set<Product> prod) {
        this.prod = prod;
    }
}
