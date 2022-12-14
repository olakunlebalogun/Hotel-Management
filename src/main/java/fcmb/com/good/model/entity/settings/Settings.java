package fcmb.com.good.model.entity.settings;

import fcmb.com.good.model.entity.BaseEntity;

import javax.persistence.Entity;


//@Entity
public class Settings extends BaseEntity {

    private String description;

    private Category category;
    private String postedBy;


}
