package ua.com.anyapps.test.Server.Users;

public class Location {
    private String country;

    private String city;

    private Street street;

    private Timezone timezone;

    private String postcode;

    private Coordinates coordinates;

    private String state;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public Street getStreet ()
    {
        return street;
    }

    public void setStreet (Street street)
    {
        this.street = street;
    }

    public Timezone getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (Timezone timezone)
    {
        this.timezone = timezone;
    }

    public String getPostcode ()
    {
        return postcode;
    }

    public void setPostcode (String postcode)
    {
        this.postcode = postcode;
    }

    public Coordinates getCoordinates ()
    {
        return coordinates;
    }

    public void setCoordinates (Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", city = "+city+", street = "+street+", timezone = "+timezone+", postcode = "+postcode+", coordinates = "+coordinates+", state = "+state+"]";
    }
}
