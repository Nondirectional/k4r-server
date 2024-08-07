import 'dart:convert';

import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:k4r_client/component/request_error_interceptor.dart';
import 'package:k4r_client/component/request_uris.dart';

class ApiCaller {
  static const String _host = "http://192.168.1.141:8080";
  static Dio? _instance;
  static final CookieJar _cookieJar = CookieJar();

  ApiCaller() {
    _instance ??= Dio(BaseOptions(
      baseUrl: _host,
      connectTimeout: const Duration(seconds: 5),
      receiveTimeout: const Duration(seconds: 5),
    ));
    _instance?.interceptors.add(CookieManager(_cookieJar));
    _instance?.interceptors.add(RequestErrorInterceptor());
  }

  Future<Response>? signin(
      String identifier, String password, bool neverExpire) {
    return _instance?.post(RequestUris.signin.uri,
        data: {"identifier": identifier, "password": password});
  }

  Future<Response>? signup(String username, String password, String email) {
    return _instance?.post(RequestUris.signup.uri,
        data: {"username": username, "password": password, "email": email});
  }
}



