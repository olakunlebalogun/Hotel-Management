package fcmb.com.good.model.entity.settings;

import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.settings.enums.Currency;
import fcmb.com.good.model.entity.settings.enums.Department;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category extends BaseEntity {

    private String designation;
    private Department department;
    private Currency currency;
}
