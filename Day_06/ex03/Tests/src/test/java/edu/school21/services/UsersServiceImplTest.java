package edu.school21.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;


public class UsersServiceImplTest {
	private final String CORRECT_LOGIN = "GOOD_LOGIN";
	private final String INCORRECT_LOGIN = "BAD_LOGIN";
	private final String CORRECT_PASSWORD = "GOOD_PASSWORD";
	private final String INCORRECT_PASSWORD = "BAD_PASSWORD";
	private User user;

	private final UsersRepository usersRepositoryMock = mock(UsersRepository.class);
	private final UsersServiceImpl usersServiceMock = new UsersServiceImpl(usersRepositoryMock);

	@BeforeEach()
	public void init() {
		user = new User(1, CORRECT_LOGIN, CORRECT_PASSWORD, false);
		when(usersRepositoryMock.findByLogin(CORRECT_LOGIN)).thenReturn(user);
		doNothing().when(usersRepositoryMock).update(user);
	}

	@Test
	public void correctLoginAndPasswordTest() throws AlreadyAuthenticatedException, EntityNotFoundException {
		Assertions.assertTrue(usersServiceMock.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD));
		verify(usersRepositoryMock).findByLogin(CORRECT_LOGIN);
		verify(usersRepositoryMock).update(user);
	}

	@Test
	public void incorrectLoginTest() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> usersServiceMock.authenticate(INCORRECT_LOGIN, CORRECT_PASSWORD));
		verify(usersRepositoryMock).findByLogin(INCORRECT_LOGIN);
		verify(usersRepositoryMock, never()).update(user);
	}

	@Test
	public void incorrectPasswordTest() throws AlreadyAuthenticatedException, EntityNotFoundException {
		Assertions.assertFalse(usersServiceMock.authenticate(CORRECT_LOGIN, INCORRECT_PASSWORD));
		verify(usersRepositoryMock).findByLogin(CORRECT_LOGIN);
		verify(usersRepositoryMock, never()).update(any());
	}
}