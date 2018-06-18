package br.com.consistency.Consistency.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import static java.lang.String.format;

public class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "app";
    private static final String X_BACKEND_APP_ALERT = format("X-%s-alert", APPLICATION_NAME);
    private static final String X_BACKEND_APP_PARAMS = format("X-%s-params", APPLICATION_NAME);
    private static final String X_BACKEND_APP_ERROR = format("X-%s-error", APPLICATION_NAME);

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_BACKEND_APP_ALERT, message);
        headers.add(X_BACKEND_APP_PARAMS, param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_BACKEND_APP_ERROR, "error." + errorKey);
        headers.add(X_BACKEND_APP_PARAMS, entityName);
        return headers;
    }
}
