package org.javacream.training.security.keytab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import com.sun.security.auth.module.Krb5LoginModule;

/**
 * This is simple Java program that tests ability to authenticate with Kerberos
 * using the JDK implementation.
 * 
 * The program uses no libraries but JDK itself.
 */
public class CheckAuthentication {

	private void loginImpl(final String propertiesFileName) throws Exception {
		System.out.println("NB: system property to specify the krb5 config: [java.security.krb5.conf]");
		// System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");

		System.out.println(System.getProperty("java.version"));

		System.setProperty("sun.security.krb5.debug", "true");

		final Subject subject = new Subject();

		final Krb5LoginModule krb5LoginModule = new Krb5LoginModule();
		final Map<String, String> optionMap = new HashMap<String, String>();

		if (propertiesFileName == null) {
			// optionMap.put("ticketCache", "/tmp/krb5cc_1000");
			optionMap.put("principal", "CHANGE"); // default realm

			optionMap.put("doNotPrompt", "false");
			optionMap.put("refreshKrb5Config", "true");
			optionMap.put("useTicketCache", "true");
			optionMap.put("renewTGT", "true");
			optionMap.put("useKeyTab", "false");
			optionMap.put("storeKey", "true");
			optionMap.put("isInitiator", "true");
		} else {
			File f = new File(propertiesFileName);

			System.out.println("======= loading property file [" + f.getAbsolutePath() + "]");
			Properties p = new Properties();
			InputStream is = new FileInputStream(f);
			try {
				p.load(is);
			} finally {
				is.close();
			}
			optionMap.putAll((Map) p);
		}
		optionMap.put("debug", "true"); // switch on debug of the Java implementation

		krb5LoginModule.initialize(subject, new CallbackHandler() {

			@Override
			public void handle(Callback[] callbacks)
					 throws IOException, UnsupportedCallbackException {

					   for (int i = 0; i < callbacks.length; i++) {
					      if (callbacks[i] instanceof TextOutputCallback) {

					          // display the message according to the specified type
					          TextOutputCallback toc = (TextOutputCallback)callbacks[i];
					          switch (toc.getMessageType()) {
					          case TextOutputCallback.INFORMATION:
					              System.out.println(toc.getMessage());
					              break;
					          case TextOutputCallback.ERROR:
					              System.out.println("ERROR: " + toc.getMessage());
					              break;
					          case TextOutputCallback.WARNING:
					              System.out.println("WARNING: " + toc.getMessage());
					              break;
					          default:
					              throw new IOException("Unsupported message type: " +
					                                  toc.getMessageType());
					          }

					      } else if (callbacks[i] instanceof NameCallback) {

					          // prompt the user for a username
					          NameCallback nc = (NameCallback)callbacks[i];

					          // ignore the provided defaultName
					          System.err.print(nc.getPrompt());
					          System.err.flush();
					          nc.setName((new BufferedReader
					                  (new InputStreamReader(System.in))).readLine());

					      } else if (callbacks[i] instanceof PasswordCallback) {

					          // prompt the user for sensitive information
					          PasswordCallback pc = (PasswordCallback)callbacks[i];
					          System.err.print(pc.getPrompt());
					          System.err.flush();
					          pc.setPassword(readPassword(System.in));

					      } else {
					          throw new UnsupportedCallbackException
					                  (callbacks[i], "Unrecognized Callback");
					      }
					   }
					 }

					 // Reads user password from given input stream.
					 private char[] readPassword(InputStream in) throws IOException {
					    return new BufferedReader(new InputStreamReader(in)).readLine().toCharArray();
					 }			
		}, new HashMap<String, String>(), optionMap);

		boolean loginOk = krb5LoginModule.login();
		System.out.println("======= login:  " + loginOk);

		boolean commitOk = krb5LoginModule.commit();
		System.out.println("======= commit: " + commitOk);

		System.out.println("======= Subject: " + subject);
	}

	public static void main(String[] args) throws Exception {
		System.out
				.println("A property file with the login context can be specified as the 1st and the only paramater.");
		final CheckAuthentication krb = new CheckAuthentication();
		krb.loginImpl(args.length == 0 ? null : args[0]);
	}
}
