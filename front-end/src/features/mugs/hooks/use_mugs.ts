// hooks/useMugs.ts
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { mugApi } from '../../../API/mug_api';
import { useAuth0 } from '@auth0/auth0-react';
import type { MugDTO } from '../../../model/mugDTO';

export function useMugs() {
  const { getAccessTokenSilently } = useAuth0();
  
  return useQuery({
    queryKey: ['mugs'],
    queryFn: async () => {
      const token = await getAccessTokenSilently();
      return mugApi.getAllMugs(token);
    }
  });
}

export function useCreateMug() {
  const { getAccessTokenSilently } = useAuth0();
  const queryClient = useQueryClient();
  
  return useMutation({
    mutationFn: async (mug: MugDTO) => {
      const token = await getAccessTokenSilently();
      return mugApi.createMug(mug, token);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['mugs'] });
    }
  });
}

export function useDeleteMug() {
  const { getAccessTokenSilently } = useAuth0();
  const queryClient = useQueryClient();
  
  return useMutation({
    mutationFn: async (id: number) => {
      const token = await getAccessTokenSilently();
      return mugApi.deleteMug(id, token);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['mugs'] });
    }
  });
}