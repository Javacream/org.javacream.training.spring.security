import { AuthConfig } from 'angular-oauth2-oidc';

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8160/auth/realms/javacream',
  redirectUri: window.location.origin + '/index.html',
  clientId: 'javacream_client',
  responseType: 'code',
  scope: 'openid profile email',
  showDebugInformation: true,
  dummyClientSecret: "83cd4cab-fc8b-4b0f-a5b9-ce90600dd0c6",
  timeoutFactor: 0.01
};
