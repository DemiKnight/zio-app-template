API Authentication
---
Status: *In-Progress  🏗*


Most authentication code is held within `uk.nightcrawler.core.auth`, this mainly includes authorising HTTP requests. We 
check the `Authorization` header value for a JWT. If the header is missing or contains an invalid JWT token, it will result in a 404.
**Import parts of the authentication**:
- `userRequestAuthoriser` - This is what takes the request and passes it to a function for authorisation
  - Any modifications to the unauthorised response should be made here.
  - Additionally, for further control, it may be necessary to reimplement `customAuthProvidingZIO`
- `protectedResource(endpoingFn)` - This is a utility function for constructing a user-protected endpoint (requires login)
  - All endpoint functions most conform to `ZIO[UserIdentity & ?, Nothing,Response]`. This also means it will have acces to the Logged-In User.
- `UserAuthService` - TODO: For now a stub. in the future will contain authorisation for JWT
  - The function that takes a request and turns it into a `UserIdentity` or `401` response.
- `UserIdentity` - The user identity, containing the user's DB record.
