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
 * <p>Java class for CanDownloadRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CanDownloadRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="metadataUuid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coordinates" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coordinateSystem" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "CanDownloadRequestType", propOrder = {
    "metadataUuid",
    "coordinates",
    "coordinateSystem",
    "links"
})
public class CanDownloadRequestType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String metadataUuid;
    @XmlElement(required = true)
    protected String coordinates;
    @XmlElement(required = true)
    protected String coordinateSystem;
    @XmlElementRef(name = "_links", namespace = "http://skjema.geonorge.no/SOSI/tjenestespesifikasjon/nedlastingapi/2.0", type = JAXBElement.class, required = false)
    protected JAXBElement<LinkListeType> links;

    /**
     * Gets the value of the metadataUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetadataUuid() {
        return metadataUuid;
    }

    /**
     * Sets the value of the metadataUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetadataUuid(String value) {
        this.metadataUuid = value;
    }

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinates(String value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the coordinateSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Sets the value of the coordinateSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinateSystem(String value) {
        this.coordinateSystem = value;
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
