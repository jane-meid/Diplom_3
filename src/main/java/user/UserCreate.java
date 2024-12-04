package user;

import com.github.javafaker.Faker;

public class UserCreate {
    public static User getRandomUser(){
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(10, 15);
        final String name = faker.name().fullName();
        return new User(email, password, name);
    }

    public static User getRandomUserWithWrongPassword(){
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(4, 6);
        final String name = faker.name().fullName();
        return new User(email, password, name);
    }
}
