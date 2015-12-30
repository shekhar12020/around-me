package com.shekhar.app.aroundme.model.list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlaceSearchResponse {

@SerializedName("html_attributions")
@Expose
private ArrayList<Object> htmlAttributions = new ArrayList<Object>();
@SerializedName("next_page_token")
@Expose
private String nextPageToken;
@SerializedName("results")
@Expose
private ArrayList<Result> results = new ArrayList<Result>();
@SerializedName("status")
@Expose
private String status;

/**
* 
* @return
* The htmlAttributions
*/
public ArrayList<Object> getHtmlAttributions() {
return htmlAttributions;
}

/**
* 
* @param htmlAttributions
* The html_attributions
*/
public void setHtmlAttributions(ArrayList<Object> htmlAttributions) {
this.htmlAttributions = htmlAttributions;
}

/**
* 
* @return
* The nextPageToken
*/
public String getNextPageToken() {
return nextPageToken;
}

/**
* 
* @param nextPageToken
* The next_page_token
*/
public void setNextPageToken(String nextPageToken) {
this.nextPageToken = nextPageToken;
}

/**
* 
* @return
* The results
*/
public ArrayList<Result> getResults() {
return results;
}

/**
* 
* @param results
* The results
*/
public void setResults(ArrayList<Result> results) {
this.results = results;
}

/**
* 
* @return
* The status
*/
public String getStatus() {
return status;
}

/**
* 
* @param status
* The status
*/
public void setStatus(String status) {
this.status = status;
}

}