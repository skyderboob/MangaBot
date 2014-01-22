package irc;

import database.Database;
import database.ReadingUser;
import mail.SpamMailBot;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

public class IRCBotListener extends ListenerAdapter {

    @Override
    public void onMessage(MessageEvent event) throws Exception {
        if (event.getUser().getNick().equals("skyderai")) {
            BakaUpdateMessage bu = BakaUpdateMessage.tryParse(event.getMessage());
            if (!bu.equals(BakaUpdateMessage.BLANK_MESSAGE)) {
                ArrayList<String> recipients = Database.getEmailsOfUsersWatchingRelease(bu.getSeriesName(), bu.getGroup());
                String subject = String.format("New chapter of %s", bu.getSeriesName());
                String body = bu.toString();
                for (String recipient : recipients) {
                    SpamMailBot.sendMail(recipient, subject, body);
                    event.getBot().sendIRC().message("#baka-bot", String.format("Sent mail to %s about %s", recipient, subject));
                    Thread.sleep(2000);
                }
            }
        }

        if (event.getUser().getNick().equals("BU|Bot")) {
            BakaUpdateMessage bu = BakaUpdateMessage.tryParse(event.getMessage());
            if (!bu.equals(BakaUpdateMessage.BLANK_MESSAGE)) {
                event.getBot().sendIRC().message("#baka-bot", event.getMessage());
                ArrayList<String> recipients = Database.getEmailsOfUsersWatchingRelease(bu.getSeriesName(), bu.getGroup());
                String subject = String.format("New chapter of %s", bu.getSeriesName());
                String body = bu.toString();
                for (String recipient : recipients) {
                    SpamMailBot.sendMail(recipient, subject, body);
                    event.getBot().sendIRC().message("#baka-bot", String.format("Sent mail to %s about %", recipient, subject));
                    Thread.sleep(2000);
                }
            }
        }
    }

    public static void main(String[] args) {
        String test = "New Release: Kill No More (Novel) v.6 c.4 [Japtem]";
        //new IRCBotListener().extractMessage(test);
    }
}
