package com.example.currency

object ApiConfig {

    const val api_key = "71f8a2957da0a345451842fe8fe248b22de4721e"
    const val baseURL = "https://api.getgeoapi.com/v2/currency/convert"
    var url = "https://api.getgeoapi.com/v2/currency/convert\n" +
            "?api_key=$api_key\n" +
            "&from=EUR\n" +
            "&to=GBP\n" +
            "&amount=1"
}