// pages/user_management_page.tsx
import { useAuth0 } from '@auth0/auth0-react';
import { useIsAdmin } from '../features/users/hooks/use_user_profile';

export function UserManagementPage() {
  const { user } = useAuth0();
  const { data: isAdmin, isLoading: isAdminLoading } = useIsAdmin();

  // For now, use demo data - you can create a real users API later
  const demoUsers = [
    { id: '1', name: 'Admin User', email: 'admin@example.com', role: 'ADMIN' },
    { id: '2', name: 'Regular User', email: 'user@example.com', role: 'USER' },
    { id: '3', name: user?.name || 'Current User', email: user?.email || '', role: 'USER' }
  ];

  if (isAdminLoading) {
    return <div className="flex justify-center p-8">Loading...</div>;
  }

  if (!isAdmin) {
    return (
      <div className="container mx-auto p-6">
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
          Access denied. Administrator privileges required.
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">User Management</h1>
      <div className="bg-white rounded-lg shadow-md overflow-hidden">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">ID</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Email</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Role</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {demoUsers.map((user) => (
              <tr key={user.id}>
                <td className="px-6 py-4 text-sm text-gray-900">{user.id}</td>
                <td className="px-6 py-4 text-sm text-gray-900">{user.name}</td>
                <td className="px-6 py-4 text-sm text-gray-900">{user.email}</td>
                <td className="px-6 py-4 text-sm">
                  <span className={`px-2 py-1 rounded-full text-xs font-medium ${
                    user.role === 'ADMIN' 
                      ? 'bg-purple-100 text-purple-800' 
                      : 'bg-green-100 text-green-800'
                  }`}>
                    {user.role}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <p className="mt-4 text-gray-600 text-sm">
        Note: This is demo data. In a real app, you'd fetch users from your backend.
      </p>
    </div>
  );
}