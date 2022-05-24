package com.dorin.smartravel.retrofit;

import android.util.Log;

import com.dorin.smartravel.Objects.User;
import com.dorin.smartravel.serverObjects.ActivityBoundary;
import com.dorin.smartravel.serverObjects.CreatedBy;
import com.dorin.smartravel.serverObjects.DomainWithEmail;
import com.dorin.smartravel.serverObjects.DomainWithId;
import com.dorin.smartravel.serverObjects.Instance;
import com.dorin.smartravel.serverObjects.InstanceBoundary;
import com.dorin.smartravel.serverObjects.InvokedBy;
import com.dorin.smartravel.serverObjects.Location;
import com.dorin.smartravel.serverObjects.UserBoundary;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

public class Convertor{


    public static UserBoundary convertUserToUserBoundary(User user) {
        UserBoundary userBoundary = new UserBoundary();
        userBoundary.setEmail(user.getEmail())
                .setUsername(user.getFirstName()+user.getLastName());
        userBoundary.setRole(user.getRole());
        userBoundary.setAvatar(user.getAvatar());
        return userBoundary;
    }



    public static InstanceBoundary convertUserToInstanceBoundary(User currentUser) {
        InstanceBoundary instanceBoundary = new InstanceBoundary();
        instanceBoundary.setType("user");
        instanceBoundary.setName(currentUser.getEmail());
        instanceBoundary.setActive(true);
        instanceBoundary.setCreatedBy(new CreatedBy(new DomainWithEmail("2022b.maya.gembom","manager@email")));
        Log.d("roman",instanceBoundary.getCreatedBy().getUserId()+"");
        instanceBoundary.setLocation(new Location(0.0,0.0)); // FIXME: 5/20/2022 location track don't work!!!
        Map<String, Object> map = new LinkedTreeMap<>();
        map.put("firstName",currentUser.getFirstName());
        map.put("lastName",currentUser.getLastName());
        instanceBoundary.setInstanceAttributes(map);
        return instanceBoundary;
    }


    public static ActivityBoundary convertToActivityBoundary(String instanceDomain,String instanceId,String userDomain,String userEmail,String type){
        ActivityBoundary activityBoundary = new ActivityBoundary();
        activityBoundary.setInstance(new Instance(new DomainWithId(instanceDomain,instanceId)));
        activityBoundary.setInvokedBy(new InvokedBy(new DomainWithEmail(userDomain,userEmail)));
        activityBoundary.setType(type);
        return activityBoundary;
    }





}
