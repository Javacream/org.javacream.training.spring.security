package org.javacream.security.plain.crypt;

import java.security.Provider;
import java.security.Security;
import java.security.Provider.Service;
import java.util.Set;

public class ShowProviders{

	public static void main(String[] args) {
		Provider[] providers = Security.getProviders();
		for (Provider provider: providers){
			System.out.println(provider.getInfo());
			Set<Service> services = provider.getServices();
			System.out.println(services);
		}
	}
	
}
