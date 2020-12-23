package com.test.coursech.domain.interactor;

public class AuthInteractor {
    public boolean checkAuth(String login, String pass) {
        return !login.equals("") || !pass.equals("");
    }
}
