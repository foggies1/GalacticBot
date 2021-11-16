package net.galacticprison.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.galacticprison.bot.impl.GUserDatabase;
import net.galacticprison.bot.impl.GUserFactory;

import javax.security.auth.login.LoginException;

public class GalacticBot {

    private static JDA jda;
    private static GUserDatabase gUserDatabase;
    private static GUserFactory gUserFactory;

    public static void main(String[] args) throws LoginException {

        jda = JDABuilder.createDefault("token")
                .setActivity(Activity.listening("noobs"))
                .addEventListeners()
                .build();

        gUserDatabase = new GUserDatabase(jda,"localhost", "3306", "users", "root", "");
        gUserFactory = new GUserFactory();

    }

    public static GUserDatabase getGUserDatabase() {
        return gUserDatabase;
    }

    public static GUserFactory getGUserFactory() {
        return gUserFactory;
    }

    public static JDA getJda() {
        return jda;
    }
}
