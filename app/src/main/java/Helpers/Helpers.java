package Helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SQLLiteHelpers.SQLite_OpenHelperUsers;

public class Helpers {
    public static boolean doesStringMatchRegexp(String string, String regexp){
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        boolean hasNumbers = matcher.find();
        return hasNumbers;
    }

    public static boolean LoadStatesAndDistricts(String string, String regexp){
        SQLite_OpenHelperUsers
    }
}