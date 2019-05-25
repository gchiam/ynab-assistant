package me.chiam.gordon.ynabassistant.actions;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

public class YnabActionsApp extends DialogflowApp {
    @ForIntent("welcome")
    public ActionResponse welcome(ActionRequest request) {
        ResponseBuilder responseBuilder = getResponseBuilder(request)
                .add("Welcome to YNAB Assistant");
        return responseBuilder.build();
    }

}
