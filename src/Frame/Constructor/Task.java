package Frame.Constructor;

public class Task {
    private int id;
    private String description;
    private String deadline;
    private String duration;
    private String priority;
    private String thematic;
    private String assignementBy;
    private String assignementFor;
    private Boolean archive;
    private String dateArchive;
    private String archiveBy;

    public Task(int id, String description, String deadline, String duration, String priority, String thematic,
            String assignementBy, String assignementFor, Boolean archive, String dateArchive, String archiveBy) {
    this.id = id;
    this.description = description;
    this.deadline = deadline;
    this.duration = duration;
    this.priority = priority;
    this.thematic = thematic;
    this.assignementBy = assignementBy;
    this.assignementFor = assignementFor;
    this.archive = archive;
    this.dateArchive = dateArchive;
    this.archiveBy = archiveBy;
}

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDuration() {
        return duration;
    }

    public String getPriority() {
        return priority;
    }

    public String getThematic() {
        return thematic;
    }

    public String getAssignementBy() {
        return assignementBy;
    }

    public String getAssignementFor() {
        return assignementFor;
    }

    public Boolean getArchive() {
        return archive;
    }

    public String getDateArchive() {
        return dateArchive;
    }

    public String getArchiveBy() {
        return archiveBy;
    } 

    
}
