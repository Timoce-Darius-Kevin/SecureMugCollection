import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../features/contexts/login_context';
import { useAuth0 } from '@auth0/auth0-react';

interface ProtectedRouteProps {
    children: React.ReactNode;
    requireAdmin?: boolean;
}

export const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ 
    children, 
    requireAdmin = false 
}) => {
    const { isAuthenticated, isAdmin, loading } = useAuth0();
    const { isAuthenticated: isAuth0, isLoading, loginWithRedirect } = useAuth0();

    if (loading || isLoading) {
        return <div className="flex justify-center p-8">Loading...</div>;
    }

    if (!isAuthenticated) {
        loginWithRedirect();
        return <Navigate to="/login" replace />;
    }

    if (requireAdmin && !isAdmin) {
        return (
            <div className="container mx-auto p-6">
                <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
                    Access denied. Administrator privileges required.
                </div>
            </div>
        );
    }

    return <>{children}</>;
};