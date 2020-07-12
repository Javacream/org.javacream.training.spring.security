* try unauthenticated access
  * `curl http://localhost:8000/api/anonymous`
    * OK
  * `curl http://localhost:8000/api/user`
    * 401
* retrieve JWT
  * ` curl -verbose http://localhost:8000/authenticate/user/password`
    * Token, something like 2eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTk0NTU2NDMwLCJpYXQiOjE1OTQ1Mzg0MzB9.fVzym9qQl-8Q9Ymq9notyHD1-P3nTRpg_-FS3axSLAn05zNU9cvsuAipYUMgez4ZyzjHn0C9S64ZobD9YqNkZg

* try authenticated access    
  * ` curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTk0NTU2MTAyLCJpYXQiOjE1OTQ1MzgxMDJ9.xEeFmhRhNXV0qLW6F7h0sUHMzRqMj8_8rvATbTJL1FhPGbQa8gb40jskvYqU8KVQ3H1WBQj02RcwHuxBejbvVQ" http://localhost:8000/api/anonymous
    * OK
  * ` curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTk0NTU2MTAyLCJpYXQiOjE1OTQ1MzgxMDJ9.xEeFmhRhNXV0qLW6F7h0sUHMzRqMj8_8rvATbTJL1FhPGbQa8gb40jskvYqU8KVQ3H1WBQj02RcwHuxBejbvVQ" http://localhost:8000/api/user
    * OK
  * ` curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTk0NTU2MTAyLCJpYXQiOjE1OTQ1MzgxMDJ9.xEeFmhRhNXV0qLW6F7h0sUHMzRqMj8_8rvATbTJL1FhPGbQa8gb40jskvYqU8KVQ3H1WBQj02RcwHuxBejbvVQ" http://localhost:8000/api/admin
    * 403