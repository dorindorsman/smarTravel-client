package com.dorin.smartravel;

import com.dorin.smartravel.Objects.DayTrip;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.retrofit.RetrofitService;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import retrofit2.Retrofit;

public class DataManger {

    private static DataManger single_Instance_dataManger=null;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private GoogleSignInAccount account;

    private Trip currentTrip;
    private DayTrip currentDayTrip;
    private RetrofitService retrofitService;

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    private DataManger() {




        if(retrofitService==null)
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


    public DayTrip getCurrentDayTrip() {
        return currentDayTrip;
    }

    public DataManger setCurrentDayTrip(DayTrip currentDayTrip) {
        this.currentDayTrip = currentDayTrip;
        return this;
    }

    public GoogleSignInClient getmGoogleSignInClient() {
        return mGoogleSignInClient;
    }

    public DataManger setmGoogleSignInClient(GoogleSignInClient mGoogleSignInClient) {
        this.mGoogleSignInClient = mGoogleSignInClient;
        return this;
    }

    public GoogleSignInOptions getGso() {
        return gso;
    }

    public DataManger setGso(GoogleSignInOptions gso) {
        this.gso = gso;
        return this;
    }

    public GoogleSignInAccount getAccount() {
        return account;
    }

    public DataManger setAccount(GoogleSignInAccount account) {
        this.account = account;
        return this;
    }
}
