package ru.netology;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.data.BankData;

import static io.restassured.RestAssured.given;

public class SendData {
        private static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();


        public static void sendUser(BankData newUser) {
           given()
                    .spec(requestSpec)
                    .body(newUser)
                    .when()
                    .post("/api/system/users")
                    .then()
                    .statusCode(200);
        }
    }

