package com.company;

public class PublishingRetailer {
    private int ID;
    private String name;
    private IPublishingArtifact[] publishingArtifacts;
    private Countries[] countries;

    public PublishingRetailer(int ID, String name, IPublishingArtifact[] publishingArtifacts, Countries[] countries) {
        this.ID = ID;
        this.name = name;
        this.publishingArtifacts = publishingArtifacts;
        this.countries = countries;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IPublishingArtifact[] getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public void setPublishingArtifacts(IPublishingArtifact[] publishingArtifacts) {
        this.publishingArtifacts = publishingArtifacts;
    }

    public Countries[] getCountries() {
        return countries;
    }

    public void setCountries(Countries[] countries) {
        this.countries = countries;
    }
}
