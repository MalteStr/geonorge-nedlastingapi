//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.05 at 08:37:03 AM CEST 
//


package no.geonorge.skjema.sosi.tjenestespesifikasjon.nedlastingapi._2;

import java.io.Serializable;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import no.kxml.rest._1.LinkListeType;


/**
 * <p>Java class for OrderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="orderLines" type="{http://skjema.geonorge.no/SOSI/tjenestespesifikasjon/nedlastingapi/2.0}OrderLineListe"/&gt;
 *         &lt;element name="_links" type="{http://www.kxml.no/rest/1.0}LinkListeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderType", propOrder = {
    "email",
    "orderLines",
    "links"
})
public class OrderType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected OrderLineListe orderLines;
    @XmlElementRef(name = "_links", namespace = "http://skjema.geonorge.no/SOSI/tjenestespesifikasjon/nedlastingapi/2.0", type = JAXBElement.class, required = false)
    protected JAXBElement<LinkListeType> links;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the orderLines property.
     * 
     * @return
     *     possible object is
     *     {@link OrderLineListe }
     *     
     */
    public OrderLineListe getOrderLines() {
        return orderLines;
    }

    /**
     * Sets the value of the orderLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderLineListe }
     *     
     */
    public void setOrderLines(OrderLineListe value) {
        this.orderLines = value;
    }

    /**
     * Gets the value of the links property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LinkListeType }{@code >}
     *     
     */
    public JAXBElement<LinkListeType> getLinks() {
        return links;
    }

    /**
     * Sets the value of the links property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LinkListeType }{@code >}
     *     
     */
    public void setLinks(JAXBElement<LinkListeType> value) {
        this.links = value;
    }

}
