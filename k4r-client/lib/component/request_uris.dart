enum RequestUris {
  signin('/auth/authentication/no-auth/sign-in'),
  signup('/auth/authentication/no-auth/sign-up');
  final String uri;

  const RequestUris(this.uri);
}