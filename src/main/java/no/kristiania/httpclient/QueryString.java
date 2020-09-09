package no.kristiania.httpclient;

public class QueryString {
    private final String value;

    public QueryString(String queryString){
        int equalsPos = queryString.indexOf('=');
        value = queryString.substring(equalsPos + 1);
    }

    public String getParameter(String name) {
        return value;
    }
}
