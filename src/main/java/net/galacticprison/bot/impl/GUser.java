package net.galacticprison.bot.impl;

import net.dv8tion.jda.api.entities.User;

public class GUser implements net.galacticprison.bot.api.guser.GUser {

    private User dUser;
    private long points;

    public GUser(User dUser, long points) {
        this.dUser = dUser;
        this.points = points;
    }

    @Override
    public void addPoints(long amount) {
        setPoints(getPoints() + amount);
    }

    @Override
    public void removePoints(long amount) {
        if(getPoints() - amount < 0) amount = getPoints();
        setPoints(getPoints() - amount);
    }

    @Override
    public String getDUserID() {
        return dUser.getId();
    }

    @Override
    public String getDUserName() {
        return dUser.getName();
    }

    public User getdUser() {
        return dUser;
    }

    public void setdUser(User dUser) {
        this.dUser = dUser;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

}
