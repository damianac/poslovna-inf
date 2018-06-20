
package rs.ftn.xws.booking.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ftn.xws.booking.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestMethod_QNAME = new QName("http://booking.xws.ftn.rs/test", "testMethod");
    private final static QName _UploadMultiple_QNAME = new QName("http://booking.xws.ftn.rs/test", "uploadMultiple");
    private final static QName _UploadModelXsd_QNAME = new QName("http://booking.xws.ftn.rs/test", "uploadModelXsd");
    private final static QName _TestMethodResponse_QNAME = new QName("http://booking.xws.ftn.rs/test", "testMethodResponse");
    private final static QName _UploadMultipleResponse_QNAME = new QName("http://booking.xws.ftn.rs/test", "uploadMultipleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ftn.xws.booking.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UploadModelXsd }
     * 
     */
    public UploadModelXsd createUploadModelXsd() {
        return new UploadModelXsd();
    }

    /**
     * Create an instance of {@link TestMethodResponse }
     * 
     */
    public TestMethodResponse createTestMethodResponse() {
        return new TestMethodResponse();
    }

    /**
     * Create an instance of {@link UploadMultipleResponse }
     * 
     */
    public UploadMultipleResponse createUploadMultipleResponse() {
        return new UploadMultipleResponse();
    }

    /**
     * Create an instance of {@link TestMethod }
     * 
     */
    public TestMethod createTestMethod() {
        return new TestMethod();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/test", name = "testMethod")
    public JAXBElement<TestMethod> createTestMethod(TestMethod value) {
        return new JAXBElement<TestMethod>(_TestMethod_QNAME, TestMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadMultiple }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/test", name = "uploadMultiple")
    public JAXBElement<UploadMultiple> createUploadMultiple(UploadMultiple value) {
        return new JAXBElement<UploadMultiple>(_UploadMultiple_QNAME, UploadMultiple.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadModelXsd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/test", name = "uploadModelXsd")
    public JAXBElement<UploadModelXsd> createUploadModelXsd(UploadModelXsd value) {
        return new JAXBElement<UploadModelXsd>(_UploadModelXsd_QNAME, UploadModelXsd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestMethodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/test", name = "testMethodResponse")
    public JAXBElement<TestMethodResponse> createTestMethodResponse(TestMethodResponse value) {
        return new JAXBElement<TestMethodResponse>(_TestMethodResponse_QNAME, TestMethodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadMultipleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/test", name = "uploadMultipleResponse")
    public JAXBElement<UploadMultipleResponse> createUploadMultipleResponse(UploadMultipleResponse value) {
        return new JAXBElement<UploadMultipleResponse>(_UploadMultipleResponse_QNAME, UploadMultipleResponse.class, null, value);
    }

}
