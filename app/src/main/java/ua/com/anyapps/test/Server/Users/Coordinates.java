package ua.com.anyapps.test.Server.Users;

public class Coordinates {
    private String latitude;

    private String longitude;

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [latitude = "+latitude+", longitude = "+longitude+"]";
    }
}
