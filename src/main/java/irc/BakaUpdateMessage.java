package irc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by skyderboob on 1/21/14.
 */
public class BakaUpdateMessage {

    private static Pattern BAKA_MESSAGE_PATTERN;
    public static BakaUpdateMessage BLANK_MESSAGE;

    static {
        //Example: (v can be omiited)
        // - New Release: Kill No More (Novel) v.6 c.4 [Japtem]
        // - New Release: Living Like a Flower c.9 [Sura`s Place]
        BAKA_MESSAGE_PATTERN = Pattern.compile("New Release: (.+?) ((v.[0-9]+ )?c.[0-9]+) \\[(.+)\\]");
        BLANK_MESSAGE = new BakaUpdateMessage("", "", "");
    }

    private String seriesName;
    private String releasedChapter;
    private String group;

    public BakaUpdateMessage(String seriesName, String releasedChapter, String group) {
        this.seriesName = seriesName;
        this.releasedChapter = releasedChapter;
        this.group = group;
    }

    public static void main(String[] args) {
        BakaUpdateMessage bu = tryParse("New Release: Living Like a Flower c.9 [Sura`s Place]");
    }

    public static BakaUpdateMessage tryParse(String message) {
        Matcher m = BAKA_MESSAGE_PATTERN.matcher(message);
        if (!m.matches())
            return BLANK_MESSAGE;
        else return new BakaUpdateMessage(message);
    }

    private BakaUpdateMessage(String message) {
        Matcher matcher = BAKA_MESSAGE_PATTERN.matcher(message);
        while (matcher.find()) {
            setSeriesName(matcher.group(1));
            setReleasedChapter(matcher.group(2));
            setGroup(matcher.group(4));
        }
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getReleasedChapter() {
        return releasedChapter;
    }

    public void setReleasedChapter(String releasedChapter) {
        this.releasedChapter = releasedChapter;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String toString() {
        return String.format("%s has released %s of %s.", group, releasedChapter, seriesName);
    }
}
