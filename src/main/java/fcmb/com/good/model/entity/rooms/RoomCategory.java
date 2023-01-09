package fcmb.com.good.model.entity.rooms;

import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "roomCategory")
public class RoomCategory extends BaseEntity {

    private String category;
    private String description;
    private String cost;
    private String status;

    @OneToMany(mappedBy = "roomCategory", fetch = FetchType.LAZY)
    private List<Rooms> roomsList;

    public RoomCategory(){}



}
