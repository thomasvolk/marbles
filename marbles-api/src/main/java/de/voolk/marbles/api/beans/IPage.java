package de.voolk.marbles.api.beans;

public interface IPage extends IIdentifiable {
    String getContent();

    String getName();

    boolean isRoot();
}
