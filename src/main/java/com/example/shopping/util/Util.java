package com.example.shopping.util;

import com.example.shopping.domain.user.Consumer;

import javax.servlet.http.HttpSession;

public class Util {
    public static class Session {
        public static Consumer getUser(HttpSession session) {
            return (Consumer)session.getAttribute("login_user");
        }
    }
}
