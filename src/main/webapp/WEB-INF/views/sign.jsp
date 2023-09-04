<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>롯데 ON : 회원가입/로그인</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'><link rel="stylesheet" href="./style.css">

  <!-- Css Styles -->
  <link rel="stylesheet" href="../../static/css/userstyle.css" type="text/css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

  <!-- jQuery -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <section id="formHolder">
        <div class="row">
            <!-- Brand Box -->
            <div class="col-sm-6 brand">
              <a href="#" class="logo">LOTTE ON <span>.</span></a>
              <div class="heading">
                <h2>LOTTE ON</h2>
                <p>Enjoy Shopping</p>
              </div>
            </div>
            <!-- Form Box -->
            <div class="col-sm-6 form">
                <!-- Login Form -->
                <div class="login form-peice">
                  <form class="login-form" action="/user/sign-in" method="post">
                    <div class="form-group">
                      <label for="loginEmail">Email Adderss</label>
                      <input type="email" name="userEmail" id="loginEmail" required>
                    </div>
                    <div class="form-group">
                      <label for="loginPassword">Password</label>
                      <input type="password" name="password" id="loginPassword" required>
                    </div>
                    <div class="CTA">
                      <input type="submit" value="Login">
                      <a href="#" class="switch">계정 생성</a>
                    </div>
                  </form>
                </div>
                <!-- End Login Form -->
                <!-- Signup Form -->
                <div class="signup form-peice switched">
                  <form class="signup-form" action="/user/sign-up" method="post">
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" name="userEmail" id="email" class="email">
                      <span class="error"></span>
                    </div>

                    <div class="form-group">
                      <label for="password">Password</label>
                      <input type="password" name="password" id="password" class="pass">
                      <span class="error"></span>
                    </div>

                    <div class="form-group">
                      <label for="passwordCon">Confirm Password</label>
                      <input type="password" name="passwordCon" id="passwordCon" class="passConfirm">
                      <span class="error"></span>
                    </div>

                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" name="userName" id="name" class="name">
                      <span class="error"></span>
                    </div>

                    <div class="form-group">
                      <label for="phone">Phone Number</label>
                      <input type="text" name="phoneNumber" id="phone">
                      <span class="error"></span>
                    </div>

                    <div class="form-group">
                      <label for="address">Address</label>
                      <input type="text" name="address" id="address" class="address">
                      <input type="text" name="addressDetail" id="address_detail">
                      <span class="error"></span>
                    </div>

                    <div class="CTA">
                      <input type="submit" value="Signup Now">
                      <a href="#" class="switch">로그인</a>
                    </div>
                  </form>
                </div>
                <!-- End Signup Form -->
            </div>
        </div>
    </section>
</div>
<script src="../../static/js/userscript.js"></script>
</body>