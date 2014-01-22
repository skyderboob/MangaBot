import database.Database;
import database.ReadingUser;
import database.ScanlatingGroup;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by skyderboob on 1/22/14.
 */
public class DatabaseTest {
    @Before
    public void initObjects() throws IOException {
        ScanlatingGroup.loadDatabase();
        ReadingUser.loadDatabase();
    }

    @Test
    public void testGroupDatabase() throws IOException {
        ArrayList<String> test = Database.getEmailsOfUsersWatchingRelease("aaa1", "aaa");
//        ScanlatingGroup.updateGroupInfoFromRelease("aaa", "Japflap");
//        ScanlatingGroup.updateGroupInfoFromRelease("aaa", "asddsaasd");
//        ScanlatingGroup.updateDatabase();
        System.out.println(StringUtils.join(test, "\n"));
    }
}
