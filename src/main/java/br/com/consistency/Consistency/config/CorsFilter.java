//package br.com.consistency.Consistency.config;
////add correct package
//
//import spark.Filter;
//import spark.Request;
//import spark.Response;
//import spark.Spark;
//
//import java.br.com.consistency.Consistency.util.HashMap;
//
///**
// * Really simple helper for enabling CORS in a spark application;
// */
//public class CorsFilter /*implements Apply*/ {
//
//    private final HashMap<String, String> corsHeaders = new HashMap<>();
//
//    public CorsFilter() {
//        corsHeaders.put("Access-Control-Allow-Methods", "GET,POST");
//        corsHeaders.put("Access-Control-Allow-Origin", "*");
//        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
//        corsHeaders.put("Access-Control-Allow-Credentials", "true");
//    }
//
//    public void apply() {
//        Filter filter = new Filter() {
//            @Override
//            public void handle(Request request, Response response) throws Exception {
//                corsHeaders.forEach((key, value) -> {
//                    response.header(key, value);
//                });
//            }
//        };
//        Spark.after(filter);
//    }
//}