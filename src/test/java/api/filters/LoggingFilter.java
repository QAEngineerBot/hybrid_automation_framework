package api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {
        //Log Request
        logRequest(requestSpec); //intercept
        // Get the Response
        Response response = filterContext.next(requestSpec, filterableResponseSpecification); // Request is going to be executed.
        //Log Response
        logResponse(response);
        //return the response for further assertions
        return response;
    }

    public void logRequest(FilterableRequestSpecification requestSpec) {
        logger.info("====== REQUEST ======");
        logger.info("URL        : {}", requestSpec.getURI());
        logger.info("Method     : {}", requestSpec.getMethod());
        logger.info("Headers    : {}", requestSpec.getHeaders());
        if (requestSpec.getBody() != null) {
            logger.info("Request Body       : \n{}", requestSpec.getBody().toString());
        } else {
            logger.info("Request Body       : No request body");
        }
        logger.info("=====================");

    }

    public void logResponse(Response response) {
        logger.info("====== RESPONSE ======");
        logger.info("Status Code : {}", response.getStatusCode());
        logger.info("Headers     : {}", response.getHeaders());
        logger.info("Response Time   : {}",response.getTime()," ms");
        String responseBody = response.getBody().asPrettyString();
        if (responseBody.isEmpty()) {
            logger.info("Response Body        : No response body");
        } else {
            logger.info("Response Body        : \n{}", responseBody);
        }
        logger.info("======================");

    }
}
