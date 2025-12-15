package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.AbstractControllerTests;
import com.kolosov.learnjava_jc_spring.common.errors.exceptions.ResourceNotFoundException;
import com.kolosov.learnjava_jc_spring.jsonView.controllers.UserController;
import com.kolosov.learnjava_jc_spring.jsonView.models.User;
import com.kolosov.learnjava_jc_spring.jsonView.services.UserService;
import com.kolosov.learnjava_jc_spring.common.views.View;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest extends AbstractControllerTests {
    private static final String REST_URL_USERS_LIST = UserController.REST_URL;
    private static final String REST_URL_USER_BY_ID = UserController.REST_URL + "/";
    private static final String REST_URL_CREATE_USER = UserController.REST_URL;
    private static final String REST_URL_UPDATE_USER = UserController.REST_URL;
    private static final String REST_URL_DELETE_USER = UserController.REST_URL;

    @MockitoBean
    UserService userService;

    @Test
    void usersList() throws Exception {
        List<User> expectedUsers = List.of(
                UserControllerTestData.user1WithOrders,
                UserControllerTestData.user2WithOrders
        );
        String expectedJson = jsonHelper.writeValue(expectedUsers, View.UserSummary.class);

        when(userService.getAll()).thenReturn(expectedUsers);

        perform(get(REST_URL_USERS_LIST))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        verify(userService, times(1)).getAll();
    }

    @Test
    void user_whenUserExist() throws Exception {
        User expectedUser = UserControllerTestData.user1WithOrders;
        String expectedJson = jsonHelper.writeValue(expectedUser, View.UserDetails.class);

        when(userService.getById(expectedUser.getId())).thenReturn(expectedUser);

        perform(get(REST_URL_USER_BY_ID + expectedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        verify(userService, times(1)).getById(expectedUser.getId());
    }

    @Test
    void user_whenUserNotExist() throws Exception {
        long userId = 999;

        when(userService.getById(userId)).thenReturn(null);

        perform(get(REST_URL_USER_BY_ID + userId))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getById(userId);
    }

    @Test
    void create() throws Exception {
        User user = UserControllerTestData.newUser;
        String requestBody = jsonHelper.writeValue(user);

        MockHttpServletRequestBuilder post = post(REST_URL_CREATE_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        perform(post).andExpect(status().isOk());

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void create_whenInvalidRequestBody() throws Exception {
        User user = UserControllerTestData.invalidUser;
        String requestBody = jsonHelper.writeValue(user);

        MockHttpServletRequestBuilder post = post(REST_URL_CREATE_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        perform(post).andExpect(status().isBadRequest());

        verify(userService, times(0)).save(any(User.class));
    }

    @Test
    void update() throws Exception {
        User user = UserControllerTestData.user1WithoutOrders;
        String requestBody = jsonHelper.writeValue(user);

        MockHttpServletRequestBuilder post = put(REST_URL_UPDATE_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        perform(post).andExpect(status().isOk());

        verify(userService, times(1)).update(eq(user.getId()), any(User.class));
    }

    @Test
    void update_whenInvalidRequestBody() throws Exception {
        User user = UserControllerTestData.invalidUser;
        String requestBody = jsonHelper.writeValue(user);

        MockHttpServletRequestBuilder request = put(REST_URL_UPDATE_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        perform(request).andExpect(status().isBadRequest());

        verify(userService, times(0)).update(anyLong(), any(User.class));
    }

    @Test
    void update_whenUserNotExist() throws Exception {
        User user = UserControllerTestData.user1WithoutOrders;
        String requestBody = jsonHelper.writeValue(user);

        when(userService.update(eq(user.getId()), any(User.class))).thenThrow(ResourceNotFoundException.class);

        MockHttpServletRequestBuilder request = put(REST_URL_UPDATE_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        perform(request).andExpect(status().isNotFound());

        verify(userService, times(1)).update(eq(user.getId()), any(User.class));
    }

    @Test
    void delete() throws Exception {
        long userId = 1;

        perform(MockMvcRequestBuilders.delete(REST_URL_DELETE_USER + "/" + userId))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteById(userId);
    }
}