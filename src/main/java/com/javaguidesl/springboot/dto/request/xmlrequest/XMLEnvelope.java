package com.javaguidesl.springboot.dto.request.xmlrequest;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "soap:Envelope")
public class XMLEnvelope {

    @JacksonXmlProperty(localName = "xmlns:xsi", isAttribute = true)
    private String xsi = "test";

    @JacksonXmlProperty(localName = "xmlns:xsd", isAttribute = true)
    private String xsd ="test";

    @JacksonXmlProperty(localName = "xmlns:soap", isAttribute = true)
    private String soap= "test";

    public Body getBody() {
        return body;
    }

    @JacksonXmlProperty(localName = "Body")
    private Body body;

    public XMLEnvelope(String soeid, String xmlns) {
        body = new Body(soeid, xmlns);
    }
    public XMLEnvelope(){}
}

class Body {

    public List<FindUser> getFindUser() {
        return findUser;
    }

    public void setFindUser(List<FindUser> findUser) {
        this.findUser = findUser;
    }


    @JacksonXmlElementWrapper(localName = "FindUsers")
    @JacksonXmlProperty(localName = "FindUser")
    private List<FindUser> findUser;

    public Body(String soeid, String xmlns){
        findUser = new ArrayList<FindUser>();
        FindUser dfindUser = new FindUser();
        dfindUser.setSAMAccountName(xmlns);
        dfindUser.setSAMAccountName(soeid);
        findUser.add(dfindUser);
        findUser.add(dfindUser);
    }

    public Body(){}
}

class FindUser {

    @JacksonXmlProperty(isAttribute = true)
    private String xmlns;

    @JacksonXmlProperty(localName = "SAMAccountName")
    private String samaccountName;

    public FindUser(){}

    public String getXmlns() {
        return xmlns;
    }
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
    public String getSAMAccountName() {
        return samaccountName;
    }
    public void setSAMAccountName(String SAMAccountName) {
        this.samaccountName = SAMAccountName;
    }
}

