package net.galacticprison.bot.impl;

import net.galacticprison.bot.GalacticBot;
import net.galacticprison.bot.api.guser.GUserProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GUserFactory implements GUserProvider {

    private static List<GUser> gUserCache;
    private GUserDatabase gUserDatabase;

    public GUserFactory() {
        gUserCache = new ArrayList<>();
        gUserDatabase = GalacticBot.getGUserDatabase();
    }

    @Override
    public void loadGUser(String userID) {
        if(!cacheContains(userID))
            gUserCache.add(gUserDatabase.getUser(userID));
    }

    @Override
    public void unloadGUser(String userID) {
        if(cacheContains(userID)) {
            if (getGUserByID(userID).isPresent())
                gUserCache.remove(getGUserByID(userID).get());
        }
    }

    @Override
    public boolean cacheContains(String userID) {
        return getGUserByID(userID).isPresent();
    }

    @Override
    public Optional<GUser> getGUserByID(String userID) {
        return gUserCache.stream().filter(gUser -> gUser.getdUser().getId().equals(userID)).findAny();
    }

    @Override
    public Optional<GUser> getGUserByName(String username) {
        return gUserCache.stream().filter(gUser -> gUser.getdUser().getName().equals(username)).findAny();
    }

    @Override
    public List<GUser> getGUserListByPoints(long pointMinimum) {
        return gUserCache.stream().filter(gUser -> gUser.getPoints() >= pointMinimum).collect(Collectors.toList());
    }

    @Override
    public List<GUser> getGUserList() {
        return gUserCache;
    }
}
