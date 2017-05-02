package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 4/24/2017.
 */
public class Airport {
    protected String name;
    protected String ID;
    protected String continent;
    protected String fullContinent;
    protected String longitude;
    protected String latitude;
    protected String elevation;
    protected String isoCountry;
    protected String countryName;
    protected String isoRegion;
    protected String municipality;
    protected String scheduledService;
    protected String gpsCode;
    protected String iataCode;
    protected String localCode;
    protected String homeLink;
    protected String wikipediaLink;
    protected String keywords;
    protected String type;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
        convertToFullContinent();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String iso_country) {
        this.isoCountry = iso_country;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public void setIsoRegion(String iso_region) {
        this.isoRegion = iso_region;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getScheduledService() {
        return scheduledService;
    }

    public void setScheduledService(String scheduledService) {
        this.scheduledService = scheduledService;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public void setGps_code(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getHomeLink() {
        return homeLink;
    }

    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public void setWikipediaLink(String wikipediaLink) {
        this.wikipediaLink = wikipediaLink;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFullContinent() {
        return fullContinent;
    }

    public void setFullContinent(String fullContinent) {
        this.fullContinent = fullContinent;
    }

    protected void convertToFullContinent(){
        if(continent.equals("NA"))
            fullContinent = "North America";
        else if(continent.equals("AF"))
            fullContinent = "Africa";
        else if(continent.equals("AN"))
            fullContinent = "Antarctica";
        else if(continent.equals("AS"))
            fullContinent = "Asia";
        else if(continent.equals("EU"))
            fullContinent = "Europe";
        else if(continent.equals("OC"))
            fullContinent = "Oceania";
        else if(continent.equals("SA"))
            fullContinent = "South America";
    }
}
