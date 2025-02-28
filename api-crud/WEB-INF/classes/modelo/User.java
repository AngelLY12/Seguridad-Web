package modelo;
public class User {
    private String rfc;
    private String name;
    private String lastName;
    private String password;
    private String profile;

    public User(String rfc, String name, String lastName,String password){
        this.rfc=rfc;
        this.name=name;
        this.lastName=lastName;
        this.password=password;
        this.profile="USER";
    }
    public User(){

    }
    public User(String rfc, String password){
        this.rfc=rfc;
        this.password=password;
    }

    public String getRFC(){return rfc;}
    public String getName(){return name;}
    public String getLastName(){return lastName;}
    public String getPassword(){return password;}
    public String getProfile(){return profile;}
    public void setRFC(String rfc){this.rfc=rfc;}
    public void setName(String name){this.name=name;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public void setPassword(String password){this.password=password;}
    public void setProfile(String profile) {
    if (profile == "ADMIN") {
        throw new SecurityException("No puedes asignar el rol de ADMIN manualmente.");
    }
    this.profile = profile.toUpperCase();
    }
    @Override
    public String toString(){
        return "Usuario{" +
        "RFC=" + rfc +
        ", nombre='" + name + '\'' +
        ", Apellido='" + lastName + '\'' +
        ", profile='" + profile + '\'' + 

        '}';
    }

}
