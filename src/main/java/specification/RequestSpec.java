package specification;

import constant.Params;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    RequestSpecification requestSpecification;

    public RequestSpec(String endPoint) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Params.commonURL + endPoint)
                .addHeader("Authorization", "Bearer " + Params.token)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

}