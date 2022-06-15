package com.example.smarticity.web.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController {
    public static final String PERM_GET = "get";
    public static final String PERM_CREATE = "create";
    public static final String PERM_EDIT = "edit";
    public static final String PERM_DELETE = "delete";
//    public static final String PERM_ = "delete";

//    /**
//     * Validate the given clientId from the requests match {alphanumeric_}_local_server and returns it
//     *
//     * @param data Incomming request data
//     * @return the clientId or null if invalid
//     */
//    protected String validateAndGetLocalServerClientId(@RequestBody Map<String, String> data) {
//        if (data.get("client_id") == null) {
//            return null;
//        }
//
//        String clientId = data.get("client_id").trim();
//
//        if (!clientId.matches("\\w+_local_server")) {
//            return null;
//        }
//        return clientId;
//    }
}

