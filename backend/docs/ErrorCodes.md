API Error Codes
---
Status: *Planning ✍️*
Errors that have occurred during a request. 

# Error Instance
An error instance will contain a reference to one specific problem. The JSON of an error is:
```json
{
  "code": 2,
  "field": "username",
  "message": "Username too short"
}
```

# Error codes
Common problems can be represented as numerical error codes. List of error codes:
Code | Description | 
--- | ----
1 | Field too long (characters)
2 | Field too short (characters)
3 | Field too large (numerically)
4 | Field too small (numerically)
