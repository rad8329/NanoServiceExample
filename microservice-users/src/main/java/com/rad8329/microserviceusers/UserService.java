package com.rad8329.microserviceusers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final List<User> list = new ArrayList<>(
            Arrays.asList(
                    new User(1, "Robert", "Díaz", "rad8329@gmail.com"),
                    new User(2, "Juan", "Díaz", "carlangas@gmail.com"),
                    new User(3, "Edwin", "Díaz", "edwin@gmail.com")
            )
    );

    List<User> getAll() {
        return list;
    }

    boolean addOne(User user) {
        return list.add(user);
    }

    Optional<User> getOne(int id) {
        return list.stream().filter(u -> u.getId() == id).findFirst();
    }

    Optional<User> removeOne(int id) {
        Optional<User> user = getOne(id);

        if (user.isPresent()) {
            list.remove(user.get());

            return user;
        }

        return user;
    }

    Optional<User> updateOne(int id, User userToUpdate) {

        Optional<User> user = Optional.empty();

        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getId() == id) {
                user = Optional.of(list.get(index));
                list.set(index, userToUpdate);

                return user;
            }
        }

        return user;
    }
}
