package fcmb.com.good.utills.EndPoints;


public class AssetsEndPoints {

    public static final String USERS="USERS";

    public static final String ASSETSCATEGORY="/assetsCategory";
    public static final String FIND_ASSETSCATEGORY=ASSETSCATEGORY+"/list";
    public static final String ADD_ASSETSCATEGORY=ASSETSCATEGORY+"/add";
    public static final String FIND_ASSETSCATEGORY_BY_ID=ASSETSCATEGORY+"/{id}";
    public static final String UPDATE_ASSETSCATEGORY= ASSETSCATEGORY+"/update/{id}";
    public static final String DELETE_ASSETSCATEGORY= ASSETSCATEGORY+"/customerDelete/{id}";

    public static final String ASSET="/assets";
    public static final String FIND_ASSET=ASSET+"/list";
    public static final String ADD_ASSET=ASSET+"/add";
    public static final String FIND_ASSET_BY_ID=ASSET+"/{id}";
    public static final String UPDATE_ASSET= ASSET+"/update/{id}";
    public static final String DELETE_ASSET= ASSET+"/customerDelete/{id}";


    public static final String DAMAGED_ASSET="/damagedAssets";
    public static final String FIND_DAMAGED_ASSET=DAMAGED_ASSET+"/list";
    public static final String ADD_DAMAGED_ASSET=DAMAGED_ASSET+"/add";
    public static final String FIND_DAMAGED_ASSET_BY_ID=DAMAGED_ASSET+"/{id}";
    public static final String UPDATE_DAMAGED_ASSET= DAMAGED_ASSET+"/update/{id}";
    public static final String DELETE_DAMAGED_ASSET= DAMAGED_ASSET+"/customerDelete/{id}";

}
