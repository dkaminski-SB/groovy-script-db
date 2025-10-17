for (tSuite in testRunner.testCase.testSuite.project.getTestSuiteList()){
	for (tCase in tSuite.getTestCaseList()) {
		// iterate through RESTful Test Steps.
		// for SOAP Test Steps  speicfy this type - com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep
		// It is countermesure as not evey Test Step use Assertions e.g. Groovy Test Step
		 for(tStep in tCase.getTestStepsOfType(com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep )){
			// Logs Test Step name
			log.info " "
			log.info "----------------------------------------"
			log.info tStep.getName()
			log.info "^^^^^^^^^^^^^^^^^^^^^^^^^"
		 	for ( tAssertion in tStep.getAssertionList()){
				// logs Assertion name
				log.info tAssertion.getName()
  				// Validate Assertion type based on name. If the assertion name matches use apropriate methods
			  	if (tAssertion.getName() == "Valid HTTP Status Codes")
			  	{	
			  		// Set Assertion codes
			  		log.info ">>> Old Status codes: " + tAssertion.getCodes()
			    		tAssertion.setCodes("200, 504")
					log.info ">>>>>> New new status: " + tAssertion.getCodes()
			  	}
			  	if (tAssertion.getName() == "Response SLA")
			  	{	
			  		// Set Assertion SLA Time
			  		log.info ">>> Old SLA: " + tAssertion.getSLA()
			    		tAssertion.setSLA("400")
					log.info ">>>>>> New SLA " + tAssertion.getSLA()
			  	}			  	
			  	if (tAssertion.getName() == "Script Assertion")
			  	{
			    	// Set content of  Script in Assertion
			    	log.info ">>> Old script:"+ tAssertion.getScriptText()
					tAssertion.setScriptText("assert messageExchange.timeTaken < 100")
					log.info ">>>>>> New script:" + tAssertion.getScriptText()
			  	}
			  	
			}
		 }
	}
}