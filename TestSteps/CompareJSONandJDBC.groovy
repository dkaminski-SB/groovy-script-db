// *****************************************************************************
//
//  Script to compare and assert two results one form RESTful Reqeust and one form JDBC query
//  Script have to be adjusted to the responses (please check example responses at bottom)
//
// *****************************************************************************

import org.w3c.dom.Node
import org.w3c.dom.Element
import groovy.transform.ToString

@ToString
class Client {
    def id
    def email
    def fullname
    def status
}

def groovyUtils = new com.eviware.soapui.support.GroovyUtils(context)

// Parse JDBC Request Test Step Result
def jdbc = context.expand( '${JDBC Request#ResponseAsXml}' )
def holderJdbc = groovyUtils.getXmlHolder(jdbc)

TreeMap<String, Client> jdbcResult = new TreeMap<String, Client>();
for(node in holderJdbc.getDomNodes("//Row")){
    Client c = new Client();
    node.getChildNodes().each { child ->
        if(child.getNodeType()==Node.ELEMENT_NODE){
            def elementName = child.getNodeName()
            def elementValue = ""
            if(child.item(0) !=null){
                elementValue = child.item(0).getNodeValue()
            }
            switch(elementName) {
                case "ClientUserID":
                    c.id = elementValue
                    break
                case "EmailAddress":
                    c.email = elementValue
                    break
                case "FullName":
                    c.fullname = elementValue
                    break
                case "Status":
                    c.status = elementValue
                    break
                default
                    break
            }
        }
    }
    jdbcResult.put(c.id.toString(), c)
}
log.info jdbcResult

// Parse REST Request Test Step Result
def rest = context.expand( '${REST Request#ResponseAsXml}' )
def holderRest = groovyUtils.getXmlHolder(rest)

TreeMap<String, Client> restResult = new TreeMap<String, Client>();
for(node in holderRest.getDomNodes("//xmlns:e")){
    Client c = new Client();
    node.getChildNodes().each { child ->
        if(child.getNodeType()==Node.ELEMENT_NODE){
            def elementName = child.getNodeName()
            def elementValue = ""
            if(child.item(0) !=null){
                elementValue = child.item(0).getNodeValue()
            }
            switch(elementName) {
                case "CustomerId":
                    c.id = elementValue
                    break
                case "Email":
                    c.email = elementValue
                    break
                case "Name":
                    c.fullname = elementValue
                    break
                case "Status":
                    c.status = elementValue
                    break
                default
                    break
            }
        }
    }
    restResult.put(c.id.toString(), c)
}
log.info restResult

// Comaparing and asserting both objects
assert jdbcResult.size() == restResult.size()

jdbcResult.each {k, v ->
    Client c1 = v
    Client c2 = restResult.get(k)
    assert c1.toString() == c2.toString()
}

// *****************************************************************************
//
//  Example Results of test steps in ReadyAPI
//  Script have to be adjusted to the responses
//
// *****************************************************************************

// JDBC Reqeust response (as XML)
<Results>
    <ResultSet fetchSize="123">
        <Row rowNumber="2">
            <ClientUserID>00000500</ClientUserID>
            <EmailAddress>john.doe@example.com </EmailAddress>
            <PhoneNumber>444444444</PhoneNumber>
            <FullName>John Doe </FullName>
            <Status>Activated</Status>
        </Row>
        <Row rowNumber="3">
            <ClientUserID>00000132</ClientUserID>
            <EmailAddress>bob.smith@example.com </EmailAddress>
            <PhoneNumber>666666666</PhoneNumber>
            <FullName>Bob Smith </FullName>
            <Status>Activated</Status>
        </Row>
        <Row rowNumber="3">
            <ClientUserID>00000003</ClientUserID>
            <EmailAddress>nn@example.com </EmailAddress>
            <PhoneNumber/>
            <FullName>--NO NAME--</FullName>
            <Status>Activated</Status>
        </Row>
    </ResultSet>    
</Results>

// REST Reqeust response example
{
    "TotalCount": 7
    "Customers": [
        {
            "CustomerId": 00000500
            "Name": "John Doe"
            "Email": "john.doe@example.com"
            "IsEmailHardBounced": false
            "Status": "Activated"
        },
        {
            "CustomerId": 00000132
            "Name": "Bob Smith"
            "Email": "bob.smith@example.com "
            "IsEmailHardBounced": false
            "Status": "Activated"
        },
        {
            "CustomerId": 000000003
            "Name": "John Doe"
            "Email": "nn@example.com"
            "IsEmailHardBounced": false
            "Status": "Activated"
        }
    ]
}
