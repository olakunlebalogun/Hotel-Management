package fcmb.com.good.utills.EndPoints;



public class ProductEndPoints {

    public static final String USERS="USERS";

    public static final String PRODUCT_ORDER_ITEMS="/productOrderItems";
    public static final String FIND_PRODUCT_ORDER_ITEMS=PRODUCT_ORDER_ITEMS+"/list";
    public static final String ADD_PRODUCT_ORDER_ITEMS=PRODUCT_ORDER_ITEMS+"/add";
    public static final String FIND_PRODUCT_ORDER_ITEMS_BY_ID=PRODUCT_ORDER_ITEMS+"/{id}";
    public static final String UPDATE_PRODUCT_ORDER_ITEMS= PRODUCT_ORDER_ITEMS+"/update/{id}";
    public static final String DELETE_PRODUCT_ORDER_ITEMS= PRODUCT_ORDER_ITEMS+"/customerDelete/{id}";

    public static final String PRODUCT_ORDER="/productOrder";
    public static final String FIND_PRODUCT_ORDER=PRODUCT_ORDER+"/list";
    public static final String ADD_PRODUCT_ORDER= PRODUCT_ORDER+"/add";
    public static final String FIND_PRODUCT_ORDER_BY_ID= PRODUCT_ORDER+"/{id}";
    public static final String UPDATE_PRODUCT_ORDER= PRODUCT_ORDER+"/update/{id}";
    public static final String DELETE_PRODUCT_ORDER= PRODUCT_ORDER+"/delete/{id}";

    public static final String PRODUCT_PURCHASE="/productPurchase";
    public static final String FIND_PRODUCT_PURCHASE=PRODUCT_PURCHASE+"/list";
    public static final String ADD_PRODUCT_PURCHASE= PRODUCT_PURCHASE+"/add";
    public static final String FIND_PRODUCT_PURCHASE_BY_ID= PRODUCT_PURCHASE+"/{id}";
    public static final String UPDATE_PRODUCT_PURCHASE= PRODUCT_PURCHASE+"/update/{id}";
    public static final String DELETE_PRODUCT_PURCHASE= PRODUCT_PURCHASE+"/delete/{id}";
    public static  final  String LIST_PRODUCT_PURCHASE_BY_DAY= FIND_PRODUCT_PURCHASE +"/by_day";
    public static  final  String LIST_PRODUCT_PURCHASE_BY_DATE_RANGE= FIND_PRODUCT_PURCHASE +"/date_range";

    public static final String PRODUCT="/products";
    public static final String FIND_PRODUCT=PRODUCT+"/list";
    public static final String ADD_PRODUCT= PRODUCT+"/add";
    public static final String FIND_PRODUCT_BY_ID= PRODUCT+"/{id}";
    public static final String UPDATE_PRODUCT= PRODUCT+"/update/{id}";
    public static final String DELETE_PRODUCT= PRODUCT+"/delete/{id}";
    public static final String SEARCH_PRODUCT_BY_CATEGORY= PRODUCT+"/searchProductListByCategory";
    public static final String SEARCH_PRODUCT_BY_NAME= PRODUCT+"/searchProductListByName";


    public static final String PRODUCT_CATEGORY="/productCategory";
    public static final String FIND_PRODUCT_CATEGORY=PRODUCT_CATEGORY+"/list";
    public static final String ADD_PRODUCT_CATEGORY= PRODUCT_CATEGORY+"/add";
    public static final String FIND_PRODUCT_CATEGORY_BY_ID= PRODUCT_CATEGORY+"/{id}";
    public static final String UPDATE_PRODUCT_CATEGORY= PRODUCT_CATEGORY+"/update/{id}";
    public static final String DELETE_PRODUCT_CATEGORY= PRODUCT_CATEGORY+"/delete/{id}";


}
