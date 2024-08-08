import 'package:dio/dio.dart';
import 'package:k4r_client/component/response_result.dart';
import 'package:k4r_client/component/snack_bar_reminder.dart';
import 'package:k4r_client/global.dart';

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
            print("Request fail,message: ${err.response?.data['message']}");
            break;
          // 其他状态码...
          default:
            SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
                GlobalKeys.scaffoldMessengerKey,
                RemindType.error,
                "Request fail,message: $err.message",
                const Duration(milliseconds: 1500));
            print("Request fail,message: ${err.response?.data['message']}");
        }
        break;
      default:
        // 默认错误处理
        SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
            GlobalKeys.scaffoldMessengerKey,
            RemindType.error,
            "Request fail,message: $err.message",
            const Duration(milliseconds: 1500));
        print("Request fail,message: ${err.response?.data['message']}");
        break;
    }

    if (err.response != null) {
      var response = err.response!;
      Result? result = parseResponse(response.data);
      if (result.errorCode != 0) {
        SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
            GlobalKeys.scaffoldMessengerKey,
            RemindType.error,
            "Request fail,message: ${result.message}",
            const Duration(milliseconds: 1500));
      }
      handler.resolve(err.response!);
    } else {
      handler.next(err);
    }
  }

  @override
  void onResponse(Response response, ResponseInterceptorHandler handler) {
    Result? result = parseResponse(response);

    response.data = result;
    if (result.errorCode != 0) {
      SnackBarReminder.showSnackBarRemindByScaffoldMessengerState(
          GlobalKeys.scaffoldMessengerKey,
          RemindType.error,
          "${result.message}",
          const Duration(milliseconds: 1500));
      handler.reject(DioException(requestOptions: response.requestOptions));
    } else {
      handler.next(response);
    }
  }
}
