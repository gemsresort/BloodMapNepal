package com.example.saroj.layout;

/**
 * Created by shudeepslove on 8/8/2016.
 */
public class Config {

    //Address of our scripts of the CRUD

    public static final String DATA_URL = "http://192.168.1.111/jsondemo/getData.php?id=";
    public static final String RETURN_LOCATION = "http://192.168.1.111/registerdemo/locationreturn.php?id=";
   // public static final String UPDATE_LOCATION = "http://192.168.0.142/registerdemo/updatelocation.php?id=";
   public static final String DATA_URL1 = "http://192.168.1.111/image/feed.php?page=";
    //  public static final String id = "http://192.168.0.100/image/";

    //Tags for my JSON
    public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_NAME1 = "name";
    //  public static final String TAG_RANK = "rank";
      public static final String TAG_DESC = "des";
    public static final String TAG_CREATED_BY = "createdBy";
    //   public static final String TAG_FIRST_APPEARANCE = "firstAppearance";
    //   public static final String TAG_POWERS = "powers";
    //  public static final String URL_GET_EMP = "http://192.168.0.103/simplifiedcrud/getEmp.php?id=";
    //   public static final String TAG_JSON_ARRAY="result";
    public static final String URL_ADD="http://192.168.1.111/Android/CRUD/addEmp.php";

    public static final String URL_GET_ALL = "http://192.168.1.111/simplifiedcrud/getAllEmp.php?";

    public static final String URL_GET_EMP = "http://192.168.1.111/simplifiedcrud/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.111/Android/CRUD/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.111/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";

    public static final String JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PHONE = "pno";
    public static final String TAG_PHONE1 = "pno1";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String bloodgroup = "emp_id";
    public static final String EMP_ID = "emp_id";
    public static final String EMP_PHONE = "emp_phone";

    //josndemo

    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "lat";
    public static final String KEY_VC = "lng";
    public static final String KEY_PHONE = "pno";

    public static final String TAG_VC = "vc";



}
