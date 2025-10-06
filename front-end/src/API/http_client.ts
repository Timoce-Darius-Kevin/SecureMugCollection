import axios from "axios";

export const httpClient = axios.create({
  baseURL: "http://localhost:8080/api",
});

// Authenticated client setup will be handled in hooks