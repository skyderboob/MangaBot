package database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by skyderboob on 1/21/14.
 */
public class ReadingUser {
    private String email;
    private ArrayList<String> watchingSeries;
    private ArrayList<String> watchingGroups;
    public static ArrayList<ReadingUser> userDatabase;
    private static final String DATABASE_FILE = "userDatabase.json";

    public static void loadDatabase() throws IOException {
        File f = new File(DATABASE_FILE);
        if (!f.exists())
            System.out.println("Can't find file.");
        ObjectMapper mapper = new ObjectMapper();
        userDatabase = mapper.readValue(new File(DATABASE_FILE), new TypeReference<ArrayList<ReadingUser>>() {
        });
    }

    public static void updateDatabase() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(DATABASE_FILE), userDatabase);
    }

    public ReadingUser() {
    }

    public ReadingUser(String email, ArrayList<String> watchingSeries, ArrayList<String> watchingGroups) {
        this.email = email;
        this.watchingSeries = watchingSeries;
        this.watchingGroups = watchingGroups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getWatchingSeries() {
        return watchingSeries;
    }

    public void setWatchingSeries(ArrayList<String> watchingSeries) {
        this.watchingSeries = watchingSeries;
    }

    public ArrayList<String> getWatchingGroups() {
        return watchingGroups;
    }

    public void setWatchingGroups(ArrayList<String> watchingGroups) {
        this.watchingGroups = watchingGroups;
    }

    @Override
    public String toString() {
        String seriesList = StringUtils.join(watchingSeries, ", ");
        String groupList = StringUtils.join(watchingGroups, ", ");
        return email + "\n" + seriesList + "\n" + groupList;
    }

    public boolean isWatchingSeries(String seriesName) {
        return watchingSeries.contains(seriesName);
    }

    public boolean isWatchGroup(String groupName) {
        return watchingGroups.contains(groupName);
    }
}
