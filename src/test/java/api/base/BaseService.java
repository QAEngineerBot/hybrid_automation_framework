package api.base;

import api.filters.LoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {

    // Default base URL
    private static final String DEFAULT_BASE_URL = "http://64.227.160.186:8080";
    private RequestSpecification requestSpecification;

    // Static initialization block for setting filters (such as logging)
    static {
        // Logging filter for request/response logs
        RestAssured.filters(new LoggingFilter());
    }

    /**
     * Constructor that sets the base URI for requests.
     *
     * @param baseUri The base URI for the requests.
     */
    public BaseService(String baseUri) {
        // If base URI is provided, use it, otherwise default to the hardcoded URI
        String uri = (baseUri != null && !baseUri.isEmpty()) ? baseUri : DEFAULT_BASE_URL;
        requestSpecification = RestAssured.given().baseUri(uri); // Initialize request specification with base URI
    }

    /**
     * Default constructor that uses the default base URI.
     */
    public BaseService() {
        // Use default base URL if no custom URI is provided
        this(DEFAULT_BASE_URL);
    }

    /**
     * Sets the base URI dynamically for the request.
     * This method allows setting a new base URI at runtime.
     *
     * @param baseUri The base URI to set.
     */
    public void setBaseUri(String baseUri) {
        // Update the base URI for the request specification
        requestSpecification.baseUri(baseUri);
    }

    /**
     * Sets the Authorization token for the request.
     *
     * @param token The Bearer token for authentication.
     */
    protected void setAuthToken(String token) {
        // Add Authorization header
        requestSpecification.header("Authorization", "Bearer " + token);
    }

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endPoint The endpoint for the GET request.
     * @return The Response object.
     */
    protected Response getRequest(String endPoint) {
        // Send GET request
        return requestSpecification.get(endPoint);
    }

    /**
     * Sends a POST request to the specified endpoint with the given body.
     *
     * @param body     The request body (object to be serialized).
     * @param endPoint The endpoint for the POST request.
     * @return The Response object.
     */
    protected Response postRequest(Object body, String endPoint) {
        // Send POST request
        return requestSpecification.contentType(ContentType.JSON).body(body).post(endPoint);
    }

    /**
     * Sends a PUT request to the specified endpoint with the given body.
     *
     * @param body     The request body (object to be serialized).
     * @param endPoint The endpoint for the PUT request.
     * @return The Response object.
     */
    protected Response putRequest(Object body, String endPoint) {
        // Send PUT request
        return requestSpecification.contentType(ContentType.JSON).body(body).put(endPoint);
    }
}
