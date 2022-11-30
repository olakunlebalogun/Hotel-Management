package fcmb.com.good.model.entity.assets;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(BaseListener.class)
@AllArgsConstructor
@Table(name = "assets")
public class Assets extends BaseEntity {

    private String category_id;
    private String asset_name;
    private Double value_cost;
    private String description;
    private Long record_id;
    private String record_type;
    private String code;
    private String Location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assetsCategoryId", insertable = false, updatable = false)
    private AssetsCategory assets_category;

    public Assets (){

    }


}
