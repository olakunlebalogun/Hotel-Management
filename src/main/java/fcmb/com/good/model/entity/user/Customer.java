package fcmb.com.good.model.entity.user;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.BaseUser;
import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.model.entity.products.Products;
import fcmb.com.good.model.entity.services.Services;
import fcmb.com.good.model.entity.transaction.AccountChart;
import fcmb.com.good.model.entity.transaction.Booking;
import fcmb.com.good.model.entity.transaction.Payment;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EntityListeners(BaseListener.class)
@Table(name="customer")
@AllArgsConstructor
public class Customer extends BaseUser {

    private String nin;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Booking> roomsList;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Document document;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Products> productsList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Services> servicesList;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private AccountChart accountChart;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Payment> paymentList;


    public Customer(){

    }


}
