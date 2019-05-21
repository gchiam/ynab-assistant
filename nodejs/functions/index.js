'use strict';

// Import the Dialogflow module from the Actions on Google client library.
const {
  dialogflow,
  Permission
} = require('actions-on-google');

// Import the firebase-functions package for deployment.
const functions = require('firebase-functions');

const app = dialogflow({ debug: true });

const ynab = require('./ynab');

// Handle the Dialogflow intent named 'favorite color'.
// The intent collects a parameter named 'color'.
app.intent('welcome', async (conv) => {
  // Respond with the user's lucky number and end the conversation.
  const name = conv.user.storage.userName;
  if (!name) {
    // Asks the user's permission to know their name, for personalization.
    conv.ask('Welcome to YNAB Assistant');
    conv.ask(new Permission({
      context: 'Hi there, to get to know you better',
      permissions: 'NAME',
    }));
  } else {
    conv.ask(`Hi again, ${name}. Welcome to YNAB Assistant.`);

    const userResponse = await ynab.api.user.getUser();
    conv.close(`Your YNAB User ID is ${userResponse.data.user.id}. ` +
    `I am still learning. Talk to you next time!`);
  }
});


// Handle the Dialogflow intent named 'actions_intent_PERMISSION'. If user
// agreed to PERMISSION prompt, then boolean value 'permissionGranted' is true.
app.intent('actions_intent_PERMISSION', (conv, params, permissionGranted) => {
  if (!permissionGranted) {
    // If the user denied our request, go ahead with the conversation.
    conv.ask(`OK, no worries. Welcome to YNAB Assistant`);
  } else {
    // If the user accepted our request, store their name in
    // the 'conv.user.storage' object for the duration of the conversation.
    conv.user.storage.userName = conv.user.name.display;
    conv.ask(`Thanks, ${conv.user.storage.userName}. Welcome to YNAB Assistant`);
  }

  (async function () {
    const userResponse = await ynab.api.user.getUser();
    conv.ask(`Your YNAB User ID is ${userResponse.data.user.id}`);
    conv.close('I am still learning. Talk to you next time!');
  })();
});


// Set the DialogflowApp object to handle the HTTPS POST request.
exports.dialogflowFirebaseFulfillment = functions.https.onRequest(app);
