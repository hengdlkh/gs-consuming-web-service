package com.example.consumingwebservice;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.consumingwebservice.wsdl.GetCountryRequest;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

@Service
@Component("GetCountry")
public class CountryClient extends WebServiceGatewaySupport {

	private String endpoint = "http://localhost/ws/countries";

	public GetCountryResponse getCountry(String country) {

		GetCountryRequest request = new GetCountryRequest();
		request.setName(country);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
				.marshalSendAndReceive(endpoint, request,
						new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

		return response;
	}

}
