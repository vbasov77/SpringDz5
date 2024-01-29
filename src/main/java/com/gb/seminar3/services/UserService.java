package com.gb.seminar3.services;

import com.gb.seminar3.models.User;
import com.gb.seminar3.models.User;
import com.gb.seminar3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Сортировка по возрасту
     */
    public List<User> sortUserByAge(List<User> people) {
        return people.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Сортировка по критериям возраста
     */
    public List<User> filterUserByAge(List<User> people, int age) {
        return people.stream().filter(User -> User.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * Средний возраст
     */
    public double calculateAverageAge(List<User> people) {
        return people.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteObjById(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    public void edit(String name, Integer age, Long id) {
        userRepository.edit(name, age, id);
    }

    public void save(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        userRepository.save(user);
    }
}
