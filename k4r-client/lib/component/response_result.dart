import 'dart:convert';

import 'package:dio/dio.dart';

Result<T> parseResponse<T>(Response response) {
  Result<T> result = Result<T>();
  result.errorCode = response.data["errorCode"];
  result.message = response.data["message"];
  result.data = response.data["data"];
  return result;
}

class Result<T> {
  int? errorCode;
  String? message;
  T? data;
}
