package com.javaguidesl.springboot.dto.response.xmlresponse;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class XMLResponse {

    @JacksonXmlProperty(localName = "Body")
    private BodyRes body;

    public BodyRes getBody() {
        return body;
    }

    public XMLResponse(){}
}

class BodyRes {

    @JacksonXmlElementWrapper(localName = "FindUsers")
    @JacksonXmlProperty(localName = "FindUser")
    private List<FindUserRes> findUser;
    public BodyRes(){}
}

class FindUserRes {

    @JacksonXmlProperty(localName = "SAMAccountName")
    private String samaccountName;

    public FindUserRes(){}
    public String getSAMAccountName() {
        return samaccountName;
    }
}
