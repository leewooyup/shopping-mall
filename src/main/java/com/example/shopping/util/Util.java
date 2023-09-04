package com.example.shopping.util;

import com.example.shopping.domain.consumer.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
    public static class Session {
        public static Consumer getUser(HttpSession session) {
            return (Consumer)session.getAttribute("login_user");
        }
    }
}
