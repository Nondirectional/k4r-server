import 'package:dio/dio.dart';
import 'package:k4r_client/component/response_result.dart';
import 'package:k4r_client/component/snack_bar_reminder.dart';
import 'package:k4r_client/key/global_keys.dart';

class RequestErrorInterceptor extends Interceptor {
  @override
  void onError(DioException err, ErrorInterceptorHandler handler) {
    // 处理错误
    switch (err.type) {
      case DioExceptionType.badResponse:
        // 根据状态码处理
        switch (err.response?.statusCode) {
          case 400:
            SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
                GlobalKeys.scaffoldMessengerKey,
                RemindType.error,
                "Request fail,message: ${err.response?.data['message']}",
                const Duration(milliseconds: 1500));
            break;
          // 其他状态码...
          default:
            SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
                GlobalKeys.scaffoldMessengerKey,
                RemindType.error,
                "Request fail,message: $err.message",
                const Duration(milliseconds: 1500));
        }
        break;
      default:
        // 默认错误处理
        break;
    }

    if (err.response != null) {
      var response = err.response!;
      Result<String> result = parseResponse(response.data);
      if (result.errorCode != 0) {
        SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
            GlobalKeys.scaffoldMessengerKey,
            RemindType.error,
            "Request fail,message: ${result.message}",
            const Duration(milliseconds: 1500));
      }
      handler.resolve(err.response!);
    } else {
      // 继续传递错误
      // handler.next(err);
    }
  }

  @override
  void onResponse(Response response, ResponseInterceptorHandler handler) {
    Result<String> result = parseResponse(response);
    response.data = result;
    if (result.errorCode != 0) {
      SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
          GlobalKeys.scaffoldMessengerKey,
          RemindType.error,
          "${result.message}",
          const Duration(milliseconds: 1500));
      handler.reject(DioException(requestOptions: response.requestOptions));
      return;
    }
    handler.next(response);
  }
}
