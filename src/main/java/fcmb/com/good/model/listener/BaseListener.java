package fcmb.com.good.model.listener;

import fcmb.com.good.model.entity.assets.Assets;
import fcmb.com.good.model.entity.assets.AssetsCategory;
import fcmb.com.good.model.entity.assets.DamagedAssets;
import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.model.entity.others.Hotel;
import fcmb.com.good.model.entity.products.*;
import fcmb.com.good.model.entity.rooms.RoomType;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.services.ServiceRequest;
import fcmb.com.good.model.entity.services.Services;
import fcmb.com.good.model.entity.transaction.*;
import fcmb.com.good.model.entity.user.*;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.util.UUID;

@Component
public class BaseListener {

    @PrePersist
    private void beforeCreate(Object data) {
        if(data instanceof Rooms){
            Rooms rooms = (Rooms) data;
            rooms.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Assets){
            Assets assets = (Assets) data;
            assets.setUuid(UUID.randomUUID());
        }
        else if(data instanceof AssetsCategory){
            AssetsCategory assetsCategory = (AssetsCategory) data;
            assetsCategory.setUuid(UUID.randomUUID());
        }
        else if(data instanceof DamagedAssets){
            DamagedAssets damagedAssets = (DamagedAssets) data;
            damagedAssets.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Document){
            Document document = (Document) data;
            document.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Hotel){
            Hotel hotel = (Hotel) data;
            hotel.setUuid(UUID.randomUUID());
        }
        else if(data instanceof ProductOrder){
            ProductOrder productOrder = (ProductOrder) data;
            productOrder.setUuid(UUID.randomUUID());
        }
        else if(data instanceof ProductOrderItems){
            ProductOrderItems productOrderItems = (ProductOrderItems) data;
            productOrderItems.setUuid(UUID.randomUUID());
        }
        else if(data instanceof ProductPurchase){
            ProductPurchase productPurchase = (ProductPurchase) data;
            productPurchase.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Product){
            Product product = (Product) data;
            product.setUuid(UUID.randomUUID());
        }
        else if(data instanceof RoomType){
            RoomType roomType = (RoomType) data;
            roomType.setUuid(UUID.randomUUID());
        }
        else if(data instanceof ServiceRequest){
            ServiceRequest serviceRequest = (ServiceRequest) data;
            serviceRequest.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Services){
            Services services = (Services) data;
            services.setUuid(UUID.randomUUID());
        }
        else if(data instanceof AccountCategory){
            AccountCategory accountCategory = (AccountCategory) data;
            accountCategory.setUuid(UUID.randomUUID());
        }
        else if(data instanceof AccountChart){
            AccountChart accountChart = (AccountChart) data;
            accountChart.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Booking){
            Booking booking = (Booking) data;
            booking.setUuid(UUID.randomUUID());
        }
        else if(data instanceof BookingReminder){
            BookingReminder bookingReminder = (BookingReminder) data;
            bookingReminder.setUuid(UUID.randomUUID());
        }
        else if(data instanceof ExpenseRequest){
            ExpenseRequest expenseRequest = (ExpenseRequest) data;
            expenseRequest.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Expenses){
            Expenses expenses = (Expenses) data;
            expenses.setUuid(UUID.randomUUID());
        }
        else if(data instanceof MaintenanceRequest){
            MaintenanceRequest maintenanceRequest = (MaintenanceRequest) data;
            maintenanceRequest.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Payment){
            Payment payment = (Payment) data;
            payment.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Customer){
            Customer customer = (Customer) data;
            customer.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Employee){
            Employee employee = (Employee) data;
            employee.setUuid(UUID.randomUUID());
        }
        else if(data instanceof EmployeeShift){
            EmployeeShift employeeShift = (EmployeeShift) data;
            employeeShift.setUuid(UUID.randomUUID());
        }
        else if(data instanceof Role){
            Role role = (Role) data;
            role.setUuid(UUID.randomUUID());
        }
        else if(data instanceof AppUser){
            AppUser user = (AppUser) data;
            user.setUuid(UUID.randomUUID());
        }

        else if(data instanceof UserType){
            UserType type = (UserType) data;
            type.setUuid(UUID.randomUUID());
        }

        else if(data instanceof ProductCategory){
            ProductCategory productCategory = (ProductCategory) data;
            productCategory.setUuid(UUID.randomUUID());
        }

    }








}
