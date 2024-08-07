import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:k4r_client/pages/home_page.dart';
import 'package:k4r_client/pages/sign_in_page.dart';
import 'package:k4r_client/pages/sign_up_page.dart';
import 'package:k4r_client/providers/access_token_provider.dart';
import 'package:k4r_client/providers/logged_sate_provider.dart';
import 'package:provider/provider.dart';

import 'key/global_keys.dart';

void main() {
  runApp(MultiProvider(
    providers: [
      ChangeNotifierProvider(create: (context) => LoggedSateProvider()),
      ChangeNotifierProvider(create: (context) => AccessTokenProvider()),
    ],
    child: MaterialApp.router(
      debugShowCheckedModeBanner: false,
      debugShowMaterialGrid: false,
      scaffoldMessengerKey: GlobalKeys.scaffoldMessengerKey,
      routerConfig: GoRouter(
          routes: [
            GoRoute(path: "/", builder: (context, state) => const HomePage()),
            GoRoute(
              path: '/sign-in',
              builder: (context, state) => SignInPage(),
            ),
            GoRoute(
                path: "/sign-up",
                builder: (context, state) => const SignUpPage()),
          ],
          redirect: (context, state) {
            final LoggedSateProvider loggedState =
            Provider.of<LoggedSateProvider>(context,listen: false);
            Set<Uri> whiteList = {Uri.parse('/sign-in'), Uri.parse('/sign-up')};
            if (!loggedState.isLoggedIn && !whiteList.contains(state.uri)) {
              print("current user has not logged,redirect to login page.");
              return '/sign-in';
            }
          }),
    ),
  ));
}