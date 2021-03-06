# test-bed-adapter
The test-bed-adaptor provides the means to connect crisis management solutions as well as (simulation) tools to the test-bed.

# Software requirements of the test-bed adaptor

The test-bed adaptor's main function is to enable different software solutions to easily exchange validated messages, both in the Common Information Space as well as the Common Simulation Space.

## FUNCTIONAL REQUIREMENTS

1. The adaptor shall enable solutions (software clients) to publish (broadcast) messages.
2. The adaptor shall enable solutions (clients) to receive messages.
3. The adaptor shall enable solutions (clients) to send directed messages to one recipient or a group of recipients.
4. The adaptor shall enable solutions (clients) to receive directed messages from a recipient.
5. The adaptor shall validate incoming messages.
6. The adaptor shall limit the size of incoming messages: the adaptor is responsible for distributing large messages by reference (link to the data). _In this case, the adaptor needs to be configured appropriately._
7. The adaptor shall be able to log all incoming and outgoing messages to file, either in raw format or as text (XML, JSON, etc.)
8. The adaptor shall transmit a heartbeat message, so others can see that it's up.
9. The adaptor shall be configurable externally.
   _For example, incoming messages and outgoing messages may be blocked by a trial manager._

## PERFORMANCE & USABILITY REQUIREMENTS

1. The adaptor shall be robust for network failures: after a network failure, it will be able to recover the missed messages from other senders, and it will be able to send unpublished messages.
2. The adaptor shall have minimal latency when publishing or receiving messages.
3. The adaptor shall provide clear error messages in English.
4. The adaptor shall be user-friendly: an experienced software developer should be able to send and receive standardized messages (without translation) in less than two hours, assuming the Dockerized test-bed is running already.

## CONSTRAINTS

1. The adaptor shall be thoroughly documented: each function is clearly described for an end-user (i.e. solution developer).
2. The adaptor shall support clients using many different software languages (e.g. Java, C#, JavaScript, Python, etc.).
3. The adaptor shall be open source, so bugs can be easily resolved and there is no roadblock impeding its uptake.
4. All functional requirements shall be tested using an automated testing environment, so after each commit, the test suite is run.
5. The adaptor software shall use a Continuous Integration environment, like Travis CI or Jenkins, so after each push to the repository, a build is performed and all tests are run.

## SCOPE / EXCLUDED FUNCTIONALITY

And finally, some functionality that I wish to exclude from the adaptor:

1. The adaptor shall not translate incoming messages to another language, e.g. German to Italian, or another user group, e.g. police to ambulance personnel.
_Otherwise, we may have to translate each message from each language to every other language. In case we need this, which seems likely, I would rather add a function to the test-bed, e.g. a translation service, but I do not want it to complicate the adaptor._
2. The adaptor shall not limit access to protected messages, e.g. when only specific recipients are allowed to receive and read a message. This functionality will be part of the test-bed (Kafka), but not of the adaptor.

