import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:k4r_client/component/request_error_interceptor.dart';
import 'package:k4r_client/component/request_uris.dart';

class ApiCaller {
  static const String _host = "http://localhost:8080";
  static Dio? _instance;

  ApiCaller() {
    _instance ??= Dio(BaseOptions(
      baseUrl: _host,
      connectTimeout: const Duration(seconds: 5),
      receiveTimeout: const Duration(seconds: 5),
    ));
    _instance?.interceptors.add(RequestErrorInterceptor());
  }

  Future<Response>? signin(
      String identifier, String password, bool neverExpire) {
    return _instance?.post(RequestUris.signin.uri,
        data: {"identifier": identifier, "password": password});
  }

  Future<Response>? signup(String username, String nickname, String password, String email) {
    return _instance?.post(RequestUris.signup.uri,
        data: {"username": username, "password": password,"nickname": nickname, "email": email});
  }

  Future<Response>? isUsernameExists(String username) {
    return _instance?.get(RequestUris.is_username_exists.uri,
        queryParameters: {"username": username});
  }

  Future<Response>? isEmailExists(String email) {
    return _instance?.get(RequestUris.is_email_exists.uri,
        queryParameters: {"email": email});
  }

  Future<void> clearCookies() async {
    dynamic result = await this.isEmailExists("email");
    return result;
  }
}
