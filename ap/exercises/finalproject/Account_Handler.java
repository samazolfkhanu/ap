package ap.exercises.finalproject;

public interface Account_Handler<T>{
    T authenticateUser(String username,String password);
    T getAUser(String username);
}
