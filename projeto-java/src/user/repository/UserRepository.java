package user.repository;

import user.model.User;

public interface UserRepository {

    public void signUp(User user);

    public void login(String userName, String password);

    public void updateUser(User user);

    public void deleteUserById();


    public void transfer(int senderId, int receiverId, double value, int productId, int quantity);

}
