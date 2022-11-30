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
@Table(name = "roomType")
public class RoomType extends BaseEntity {

    private String room_type;
    private String description;
    private String cost;
    private String status;

    @OneToMany(mappedBy = "room_type", fetch = FetchType.LAZY)
    private List<Rooms> roomsList;

    public RoomType(){}



}
