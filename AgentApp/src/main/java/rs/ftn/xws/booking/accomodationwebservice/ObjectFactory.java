
package rs.ftn.xws.booking.accomodationwebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ftn.xws.booking.accomodationwebservice package. 
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

    private final static QName _AddAccomodationResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "addAccomodationResponse");
    private final static QName _AddAccomodation_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "addAccomodation");
    private final static QName _GetAllAccomodationTypes_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllAccomodationTypes");
    private final static QName _DeleteAccomodation_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "deleteAccomodation");
    private final static QName _GetAllAdditionalServicesResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllAdditionalServicesResponse");
    private final static QName _GetAllCategoriesResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllCategoriesResponse");
    private final static QName _CreatingTerm_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "creatingTerm");
    private final static QName _ModifyAccomodation_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "modifyAccomodation");
    private final static QName _ModifyAccomodationResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "modifyAccomodationResponse");
    private final static QName _SetVisitedValue_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "setVisitedValue");
    private final static QName _CreatingTermResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "creatingTermResponse");
    private final static QName _GetAllAccomodationTypesResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllAccomodationTypesResponse");
    private final static QName _GetAllCategories_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllCategories");
    private final static QName _DeleteAccomodationResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "deleteAccomodationResponse");
    private final static QName _GetAll_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAll");
    private final static QName _GetAllAdditionalServices_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllAdditionalServices");
    private final static QName _SetVisitedValueResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "setVisitedValueResponse");
    private final static QName _GetAllResponse_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "getAllResponse");
    private final static QName _Accomodation_QNAME = new QName("http://booking.xws.ftn.rs/accomodationWebService", "Accomodation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ftn.xws.booking.accomodationwebservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccomodationSoap }
     * 
     */
    public AccomodationSoap createAccomodationSoap() {
        return new AccomodationSoap();
    }

    /**
     * Create an instance of {@link GetAllAdditionalServices }
     * 
     */
    public GetAllAdditionalServices createGetAllAdditionalServices() {
        return new GetAllAdditionalServices();
    }

    /**
     * Create an instance of {@link SetVisitedValueResponse }
     * 
     */
    public SetVisitedValueResponse createSetVisitedValueResponse() {
        return new SetVisitedValueResponse();
    }

    /**
     * Create an instance of {@link DeleteAccomodationResponse }
     * 
     */
    public DeleteAccomodationResponse createDeleteAccomodationResponse() {
        return new DeleteAccomodationResponse();
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link CreatingTermResponse }
     * 
     */
    public CreatingTermResponse createCreatingTermResponse() {
        return new CreatingTermResponse();
    }

    /**
     * Create an instance of {@link GetAllAccomodationTypesResponse }
     * 
     */
    public GetAllAccomodationTypesResponse createGetAllAccomodationTypesResponse() {
        return new GetAllAccomodationTypesResponse();
    }

    /**
     * Create an instance of {@link GetAllCategories }
     * 
     */
    public GetAllCategories createGetAllCategories() {
        return new GetAllCategories();
    }

    /**
     * Create an instance of {@link ModifyAccomodationResponse }
     * 
     */
    public ModifyAccomodationResponse createModifyAccomodationResponse() {
        return new ModifyAccomodationResponse();
    }

    /**
     * Create an instance of {@link SetVisitedValue }
     * 
     */
    public SetVisitedValue createSetVisitedValue() {
        return new SetVisitedValue();
    }

    /**
     * Create an instance of {@link GetAllCategoriesResponse }
     * 
     */
    public GetAllCategoriesResponse createGetAllCategoriesResponse() {
        return new GetAllCategoriesResponse();
    }

    /**
     * Create an instance of {@link CreatingTerm }
     * 
     */
    public CreatingTerm createCreatingTerm() {
        return new CreatingTerm();
    }

    /**
     * Create an instance of {@link ModifyAccomodation }
     * 
     */
    public ModifyAccomodation createModifyAccomodation() {
        return new ModifyAccomodation();
    }

    /**
     * Create an instance of {@link AddAccomodationResponse }
     * 
     */
    public AddAccomodationResponse createAddAccomodationResponse() {
        return new AddAccomodationResponse();
    }

    /**
     * Create an instance of {@link AddAccomodation }
     * 
     */
    public AddAccomodation createAddAccomodation() {
        return new AddAccomodation();
    }

    /**
     * Create an instance of {@link DeleteAccomodation }
     * 
     */
    public DeleteAccomodation createDeleteAccomodation() {
        return new DeleteAccomodation();
    }

    /**
     * Create an instance of {@link GetAllAdditionalServicesResponse }
     * 
     */
    public GetAllAdditionalServicesResponse createGetAllAdditionalServicesResponse() {
        return new GetAllAdditionalServicesResponse();
    }

    /**
     * Create an instance of {@link GetAllAccomodationTypes }
     * 
     */
    public GetAllAccomodationTypes createGetAllAccomodationTypes() {
        return new GetAllAccomodationTypes();
    }

    /**
     * Create an instance of {@link AccomodationTypeSoap }
     * 
     */
    public AccomodationTypeSoap createAccomodationTypeSoap() {
        return new AccomodationTypeSoap();
    }

    /**
     * Create an instance of {@link AdditionalServiceSoap }
     * 
     */
    public AdditionalServiceSoap createAdditionalServiceSoap() {
        return new AdditionalServiceSoap();
    }

    /**
     * Create an instance of {@link CategorySoap }
     * 
     */
    public CategorySoap createCategorySoap() {
        return new CategorySoap();
    }

    /**
     * Create an instance of {@link TermSoap }
     * 
     */
    public TermSoap createTermSoap() {
        return new TermSoap();
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAccomodationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "addAccomodationResponse")
    public JAXBElement<AddAccomodationResponse> createAddAccomodationResponse(AddAccomodationResponse value) {
        return new JAXBElement<AddAccomodationResponse>(_AddAccomodationResponse_QNAME, AddAccomodationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAccomodation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "addAccomodation")
    public JAXBElement<AddAccomodation> createAddAccomodation(AddAccomodation value) {
        return new JAXBElement<AddAccomodation>(_AddAccomodation_QNAME, AddAccomodation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAccomodationTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllAccomodationTypes")
    public JAXBElement<GetAllAccomodationTypes> createGetAllAccomodationTypes(GetAllAccomodationTypes value) {
        return new JAXBElement<GetAllAccomodationTypes>(_GetAllAccomodationTypes_QNAME, GetAllAccomodationTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAccomodation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "deleteAccomodation")
    public JAXBElement<DeleteAccomodation> createDeleteAccomodation(DeleteAccomodation value) {
        return new JAXBElement<DeleteAccomodation>(_DeleteAccomodation_QNAME, DeleteAccomodation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAdditionalServicesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllAdditionalServicesResponse")
    public JAXBElement<GetAllAdditionalServicesResponse> createGetAllAdditionalServicesResponse(GetAllAdditionalServicesResponse value) {
        return new JAXBElement<GetAllAdditionalServicesResponse>(_GetAllAdditionalServicesResponse_QNAME, GetAllAdditionalServicesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllCategoriesResponse")
    public JAXBElement<GetAllCategoriesResponse> createGetAllCategoriesResponse(GetAllCategoriesResponse value) {
        return new JAXBElement<GetAllCategoriesResponse>(_GetAllCategoriesResponse_QNAME, GetAllCategoriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatingTerm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "creatingTerm")
    public JAXBElement<CreatingTerm> createCreatingTerm(CreatingTerm value) {
        return new JAXBElement<CreatingTerm>(_CreatingTerm_QNAME, CreatingTerm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyAccomodation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "modifyAccomodation")
    public JAXBElement<ModifyAccomodation> createModifyAccomodation(ModifyAccomodation value) {
        return new JAXBElement<ModifyAccomodation>(_ModifyAccomodation_QNAME, ModifyAccomodation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyAccomodationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "modifyAccomodationResponse")
    public JAXBElement<ModifyAccomodationResponse> createModifyAccomodationResponse(ModifyAccomodationResponse value) {
        return new JAXBElement<ModifyAccomodationResponse>(_ModifyAccomodationResponse_QNAME, ModifyAccomodationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetVisitedValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "setVisitedValue")
    public JAXBElement<SetVisitedValue> createSetVisitedValue(SetVisitedValue value) {
        return new JAXBElement<SetVisitedValue>(_SetVisitedValue_QNAME, SetVisitedValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatingTermResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "creatingTermResponse")
    public JAXBElement<CreatingTermResponse> createCreatingTermResponse(CreatingTermResponse value) {
        return new JAXBElement<CreatingTermResponse>(_CreatingTermResponse_QNAME, CreatingTermResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAccomodationTypesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllAccomodationTypesResponse")
    public JAXBElement<GetAllAccomodationTypesResponse> createGetAllAccomodationTypesResponse(GetAllAccomodationTypesResponse value) {
        return new JAXBElement<GetAllAccomodationTypesResponse>(_GetAllAccomodationTypesResponse_QNAME, GetAllAccomodationTypesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCategories }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllCategories")
    public JAXBElement<GetAllCategories> createGetAllCategories(GetAllCategories value) {
        return new JAXBElement<GetAllCategories>(_GetAllCategories_QNAME, GetAllCategories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAccomodationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "deleteAccomodationResponse")
    public JAXBElement<DeleteAccomodationResponse> createDeleteAccomodationResponse(DeleteAccomodationResponse value) {
        return new JAXBElement<DeleteAccomodationResponse>(_DeleteAccomodationResponse_QNAME, DeleteAccomodationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAdditionalServices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllAdditionalServices")
    public JAXBElement<GetAllAdditionalServices> createGetAllAdditionalServices(GetAllAdditionalServices value) {
        return new JAXBElement<GetAllAdditionalServices>(_GetAllAdditionalServices_QNAME, GetAllAdditionalServices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetVisitedValueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "setVisitedValueResponse")
    public JAXBElement<SetVisitedValueResponse> createSetVisitedValueResponse(SetVisitedValueResponse value) {
        return new JAXBElement<SetVisitedValueResponse>(_SetVisitedValueResponse_QNAME, SetVisitedValueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccomodationSoap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booking.xws.ftn.rs/accomodationWebService", name = "Accomodation")
    public JAXBElement<AccomodationSoap> createAccomodation(AccomodationSoap value) {
        return new JAXBElement<AccomodationSoap>(_Accomodation_QNAME, AccomodationSoap.class, null, value);
    }

}
