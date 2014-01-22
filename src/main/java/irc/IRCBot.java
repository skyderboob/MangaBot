package irc;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by skyderboob on 1/21/14.
 */
public class IRCBot {

    private static PircBotX ircBot;

    static {
        Configuration configuration = new Configuration.Builder()
                .setName("AyaseAragaki")
                .setLogin("LQ")
                .setAutoNickChange(true)
                .setCapEnabled(true)
                .addListener(new IRCBotListener())
                .setServerHostname("irc.irchighway.net")
                .addAutoJoinChannel("#baka-bot")
                .addAutoJoinChannel("#baka-updates")
                .buildConfiguration();
        ircBot = new PircBotX(configuration);
    }

    public static void start() throws IOException, IrcException {
        ircBot.startBot();
    }

    public static void main(String[] args) throws IOException, IrcException {
        IRCBot.start();
    }
}
