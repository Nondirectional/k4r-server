import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:k4r_client/component/api_caller.dart';
import 'package:k4r_client/providers/logged_sate_provider.dart';
import 'package:provider/provider.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({super.key});

  @override
  SignUpPageState createState() => SignUpPageState();
}

class SignUpPageState extends State<SignUpPage> {
  final _formKey = GlobalKey<FormState>();
  late String _username;
  late String _nickname;
  late String _password;
  late String _email;

  bool loggedIn = false;

  void _submitForm() async {
    if (_formKey.currentState!.validate()) {
      _formKey.currentState?.save();
      ApiCaller apiCaller = ApiCaller();
      Response? response = await apiCaller.signup(_username,_nickname, _password, _email);
      if (response != null) {
        print(response.data);
        setState(() {
          loggedIn = true;
          LoggedSateProvider loggedSate =
              Provider.of<LoggedSateProvider>(context, listen: false);
          loggedSate.login();
          GoRouter.of(context).go('/');
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text('注册'),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Padding(
                padding:
                    const EdgeInsets.symmetric(vertical: 22, horizontal: 16.0),
                child: TextFormField(
                  decoration: const InputDecoration(labelText: '用户名'),
                  validator: (value) {
                    if (value!.isEmpty) {
                      return '请输入用户名';
                    }
                    return null;
                  },
                  onFieldSubmitted: (value) =>
                      FocusScope.of(context).nextFocus(),
                  onChanged: (value) => _username = value,
                ),
              ),
              Padding(
                padding:
                const EdgeInsets.symmetric(vertical: 22, horizontal: 16.0),
                child: TextFormField(
                  decoration: const InputDecoration(labelText: '昵称'),
                  validator: (value) {
                    if (value!.isEmpty) {
                      return '请输入昵称';
                    }
                    return null;
                  },
                  onFieldSubmitted: (value) =>
                      FocusScope.of(context).nextFocus(),
                  onChanged: (value) => _nickname = value,
                ),
              ),
              Padding(
                  padding: const EdgeInsets.symmetric(
                      vertical: 22, horizontal: 16.0),
                  child: TextFormField(
                    decoration: const InputDecoration(labelText: '密码'),
                    obscureText: true,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return '请输入密码';
                      }
                      return null;
                    },
                    onFieldSubmitted: (value) =>
                        FocusScope.of(context).nextFocus(),
                    onChanged: (value) => _password = value!,
                  )),
              Padding(
                  padding: const EdgeInsets.symmetric(
                      vertical: 22, horizontal: 16.0),
                  child: TextFormField(
                    decoration: const InputDecoration(labelText: '确认密码'),
                    obscureText: true,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return '请再次确认密码';
                      }
                      if (value != _password) {
                        return "两次密码输入不一致";
                      }
                      return null;
                    },
                    onFieldSubmitted: (value) =>
                        FocusScope.of(context).nextFocus(),
                  )),
              Padding(
                  padding: const EdgeInsets.symmetric(
                      vertical: 22, horizontal: 16.0),
                  child: TextFormField(
                    decoration: const InputDecoration(labelText: '邮箱'),
                    obscureText: false,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return '请输入邮箱';
                      }
                      // 检查是否合法邮箱
                      RegExp emailRegExp = RegExp(
                        r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$',
                        caseSensitive: false,
                      );
                      if (!emailRegExp.hasMatch(value!)) {
                        return '请输入合法邮箱';
                      }

                      return null;
                    },
                    onFieldSubmitted: (value) => _submitForm(),
                    onChanged: (value) => _email = value,
                  )),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 26.0,vertical: 22.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    MaterialButton(
                      padding: const EdgeInsets.symmetric(horizontal: 36.0),
                      color: Colors.black38,
                      onPressed: () {
                        GoRouter.of(context).go('/sign-in');
                      },
                      child: const Text('返回登录'),
                    ),
                    MaterialButton(
                      padding: const EdgeInsets.symmetric(horizontal: 36.0),
                      color: Colors.black38,
                      onPressed: _submitForm,
                      child: const Text('注册'),
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
