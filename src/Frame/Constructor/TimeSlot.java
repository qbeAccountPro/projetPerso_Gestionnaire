package Frame.Constructor;


public class TimeSlot {
    private String description;
    private String localisation;
    private String user;
    private String date;
    private String hourStart;
    private String hourEnd;

    public TimeSlot(String description, String localisation, String user, String date, String hourStart,
            String hourEnd) {
        this.description = description;
        this.localisation = localisation;
        this.user = user;
        this.date = date;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
    }

    public String getDescription() {
        return description;
    }
    public String getLocalisation() {
        return localisation;
    }
    public String getUser() {
        return user;
    }
    public String getDate() {
        return date;
    }
    public String getHourStart() {
        return hourStart;
    }
  
    public String getHourEnd() {
        return hourEnd;
    }
    
}
