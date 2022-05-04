package com.dorin.smartravel;

import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.retrofit.RetrofitService;

import retrofit2.Retrofit;

public class DataManger {

    private static DataManger single_Instance_dataManger=null;
    private Trip currentTrip;
    private RetrofitService retrofitService;

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    private DataManger() {
        retrofitService=new RetrofitService();
    }

    public static DataManger getInstance(){
        return single_Instance_dataManger;
    }

    public static DataManger initHelper(){
        if(single_Instance_dataManger==null)
            single_Instance_dataManger= new DataManger();

        return single_Instance_dataManger;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public DataManger setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
        return this;
    }


}
