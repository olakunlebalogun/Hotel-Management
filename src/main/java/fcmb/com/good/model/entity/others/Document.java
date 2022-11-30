package fcmb.com.good.model.entity.others;

import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    public Document(){}


}
