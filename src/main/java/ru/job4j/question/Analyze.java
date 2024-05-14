package ru.job4j.question;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {

        int added = 0;
        int changed = 0;

        Map<Integer, User> prevUserMap = new HashMap<>();
        for (User user : previous) {
            prevUserMap.put(user.getId(), user);
        }

        for (User currentUser : current) {
            User prevUser = prevUserMap.get(currentUser.getId());
            if (prevUser == null) {
                added++;
            } else {
                if (!prevUser.getName().equals(currentUser.getName())) {
                    changed++;
                }
                prevUserMap.remove(currentUser.getId());
            }
        }

        return new Info(added, changed, prevUserMap.size());
    }

}