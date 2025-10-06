import { httpClient } from "./http_client";
import type { Mug } from "../model/mug";
import type { MugDTO } from "../model/mugDTO";

export const mugApi = {
  async getAllMugs(token: string): Promise<Mug[]> {
    const response = await httpClient.get('/mugs', {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  },

  async createMug(mug: MugDTO, token: string): Promise<Mug> {
    const response = await httpClient.post('/mugs', mug, {
      headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
  },

  async deleteMug(id: number, token: string): Promise<void> {
    await httpClient.delete(`/mugs/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
  }
};