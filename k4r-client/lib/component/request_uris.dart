enum RequestUris {
  signin('/auth/authentication/no-auth/sign-in'),
  signup('/auth/authentication/no-auth/sign-up'),
  is_username_exists("/user/user/no-auth/username/exists"),
  is_email_exists("/user/user/no-auth/email/exists");

  final String uri;

  const RequestUris(this.uri);
}
