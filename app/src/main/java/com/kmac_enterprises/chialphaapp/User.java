package com.kmac_enterprises.chialphaapp;

/**
 * Created by Kelsey Maclin on 12/23/2015.
 */
public class User extends Person {

    public User(String f_n, String l_n, String email, String campus) {
        super(f_n, l_n, email, campus, AdminPermissions.USER);
    }

}
