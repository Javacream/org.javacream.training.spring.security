package org.javacream.training.security.keytab;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.Subject;

import com.sun.security.auth.module.Krb5LoginModule;

/**
 * This is simple Java program that tests ability to authenticate with Kerberos
 * using the JDK implementation.
 * 
 * The program uses no libraries but JDK itself.
 */
public class CheckKeytab {

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
			optionMap.put("keyTab", "/home/rainersawitzki/tomcat_service.keytab");
			optionMap.put("principal", "HTTP/localhost@JAVACREAM.ORG"); // default realm

			optionMap.put("doNotPrompt", "true");
			optionMap.put("refreshKrb5Config", "true");
			optionMap.put("useTicketCache", "true");
			optionMap.put("renewTGT", "true");
			optionMap.put("useKeyTab", "true");
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

		krb5LoginModule.initialize(subject, null, new HashMap<String, String>(), optionMap);

		boolean loginOk = krb5LoginModule.login();
		System.out.println("======= login:  " + loginOk);

		boolean commitOk = krb5LoginModule.commit();
		System.out.println("======= commit: " + commitOk);

		System.out.println("======= Subject: " + subject);
	}

	public static void main(String[] args) throws Exception {
		System.out
				.println("A property file with the login context can be specified as the 1st and the only paramater.");
		final CheckKeytab krb = new CheckKeytab();
		krb.loginImpl(args.length == 0 ? null : args[0]);
	}
}
