package com.project.journel.config.firebase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

@Component
public class FirebaseInitializer {

  @PostConstruct
  public void init() {

    String json = "{"
            + "\"type\": \"" + System.getenv("GOOGLE_TYPE") + "\","
            + "\"project_id\": \"" + System.getenv("GOOGLE_PROJECT_ID") + "\","
            + "\"private_key_id\": \"" + System.getenv("GOOGLE_PRIVATE_KEY_ID") + "\","
            + "\"private_key\": " + System.getenv("GOOGLE_PRIVATE_KEY").replace("\\n", "\n") + ","
            + "\"client_email\": \"" + System.getenv("GOOGLE_CLIENT_EMAIL") + "\","
            + "\"client_id\": \"" + System.getenv("GOOGLE_CLIENT_ID") + "\","
            + "\"auth_uri\": \"" + System.getenv("GOOGLE_AUTH_URI") + "\","
            + "\"token_uri\": \"" + System.getenv("GOOGLE_TOKEN_URI") + "\","
            + "\"auth_provider_x509_cert_url\": \"" + System.getenv("GOOGLE_AUTH_PROVIDER_CERT_URL") + "\","
            + "\"client_x509_cert_url\": \"" + System.getenv("GOOGLE_CLIENT_CERT_URL") + "\","
            + "\"universe_domain\": \"" + System.getenv("GOOGLE_UNIVERSE_DOMAIN") + "\""
            + "}";

    try {
      InputStream serviceAccount = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
      FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setProjectId(System.getenv("GOOGLE_PROJECT_ID"))
        .build();

      FirebaseApp.initializeApp(options);
      System.out.println("Firebase initialized.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public String createFirebaseCustomToken(String userId) {
    try {
        // userId = your app’s authenticated user ID
        String customToken = FirebaseAuth.getInstance().createCustomToken(userId);
        return customToken;
    } catch (FirebaseAuthException e) {
        // handle error properly
        throw new RuntimeException("Failed to create Firebase token", e);
    }
  }
}
