package com.kmac_enterprises.chialphaapp;

/**
 * Created by Kelsey Maclin on 12/23/2015.
 */
public class Admin extends Person{

    public Admin(String f_n, String l_n, String email, String campus) {
        super(f_n, l_n, email, campus, AdminPermissions.ADMIN);
    }
}
