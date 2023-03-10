package fcmb.com.good.model.entity.assets;


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
@Table(name = "damagedAsset")
public class DamagedAssets extends BaseEntity {

    private Long asset_id;
    private Long asset_category_id;
    private String quantity;
    private String status;
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assetsId", insertable = false, updatable = false)
    private Assets assets;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assetsCategoryId", insertable = false, updatable = false)
    private AssetsCategory assetsCategory;


    public DamagedAssets(){

    }

}
