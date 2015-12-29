package com.shekhar.app.aroundme.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shekhar.app.aroundme.model.Geometry;

import java.util.ArrayList;
import java.util.List;

public class Result {

@SerializedName("address_components")
@Expose
private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
@SerializedName("adr_address")
@Expose
private String adrAddress;
@SerializedName("formatted_address")
@Expose
private String formattedAddress;
@SerializedName("geometry")
@Expose
private Geometry geometry;
@SerializedName("icon")
@Expose
private String icon;
@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("place_id")
@Expose
private String placeId;
@SerializedName("reference")
@Expose
private String reference;
@SerializedName("scope")
@Expose
private String scope;
@SerializedName("types")
@Expose
private List<String> types = new ArrayList<String>();
@SerializedName("url")
@Expose
private String url;
@SerializedName("utc_offset")
@Expose
private Integer utcOffset;
@SerializedName("vicinity")
@Expose
private String vicinity;

/**
* 
* @return
* The addressComponents
*/
public List<AddressComponent> getAddressComponents() {
return addressComponents;
}

/**
* 
* @param addressComponents
* The address_components
*/
public void setAddressComponents(List<AddressComponent> addressComponents) {
this.addressComponents = addressComponents;
}

/**
* 
* @return
* The adrAddress
*/
public String getAdrAddress() {
return adrAddress;
}

/**
* 
* @param adrAddress
* The adr_address
*/
public void setAdrAddress(String adrAddress) {
this.adrAddress = adrAddress;
}

/**
* 
* @return
* The formattedAddress
*/
public String getFormattedAddress() {
return formattedAddress;
}

/**
* 
* @param formattedAddress
* The formatted_address
*/
public void setFormattedAddress(String formattedAddress) {
this.formattedAddress = formattedAddress;
}

/**
* 
* @return
* The geometry
*/
public Geometry getGeometry() {
return geometry;
}

/**
* 
* @param geometry
* The geometry
*/
public void setGeometry(Geometry geometry) {
this.geometry = geometry;
}

/**
* 
* @return
* The icon
*/
public String getIcon() {
return icon;
}

/**
* 
* @param icon
* The icon
*/
public void setIcon(String icon) {
this.icon = icon;
}

/**
* 
* @return
* The id
*/
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(String id) {
this.id = id;
}

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The placeId
*/
public String getPlaceId() {
return placeId;
}

/**
* 
* @param placeId
* The place_id
*/
public void setPlaceId(String placeId) {
this.placeId = placeId;
}

/**
* 
* @return
* The reference
*/
public String getReference() {
return reference;
}

/**
* 
* @param reference
* The reference
*/
public void setReference(String reference) {
this.reference = reference;
}

/**
* 
* @return
* The scope
*/
public String getScope() {
return scope;
}

/**
* 
* @param scope
* The scope
*/
public void setScope(String scope) {
this.scope = scope;
}

/**
* 
* @return
* The types
*/
public List<String> getTypes() {
return types;
}

/**
* 
* @param types
* The types
*/
public void setTypes(List<String> types) {
this.types = types;
}

/**
* 
* @return
* The url
*/
public String getUrl() {
return url;
}

/**
* 
* @param url
* The url
*/
public void setUrl(String url) {
this.url = url;
}

/**
* 
* @return
* The utcOffset
*/
public Integer getUtcOffset() {
return utcOffset;
}

/**
* 
* @param utcOffset
* The utc_offset
*/
public void setUtcOffset(Integer utcOffset) {
this.utcOffset = utcOffset;
}

/**
* 
* @return
* The vicinity
*/
public String getVicinity() {
return vicinity;
}

/**
* 
* @param vicinity
* The vicinity
*/
public void setVicinity(String vicinity) {
this.vicinity = vicinity;
}

}