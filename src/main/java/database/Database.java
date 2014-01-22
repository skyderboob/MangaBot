package database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by skyderboob on 1/21/14.
 */
public class Database {

    private static ArrayList<ScanlatingGroup> groupDatabase;

    static {
        try {
            ScanlatingGroup.loadDatabase();
            ReadingUser.loadDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> getEmailsOfUsersWatchingRelease(String seriesName, String groupName) {
        ArrayList<String> emailList = new ArrayList<String>();
        for (ReadingUser u : ReadingUser.userDatabase) {
            if (u.isWatchGroup(groupName) || u.isWatchingSeries(seriesName)) {
                emailList.add(u.getEmail());
            }
        }
        return emailList;
    }

    public static void updateGroupDatabase (String seriesName, String groupName) {
        ScanlatingGroup.updateGroupInfoFromRelease(seriesName, groupName);
    }
}
