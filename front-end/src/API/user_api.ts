import { httpClient } from "./http_client";
import type { User } from "../model/user";

export const userApi = {
  async getAllUsers(token: string): Promise<User[]> {
    const response = await httpClient.get('/users', {
      headers: { Authorization: `Bearer ${token}` }
    });
    console.log("Fetched users:", response.data);
    return response.data;
  },

  async getUserById(id: string, token: string): Promise<User> {
    const response = await httpClient.get(`/users/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  },

  async deleteUser(id: string, token: string): Promise<void> {
    await httpClient.delete(`/users/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
  }
};