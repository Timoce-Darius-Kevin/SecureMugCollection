// navigation.tsx
import { Link } from 'react-router-dom';
import { useAuth0 } from '@auth0/auth0-react';
import { useIsAdmin } from '../features/users/hooks/use_user_profile';

export const Navigation = () => {
  const { user, isAuthenticated, loginWithRedirect, logout, isLoading } = useAuth0();
  const { data: isAdmin, isLoading: isAdminLoading } = useIsAdmin();

  if (isLoading || isAdminLoading) {
    return (
      <nav className="bg-gray-800 text-white shadow-lg">
        <div className="container mx-auto px-6 py-3">
          <div className="flex justify-between items-center">
            <div className="text-xl font-bold">Mug Collection</div>
            <div className="text-gray-300">Loading...</div>
          </div>
        </div>
      </nav>
    );
  }

  return (
    <nav className="bg-gray-800 text-white shadow-lg">
      <div className="container mx-auto px-6 py-3">
        <div className="flex justify-between items-center">
          <div className="flex items-center space-x-4">
            <Link to="/" className="text-xl font-bold">
              Mug Collection
            </Link>
            {isAuthenticated && (
              <>
                <Link to="/mugs" className="hover:text-gray-300">
                  My Mugs
                </Link>
                {isAdmin && (
                  <Link to="/users" className="hover:text-gray-300">
                    Users
                  </Link>
                )}
              </>
            )}
          </div>
          
          <div className="flex items-center space-x-4">
            {isAuthenticated ? (
              <>
                <span className="text-gray-300">
                  Welcome, {user?.name} {isAdmin && '(Admin)'}
                </span>
                <button
                  onClick={() => logout({ logoutParams: { returnTo: window.location.origin } })}
                  className="bg-red-600 hover:bg-red-700 px-3 py-1 rounded"
                >
                  Logout
                </button>
              </>
            ) : (
              <button
                onClick={() => loginWithRedirect()}
                className="bg-blue-600 hover:bg-blue-700 px-3 py-1 rounded"
              >
                Login
              </button>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
};