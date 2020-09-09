package no.kristiania.httpclient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryStringTest {

    @Test
    void ShouldRetrieveQueryParameter(){
        QueryString queryString = new QueryString("status=200");
        assertEquals("200", queryString.getParameter("status"));
    }

    @Test
    void ShouldRetrieveOtherQueryParamter(){
        QueryString queryString = new QueryString("status=404");
        assertEquals("404", queryString.getParameter("status"));
    }

}
