package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.domain.users.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.Map;

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
        assertThat(response.getLocation().toString().matches(".*/[a-zA-Z\\d]+$"), is(true));
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
        Map userInfo = response.readEntity(Map.class);
        assertThat(userInfo.get("uri").toString(), containsString(getOneUrl));
        assertThat(userInfo.get("id"), is(user.get_id()));
        assertThat(userInfo.get("name"), is(user.getName()));

    }

    @Test
    public void should_404_when_get_user_given_not_exist() {
        User user = prepareUser(userRepository);
        String getOneUrl = usersBaseUrl + "/" + new ObjectId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));

    }
}
