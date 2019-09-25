package com.epam.service;

import com.epam.dao.UserCreating;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserParserServiceTest {
    private UserParserService service = new UserParserService();

    @Test
    public void fromString() {
        //GIVEN
        String userBeforeParsing = "login=root&password=1234567890&firstName=123&lastName=123&age=123";
        UserCreating expectedUser = new UserCreating("root", "1234567890", "123", "123", 123);

        //WHEN
        UserCreating actualUser = service.fromString(userBeforeParsing);

        //THEN
        assertThat(actualUser, is(equalTo(expectedUser)));
    }
}