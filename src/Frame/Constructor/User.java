package Frame.Constructor;

public class User {
    private int id;
    private String name;
    private String surname;
    private String company;
    private String job;
    private String email;
    private String numberphone;
    private String pathIcon;
    private String speciality;
    private String password;

    public User(int id, String name, String surname, String company, String job, String email, String phoneNumber, String pathIcon, String speciality, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.company = company;
        this.job = job;
        this.email = email;
        this.numberphone = phoneNumber;
        this.pathIcon = pathIcon;
        this.speciality  = speciality;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCompany() {
        return company;
    }

    public String getJob() {
        return job;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public String getPathIcon(){
        return pathIcon;
    }
    public String getSpeciality() {
        return speciality;
    }
    public String getPassword() {
        return password;
    }

    

}
