let ticket = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidXNlcklkIjoiRVhTLVZTQkYiLCJpYXQiOjE1MTYyMzkwMjJ9.-ItFZbvNFa78_rV4pSjlwLpg1-EyOWXY-ff-LlJDnqk";
let payload = ticket.split(".")[1];
let userId = payload.userId;

console.log(userId); 