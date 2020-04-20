package com.w2a.API_Automation_BDD.utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:src/test/resources/PropertyFiles/config.properties" })
public interface ConfigProperty extends Config {

	@Key("baseURI")
	String getBaseURI();

	@Key("basePath")
	String getBasePath();

	@Key("validSecretKey")
	String getValidAuthKey();

	@Key("customerAPIEndPoint")
	String getCustomerAPIEndPoint();

	@Key("InvalidSecretKey")
	String geyInvalidAuthKey();

}