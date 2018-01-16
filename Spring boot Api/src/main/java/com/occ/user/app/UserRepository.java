package com.occ.user.app;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import com.occ.user.register.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserRepository {
	private static final Map<String, User> users = new HashMap<>();

	@PostConstruct
	public void initData() {
		User john = new User();
        john.setName("John");
		john.setCpf("1131231455");
		john.setAge("25");

		users.put(john.getName(), john);

		User bob = new User();
        bob.setName("Bob");
        bob.setCpf("12315156671");
        bob.setAge("19");

		users.put(bob.getName(), bob);

	}

	public User findUser(String name) {
		Assert.notNull(name, "The user's name must not be null");
		return users.get(name);
	}
}
