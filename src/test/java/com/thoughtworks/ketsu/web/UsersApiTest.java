package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.domain.users.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    private String usersBaseUrl = "/users";
    @Test
    public void should_register_user() {
        Response response = post(usersBaseUrl, userJsonForTest(USER_NAME));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(usersBaseUrl));
        assertThat(response.getLocation().toString().matches(".*users/.*$"), is(true));
    }

    @Test
    public void should_400_when_input_invalid() {
        Response response = post(usersBaseUrl, userJsonForTest(INVALID_USER_NAME));

        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void should_get_one_user() {
        User user = prepareUser(userRepository);
        String getOneUrl = usersBaseUrl + "/" + user.get_id();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));

    }
}
