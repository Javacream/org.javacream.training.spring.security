import { AuthConfig } from 'angular-oauth2-oidc';

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://h2908727.stratoserver.net:8170/openam/oauth2/realms/javacream',
  redirectUri: window.location.origin + '/index.html',
  clientId: 'javacream_client',
  responseType: 'code',
  scope: 'openid profile email',
  showDebugInformation: true,
  dummyClientSecret: "javacream_client123!",
  timeoutFactor: 0.01,
  requireHttps: false
};

const authCodeFlowConfig_keycloak: AuthConfig = {
  issuer: 'http://localhost:8160/auth/realms/techem_realm',
  redirectUri: window.location.origin + '/index.html',
  clientId: 'techem_client',
  responseType: 'code',
  scope: 'openid profile email',
  showDebugInformation: true,
  dummyClientSecret: "83cd4cab-fc8b-4b0f-a5b9-ce90600dd0c6",
  timeoutFactor: 0.01
};
