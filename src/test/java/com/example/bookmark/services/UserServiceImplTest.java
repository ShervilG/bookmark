package com.example.bookmark.services;

import com.example.bookmark.BaseTest;
import com.example.bookmark.entities.Token;
import com.example.bookmark.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UserServiceImplTest extends BaseTest {

  @Mock
  TokenService tokenService;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  public void testAuthenticateToken() {
    Token testToken = new Token().toBuilder().token("abc").build();
    Mockito.when(tokenService.getToken(Mockito.any())).thenReturn(testToken);
    Assert.assertTrue(userService.authenticateUserToken("abc"));
  }
}
