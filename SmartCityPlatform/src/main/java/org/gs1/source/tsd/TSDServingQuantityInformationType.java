//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.8-b130911.1802 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2015.07.30 시간 02:38:18 PM KST 
//


package org.gs1.source.tsd;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TSD_ServingQuantityInformationType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="TSD_ServingQuantityInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numberOfServingsPerPackage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="measurementPrecisionCode" type="{urn:gs1:tsd:tsd_common:xsd:1}TSD_MeasurementPrecisionCodeType" minOccurs="0"/>
 *         &lt;element name="numberOfServingsRangeDescription" type="{urn:gs1:shared:shared_common:xsd:3}Description70Type" minOccurs="0"/>
 *         &lt;element name="avpList" type="{urn:gs1:tsd:tsd_common:xsd:1}TSD_AttributeValuePairListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSD_ServingQuantityInformationType", namespace = "urn:gs1:tsd:product_quantity_information_module:xsd:1", propOrder = {
    "numberOfServingsPerPackage",
    "measurementPrecisionCode",
    "numberOfServingsRangeDescription",
    "avpList"
})
public class TSDServingQuantityInformationType {

    protected BigDecimal numberOfServingsPerPackage;
    protected TSDMeasurementPrecisionCodeType measurementPrecisionCode;
    protected Description70Type numberOfServingsRangeDescription;
    protected TSDAttributeValuePairListType avpList;

    /**
     * numberOfServingsPerPackage 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfServingsPerPackage() {
        return numberOfServingsPerPackage;
    }

    /**
     * numberOfServingsPerPackage 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfServingsPerPackage(BigDecimal value) {
        this.numberOfServingsPerPackage = value;
    }

    /**
     * measurementPrecisionCode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TSDMeasurementPrecisionCodeType }
     *     
     */
    public TSDMeasurementPrecisionCodeType getMeasurementPrecisionCode() {
        return measurementPrecisionCode;
    }

    /**
     * measurementPrecisionCode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TSDMeasurementPrecisionCodeType }
     *     
     */
    public void setMeasurementPrecisionCode(TSDMeasurementPrecisionCodeType value) {
        this.measurementPrecisionCode = value;
    }

    /**
     * numberOfServingsRangeDescription 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Description70Type }
     *     
     */
    public Description70Type getNumberOfServingsRangeDescription() {
        return numberOfServingsRangeDescription;
    }

    /**
     * numberOfServingsRangeDescription 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Description70Type }
     *     
     */
    public void setNumberOfServingsRangeDescription(Description70Type value) {
        this.numberOfServingsRangeDescription = value;
    }

    /**
     * avpList 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TSDAttributeValuePairListType }
     *     
     */
    public TSDAttributeValuePairListType getAvpList() {
        return avpList;
    }

    /**
     * avpList 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TSDAttributeValuePairListType }
     *     
     */
    public void setAvpList(TSDAttributeValuePairListType value) {
        this.avpList = value;
    }

}
