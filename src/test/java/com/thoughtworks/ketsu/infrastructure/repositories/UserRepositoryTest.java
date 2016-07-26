package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.domain.users.UserRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_get_user() {
        User user = userRepository.save(userJsonForTest(USER_NAME));
        Optional<User> fetched = userRepository.findById(user.get_id());

        assertThat(fetched.isPresent(), is(true));
    }
}
