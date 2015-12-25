package com.kmac_enterprises.chialphaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kelsey Maclin on 12/23/2015.
 */
final class Bridge {

    private static HashMap<Person, ArrayList<Object>> users = new HashMap<Person, ArrayList<Object>>();
    private static Context context;
    private static Person current_user;

    public static void clearUser() {
        current_user = null;
    }

    public static Person getCurrentUser() {
        return current_user;
    }

    public static void setCurrentUser(Person user) {
        current_user = user;
    }

    public static void populate(){
        if (users.size() != 0) {
            return;
        }

        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Admin("Kelsey", "Maclin", "keoyome@yahoo.com", "GeorgiaTech"));
        people.add(new Admin("Jay", "Karwatsky", "jay@perfect.com", "GeorgiaTech"));

        int passHash = "awesome1".hashCode();
        for (Person p: people){
            Bridge.addMember(p, passHash);
        }
    }

    public static HashMap<Person, ArrayList<Object>> getUsers() {
        return users;
    }

    public static void setContext(Context context) {
        Bridge.context = context;
    }

    public static boolean addMember(Person per, int passHash) {
        if (per != null && passHash != 0) {
            ArrayList<Object> myList = new ArrayList<>();
            myList.add(per);
            Integer i = passHash;
            myList.add(i);
            users.put(per, myList);
            return true;
        }
        return false;
    }

    public static Person authenticateMembership(Person per, int passHash) {
        if (users.containsKey(per)) {
            ArrayList<Object> value = users.get(per);
            if ((int)value.get(1) == passHash) {
                Bridge.current_user = (Person)value.get(0);
                return Bridge.current_user;
            }
            return null;
        }
        return null;
    }

}
