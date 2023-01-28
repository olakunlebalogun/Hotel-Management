package fcmb.com.good.model.entity.assets;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.user.AppUser;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "assetsCategory")
public class AssetsCategory extends BaseEntity {

    private String name;
    private String type;
    private String description;
    private String account_no;

    @OneToMany(mappedBy = "assetsCategory")
    private List<Assets> assets_list;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy_id", updatable = true)
    private AppUser createdBy;

    public AssetsCategory(){

    }



}
