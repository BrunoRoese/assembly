package com.challenge.assembly.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
This is an example of versioning on the api with URI, now we can create a new version of the endpoint and make the necessary changes.
Is it a good practice to version the api to ensure backward compatibility with the client's code. Not changing the version our clients are using.
A proper way to test clients contract is to use integration testing with RestAssured.
I would choose the URI versioning because it is easier to maintain and understand, even though it is not the best practice when looking up the RESTful principles.
 */
@RestController
@RequestMapping("api/v2/votes")
public class VoteControllerV2 {
}
