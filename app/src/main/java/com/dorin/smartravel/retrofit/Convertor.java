package com.dorin.smartravel.retrofit;

import com.dorin.smartravel.Objects.User;
import com.dorin.smartravel.serverObjects.CreatedBy;
import com.dorin.smartravel.serverObjects.DomainWithEmail;
import com.dorin.smartravel.serverObjects.InstanceBoundary;
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
        instanceBoundary.setCreatedBy(new CreatedBy(new DomainWithEmail(currentUser.getDomain(),currentUser.getEmail())));
        instanceBoundary.setLocation(new Location(0.0,0.0)); // FIXME: 5/20/2022 location track don't work!!!
        Map<String, Object> map = new LinkedTreeMap<>();
        map.put("firstName",currentUser.getFirstName());
        map.put("lastName",currentUser.getLastName());
        instanceBoundary.setInstanceAttributes(map);
        return instanceBoundary;
    }








}
