package rs.ftn.xws.booking.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rs.ftn.xws.booking.webservice.AccomodationWebService;
import rs.ftn.xws.booking.webservice.TestServiceImpl;

@Configuration
public class CXFConfig {

	@Autowired
	private TestServiceImpl testService;
	
	@Autowired
	private AccomodationWebService accomodationWebService;
	
	@Bean
	public Endpoint testServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), testService);
		endpoint.publish("/TestService");
		return endpoint;
	}
	
	@Bean
	public Endpoint accomodationWebServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), accomodationWebService);
		endpoint.publish("/AccomodationWebService");
		return endpoint;
	}
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus springBus = new SpringBus();
		springBus.getInInterceptors().add(getWSS4JInInterceptor());
		springBus.getOutInterceptors().add(getWSS4JOutInterceptor());
		return springBus;
	}
	
	private WSS4JInInterceptor getWSS4JInInterceptor() {
		Map<String, Object> inProps = new HashMap<String, Object>();
		inProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
		inProps.put(WSHandlerConstants.SIG_PROP_FILE, "server-crypto.properties");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "server-crypto.properties");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordCallback.class.getName());

		return new WSS4JInInterceptor(inProps);
	}

	private WSS4JOutInterceptor getWSS4JOutInterceptor() {
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, 
				WSHandlerConstants.TIMESTAMP + " " + WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
		outProps.put(WSHandlerConstants.USER, "server");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "server-crypto.properties");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "server-crypto.properties");
		outProps.put(WSHandlerConstants.ENCRYPTION_USER, WSHandlerConstants.USE_REQ_SIG_CERT);
		outProps.put(WSHandlerConstants.SIGNATURE_PARTS,
				"{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		outProps.put(WSHandlerConstants.ENCRYPTION_PARTS,
				"{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		outProps.put(WSHandlerConstants.ENC_SYM_ALGO, WSConstants.TRIPLE_DES);
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerPasswordCallback.class.getName());

		return new WSS4JOutInterceptor(outProps);
	}
	
}
