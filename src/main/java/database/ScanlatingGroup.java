package database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by skyderboob on 1/22/14.
 */
public class ScanlatingGroup {
    private String name;
    private ArrayList<String> scanlatingSeries;

    public static ArrayList<ScanlatingGroup> groupDatabase;

    private static final String DATABASE_FILE = "groupDatabase.json";

    public static void loadDatabase() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        groupDatabase = mapper.readValue(new File(DATABASE_FILE), new TypeReference<ArrayList<ScanlatingGroup>>() {
        });
    }

    public static void updateDatabase() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(DATABASE_FILE), groupDatabase);
    }

    public ScanlatingGroup() {}

    public ScanlatingGroup(String name, ArrayList<String> scanlatingSeries) {
        this.name = name;
        this.scanlatingSeries = scanlatingSeries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getScanlatingSeries() {
        return scanlatingSeries;
    }

    public void setScanlatingSeries(ArrayList<String> scanlatingSeries) {
        this.scanlatingSeries = scanlatingSeries;
    }

    public String toString() {
        String series = StringUtils.join(scanlatingSeries, ", ");
        return String.format("%s is scanlating: %s", name, series);
    }

    public boolean isScanlatingSeries(String seriesName) {
        return scanlatingSeries.contains(seriesName);
    }

    public static ScanlatingGroup getGroupFromGroupName(String groupName) {
        for (ScanlatingGroup g : groupDatabase) {
            if (g.getName().equals(groupName))
                return g;
        }
        return null;
    }

    public static boolean isGroupExists(String groupName) {
        ScanlatingGroup g = getGroupFromGroupName(groupName);
        return g == null;
    }

    public static void updateGroupInfoFromRelease(String seriesName, String groupName) {
        ScanlatingGroup g = getGroupFromGroupName(groupName);
        if (g != null) {
            if (!g.isScanlatingSeries(seriesName))
                g.scanlatingSeries.add(seriesName);
        } else {
            ArrayList<String> scanlatingSeries = new ArrayList<String>();
            scanlatingSeries.add(seriesName);
            g = new ScanlatingGroup(groupName, scanlatingSeries);
            groupDatabase.add(g);
        }
    }
}
