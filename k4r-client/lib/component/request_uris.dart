enum RequestUris {
  signin('/auth/authentication/no-auth/sign-in'),
  signup('/auth/authentication/no-auth/sign-up'),
  updateProfile("/users/user/profile"),
  getProfile("/users/user/profile");

  final String uri;

  const RequestUris(this.uri);
}
