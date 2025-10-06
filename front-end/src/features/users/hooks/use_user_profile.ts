// hooks/useUserProfile.ts
import { useQuery } from '@tanstack/react-query';
import { userProfileApi } from '../../../API/user_profile_api';
import { useAuth0 } from '@auth0/auth0-react';

export function useUserProfile() {
  const { getAccessTokenSilently } = useAuth0();
  
  return useQuery({
    queryKey: ['user-profile'],
    queryFn: async () => {
      const token = await getAccessTokenSilently();
      return userProfileApi.getCurrentUser(token);
    }
  });
}

export function useIsAdmin() {
  const { getAccessTokenSilently } = useAuth0();
  
  return useQuery({
    queryKey: ['user-is-admin'],
    queryFn: async () => {
      const token = await getAccessTokenSilently();
      return userProfileApi.checkAdmin(token);
    }
  });
}