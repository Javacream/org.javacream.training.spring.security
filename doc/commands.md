# kadmin.local

# create keytab
* ktutil
  * add_entry -password -p HTTP/javacream@JAVACREAM.ORG -k 1 -e aes256-cts-hmac-sha1-96
  * add_entry -password -p javacream@JAVACREAM.ORG -k 1 -e arcfour-hmac-exp
  * wkt
