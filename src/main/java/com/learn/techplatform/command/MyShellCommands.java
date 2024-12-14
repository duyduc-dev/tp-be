package com.learn.techplatform.command;

import com.learn.techplatform.entities.User;
import com.learn.techplatform.firebase.FirebaseService;
import com.learn.techplatform.firebase.modals.PushNotification;
import com.learn.techplatform.repositories.UserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MyShellCommands {

    private final UserRepository userRepository;
    private final FirebaseService firebaseService;

    public MyShellCommands(UserRepository userRepository, FirebaseService firebaseService) {
        this.userRepository = userRepository;
        this.firebaseService = firebaseService;
    }

    @ShellMethod("Push notification")
    public String pushNotification(String id) {
        User user = this.userRepository.findById(id).orElse(null);

        if(user != null) {
            this.firebaseService.pushNotification(PushNotification.builder()
                    .userId(user.getId())
                    .title("Test title")
                    .body("Test body")
                    .build());
            return "Hello " + user.getFirstName();
        }
        return "Not FOUND";
    }
}
