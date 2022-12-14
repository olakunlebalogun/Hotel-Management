package fcmb.com.good.model.entity.others;

import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.assets.AssetsCategory;
import fcmb.com.good.model.entity.products.Products;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.math3.stat.descriptive.summary.Product;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "document")
public class Document extends BaseEntity {

    private String file_type;
    private String file_name;
    private String file_size;
    private String description;
    private Long record_id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_Id", insertable = false, updatable = false)
    private Products products;

    public Document(){}


}
