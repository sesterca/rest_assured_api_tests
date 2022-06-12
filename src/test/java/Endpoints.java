public enum Endpoints {

    REGISTER("/api/register"),
    LOGIN("/api/login"),
    USERS("/api/users");

    public String endpoint;

    Endpoints(String endpoint){
        this.endpoint = endpoint;
    }
}
