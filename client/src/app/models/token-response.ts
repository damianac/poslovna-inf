export interface TokenResponse {
  accessToken: string;
  profileDto: {
    username: string
    authorities: string[]
  };
}
