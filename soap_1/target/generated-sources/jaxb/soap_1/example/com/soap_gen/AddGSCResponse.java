//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.04 at 11:43:59 PM MSK 
//


package soap_1.example.com.soap_gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="serviceStatus" type="{http://com.example.soap_1/soap_gen}serviceStatus"/&gt;
 *         &lt;element name="GSCType" type="{http://com.example.soap_1/soap_gen}GSCType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceStatus",
    "gscType"
})
@XmlRootElement(name = "addGSCResponse")
public class AddGSCResponse {

    @XmlElement(required = true)
    protected ServiceStatus serviceStatus;
    @XmlElement(name = "GSCType", required = true)
    protected GSCType gscType;

    /**
     * Gets the value of the serviceStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    /**
     * Sets the value of the serviceStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setServiceStatus(ServiceStatus value) {
        this.serviceStatus = value;
    }

    /**
     * Gets the value of the gscType property.
     * 
     * @return
     *     possible object is
     *     {@link GSCType }
     *     
     */
    public GSCType getGSCType() {
        return gscType;
    }

    /**
     * Sets the value of the gscType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GSCType }
     *     
     */
    public void setGSCType(GSCType value) {
        this.gscType = value;
    }

}