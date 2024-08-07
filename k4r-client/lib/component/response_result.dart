import 'dart:convert';

import 'package:dio/dio.dart';

Result<T> parseResponse<T>(Response response) {
  Map? data;
  if (response.data is Result) {
    return response.data;
  } else {
    data = response.data;
  }
  if (data == null) {
    throw Exception("No data found in response");
  }
  Result<T>? result = Result();
  result.message =
      data.containsKey("message") ? response.data["message"] : null;
  result.errorCode =
      data.containsKey("errorCode") ? response.data["errorCode"] : null;
  result.data = data.containsKey("data") ? response.data["data"] : null;
  return result;
}

class Result<T> {
  int? errorCode;
  String? message;
  T? data;
}
