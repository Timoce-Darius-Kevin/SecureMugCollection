import { httpClient } from "./http_client";
import type { User } from "../model/user";

export const userProfileApi = {
  async getCurrentUser(token: string): Promise<User> {
    const response = await httpClient.get('/profile/me', {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  },

  async checkAdmin(token: string): Promise<boolean> {
    const response = await httpClient.get('/profile/check-admin', {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  }
};