package com.fitchconnect.fiscontent;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.fitchconnect.api.Configuration;
import com.google.common.io.Resources;
import com.jayway.restassured.response.Response;

import groovy.json.internal.Charsets;

public class fisCon_Sprint32 extends Configuration  {
	@Test
	public void FISC_2625_issueRatingTransitionInclude() {

		String IssueRatingTransitionURI = baseURI + "/v1/issueRatingsTransitions?filter[ratingType]=FC_LT_LC_IR&filter[startDate]=2009-01-01&filter[endDate]=2018-01-01&filter[marketSectorId]=03071100";

		Response res = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
				.header("accept", acceptValue).header("content", contentValue).when().get(IssueRatingTransitionURI).then()
				.statusCode(200)
				.body(containsString("issueRatingsTransitions"))
				.body(containsString("issueRatingsTransitionHistory"))
				.extract().response();
		Assert.assertFalse(res.asString().contains("isMissing"));
		Assert.assertFalse(res.asString().contains("isRestricted"));
		
        String relatedlink = res.path("data[0].relationships.issueRatingsTransitionHistory.links.related");
        
       String includedLink = relatedlink+"?include[issueRatingsTransitionHistory]=issue";   
       

		Response res1 = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
				.header("accept", acceptValue).header("content", contentValue).when().get(includedLink).then()
				.statusCode(200)
				.body(containsString("issueRatingsTransitionHistory"))
				.body(containsString("Annual"))
				.body(containsString("Taboada Finance Limited notes"))
				.body(containsString("included"))				
				.extract().response();      
       
		Assert.assertFalse(res1.asString().contains("isMissing"));
		Assert.assertFalse(res1.asString().contains("isRestricted"));
				
	}	
	
	@Test
	public void fisc_2626_issuerTransitionHistoryInclude() {
		
		 String ratingTranstionURI = baseURI+"/v1/issuerRatingsTransitions?filter[marketSectorId]=01010112,01010109,01010219&filter[startDate]=2000-01-01&filter[endDate]=2001-01-01&filter[ratingType]=FC_LT_IDR&filter[issuerId]=80360063&filter[countryISOCode2]=TR,NL";
		 
	        System.out.println(ratingTranstionURI);
		 

			Response res = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
					.header("accept", acceptValue).header("content", contentValue).when().get(ratingTranstionURI).then()
					.statusCode(200)
					.body(containsString("issuerRatingsTransitions"))
					.body(containsString("issuerRatingsTransitionHistory"))
					.extract().response();
			Assert.assertFalse(res.asString().contains("isMissing"));
			Assert.assertFalse(res.asString().contains("isRestricted"));
			
	        String relatedlink = res.path("data[0].relationships.issuerRatingsTransitionHistory.links.related");
	        
	        //System.out.println(relatedlink);
		
	        String includedLink = relatedlink+"?include[issuerRatingsTransitionHistory]=issuer";   
	      System.out.println(includedLink);
						
				Response res1 = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
				.header("accept", acceptValue).header("content", contentValue).when().get(includedLink).then()
				.statusCode(200)
				.body(containsString("issuerRatingsTransitionHistory"))
				.body(containsString("Annual"))
				.body(containsString("Bank Nederlandse Gemeenten (BNG)"))
				.body(containsString("included"))
				.extract().response();
		Assert.assertFalse(res1.asString().contains("isMissing"));
		Assert.assertFalse(res1.asString().contains("isRestricted"));
		
	}
	
	
	@Test(enabled=true)
	public void fisc_3037_newINsuranceMetadata() throws IOException {

		URL file = Resources.getResource("fisc_3037.json");
		String myJson = Resources.toString(file, Charsets.UTF_8);

		Response res = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
				.contentType("application/vnd.api+json").body(myJson).with().when().post(dataPostUrl).then()
				.assertThat().statusCode(200)
				.body(containsString("EUR"))
				.body(containsString("value"))
				.body(containsString("Annual"))
				.body(containsString("baseCurrency"))
				.body(containsString("rate"))
				.extract().response();	
		
    float valueOfDataValuReqst = res.path("data.attributes.entities[0].values[0].values[0].value[0].EUR");
    
	Assert.assertFalse(res.asString().contains("isError"));
	Assert.assertFalse(res.asString().contains("isMissing"));
	Assert.assertFalse(res.asString().contains("isRestricted"));
	
	System.out.println(valueOfDataValuReqst);		
		
	String statementURI = baseURI+"/v1/entities/1145683/statements";          

	Response res1 = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
				.header("accept", acceptValue).header("content", contentValue).when().get(statementURI).then()
				.statusCode(200)
				.body(containsString("statements"))				
				.extract().response();      
        
	float ValueStatemetnResource = res1.path("data[0].attributes.detail[0].value[0].value");
		
	System.out.println(ValueStatemetnResource);
		
		
	Assert.assertFalse(res1.asString().contains("isMissing"));
	Assert.assertFalse(res1.asString().contains("isRestricted"));
			
	boolean failure = false ;
	if(valueOfDataValuReqst==ValueStatemetnResource){
		System.out.println("DA value Matched with Statement Resrouce value");
	  }else{
		  failure = true ;
       System.out.println("Failed");
		
	  }
	
	Assert.assertFalse(failure);	
	
	}
	
	
	@Test 
	
	public void fisc_2510_entitywithRiskConnectFlagN () {
	
	String regionURI = baseURI + "/v1/entities/1407151";

	Response res = given().header("Authorization", AuthrztionValue).header("X-App-Client-Id", XappClintIDvalue)
			.header("accept", acceptValue).header("content", contentValue).when().get(regionURI).then()
			.statusCode(200)
			.body(containsString("countryName"))
			.body(containsString("Brazil"))
			.body(containsString("included"))
			
			.extract().response();		
	
	Assert.assertFalse(res.asString().contains("isMissing"));
	Assert.assertFalse(res.asString().contains("isRestricted"));
	
	}
	 
}