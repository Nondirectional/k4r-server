import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:k4r_client/component/request_error_interceptor.dart';
import 'package:k4r_client/component/request_uris.dart';
import 'package:k4r_client/providers/access_token_provider.dart';
import 'package:provider/provider.dart';

class ApiCaller {
  static const String _host = "http://localhost:8080";
  static final ApiCaller _instance = ApiCaller._internal();
  late Dio _dio;

  ApiCaller._internal() {
    _dio = Dio(BaseOptions(
      baseUrl: _host,
      connectTimeout: const Duration(seconds: 5),
      receiveTimeout: const Duration(seconds: 5),
    ));
    _dio.interceptors.add(RequestErrorInterceptor());
    _dio.interceptors.add(
      InterceptorsWrapper(
        onRequest: (options, handler) {
          // 动态获取AccessToken
          final context = options.extra['context'] != null
              ? options.extra['context'] as BuildContext
              : null;

          if (context != null) {
            final tokenProvider =
                Provider.of<AccessTokenProvider>(context, listen: false);
            print(tokenProvider.accessToken);
            // 将AccessToken添加到请求头中
            options.headers['Access-Token'] = tokenProvider.accessToken;
          }

          return handler.next(options); // 继续请求
        },
      ),
    );
  }

  factory ApiCaller([BuildContext? context]) {
    if (context != null) {
      _instance._dio.options.extra = {'context': context};
    }
    return _instance;
  }

  Future<Response>? signin(
      String identifier, String password, bool neverExpire) {
    return _dio.post(RequestUris.signin.uri,
        data: {"identifier": identifier, "password": password});
  }

  Future<Response>? signup(
      String username, String nickname, String password, String email) {
    return _dio.post(RequestUris.signup.uri, data: {
      "username": username,
      "password": password,
      "nickname": nickname,
      "email": email
    });
  }

  Future<Response>? getProfile() {
    return _dio.get(RequestUris.getProfile.uri);
  }

  Future<Response>? updateProfile(FormData formData) {
    return _dio.put(RequestUris.updateProfile.uri, data: formData);
  }


}
