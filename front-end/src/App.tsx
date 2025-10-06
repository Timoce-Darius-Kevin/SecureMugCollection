// App.tsx
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { Auth0Provider } from '@auth0/auth0-react';
import { Navigation } from './navigation/navigation';
import { ProtectedRoute } from './navigation/protected_route';
import { MugListPage } from './pages/mug_list_page';
import { UserManagementPage } from './pages/user_management_page';
import.meta

const queryClient = new QueryClient();

function App() {
  return (
    <Auth0Provider
      domain={import.meta.env.VITE_AUTH0_DOMAIN}
      clientId={import.meta.env.VITE_AUTH0_CLIENT_ID}
      authorizationParams={{
        redirect_uri: window.location.origin,
        audience: import.meta.env.VITE_AUTH0_AUDIENCE,
        //audience: "https://mug-api.com",
      }}
      cacheLocation="localstorage"
    >
      <QueryClientProvider client={queryClient}>
        <Router>
          <div className="min-h-screen bg-gray-100">
            <Navigation />
            <Routes>
              <Route path="/" element={
                <ProtectedRoute>
                  <MugListPage />
                </ProtectedRoute>
              } />
              <Route path="/mugs" element={
                <ProtectedRoute>
                  <MugListPage />
                </ProtectedRoute>
              } />
              <Route path="/users" element={  // Add this route
                <ProtectedRoute>
                  <UserManagementPage />
                </ProtectedRoute>
              } />
            </Routes>
          </div>
        </Router>
      </QueryClientProvider>
    </Auth0Provider>
  );
}

export default App;