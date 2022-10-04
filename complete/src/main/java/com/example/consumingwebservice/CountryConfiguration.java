
package com.example.consumingwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.apache.http.auth.UsernamePasswordCredentials;


@Configuration
public class CountryConfiguration {
    
    @Autowired
    private CountryClient client;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.example.consumingwebservice.wsdl");
		return marshaller;
	}

    @Bean
    public HttpComponentsMessageSender getMessageSender() {
        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
        messageSender.setCredentials(new UsernamePasswordCredentials("username", "password"));
        return messageSender;
    }
	
	@Bean
	public CountryClient countryClient(Jaxb2Marshaller marshaller) {
//		CountryClient client = new CountryClient();
//		client.setDefaultUri("http://localhost/ws");
//		client.setMarshaller(marshaller);
//		client.setUnmarshaller(marshaller);
//		return client;
		client.setMessageSender(getMessageSender());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
	}

}
