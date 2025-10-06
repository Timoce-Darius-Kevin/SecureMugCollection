// hooks/useUsers.ts
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { userApi } from '../../../API/user_api';
import { useAuth0 } from '@auth0/auth0-react';

export function useUsers() {
  const { getAccessTokenSilently } = useAuth0();
  
  return useQuery({
    queryKey: ['users'],
    queryFn: async () => {
      const token = await getAccessTokenSilently();
      return userApi.getAllUsers(token);
    }
  });
}

export function useDeleteUser() {
  const { getAccessTokenSilently } = useAuth0();
  const queryClient = useQueryClient();
  
  return useMutation({
    mutationFn: async (id: string) => {
      const token = await getAccessTokenSilently();
      return userApi.deleteUser(id, token);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['users'] });
    }
  });
}